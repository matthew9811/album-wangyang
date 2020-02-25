package com.shengxi.wangyang.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.drew.imaging.ImageProcessingException;
import com.shengxi.wangyang.common.util.AtlaUtil;
import com.shengxi.wangyang.common.util.CosUtil;
import com.shengxi.wangyang.common.util.ExifUitl;
import com.shengxi.wangyang.common.util.KeyUtil;
import com.shengxi.wangyang.common.util.WeChatUtil;
import com.shengxi.wangyang.entity.Album;
import com.shengxi.wangyang.entity.AlbumPhoto;
import com.shengxi.wangyang.entity.Photo;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.mapper.AlbumDao;
import com.shengxi.wangyang.mapper.AlbumPhotoDao;
import com.shengxi.wangyang.mapper.CustomerDao;
import com.shengxi.wangyang.mapper.PhotoDao;
import com.shengxi.wangyang.service.CustomerService;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务层实现类
 *
 * @author y
 * @version 1.1.0
 * @date 2020-02-19 14:40:18
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    private static Integer pageSize = 5;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private AlbumPhotoDao albumPhotoDao;

    /**
     * 实现登录授权
     * 对微信授权发起请求
     * 获取唯一的openid
     * 保存openid到数据库
     *
     * @param jsCode 临时的jscode
     * @return ApiResponse 授权信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public ApiResponse login(String jsCode) {
        JSONObject loginSession = WeChatUtil.getLoginSession(jsCode);
        String openid = loginSession.getStr("openid");
        if (ObjectUtil.isNotNull(openid)) {
            int num = customerDao.judgeIsExist(openid);
            if (num > 0) {
                return new ApiResponse(200, "授权成功", openid);
            } else {
                num = customerDao.insert(openid);
                if (num > 0) {
                    return new ApiResponse(200, "插入成功", openid);
                }
            }
        }
        return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
    }

    /**
     * 上传文件，并实现保存到cos，同时将对应的信息保存进数据库
     *
     * @param openId      用户唯一标识
     * @param uploadFiles 照片文件数组
     * @return a result
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ApiResponse uploadFiles(String openId, MultipartFile[] uploadFiles) {
        /*
         * 循环操作，
         * 先获取exif的值，
         * 然后获取对应的地址
         * 最后上传文件
         * 成功后进行数据库保存
         * */
        List<Photo> list = new ArrayList<>();
        Integer customerId = customerDao.selectIdByJsCode(openId);
        for (MultipartFile uploadFile : uploadFiles) {
            try {
                //MultipartFile转file
                File tempFile = File.createTempFile(KeyUtil.getKey(), FileTypeUtil.getType(uploadFile.getInputStream()));
                uploadFile.transferTo(tempFile);
                //读取exif数据
                String[] strings = ExifUitl.readExif(tempFile);
                Date filmingTime;
                Photo photo = new Photo();
                //对exif数据进行判断
                if (strings[2] != null) {
                    filmingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strings[2]);
                } else {
                    filmingTime = new Date();
                }
                if (strings[0] != null && strings[1] != null) {
                    //经度
                    String lng = ExifUitl.pointToDegree(strings[0]);
                    //纬度
                    String lat = ExifUitl.pointToDegree(strings[1]);
                    photo.setArea(AtlaUtil.getLocalAddress(lat + "," + lng));
                } else {
                    photo.setArea("中国，广东省， 广东东软学院");
                }
                photo.setCustomerId(customerId);
                photo.setFilmingTime(filmingTime);
                photo.setUploadTime(new Date());
                photo.setLink(CosUtil.upload(tempFile, ".".concat(FileTypeUtil.getType(tempFile))));
                list.add(photo);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ImageProcessingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        Integer integer = photoDao.insertByList(list);
        if (integer > 0) {
            return new ApiResponse(ApiResponse.Status.SUCCESS, "上传成功!");
        }
        return new ApiResponse(ApiResponse.Status.INTERNAL_SERVER_ERROR, "数据库错误!");
    }

    /**
     * 根据opendid获取时间区间内的照片
     *
     * @param startTime startTime
     * @param pageNum   page number
     * @param openId    openId
     * @return list of selected photos
     */
    @Override
    public ApiResponse getPhotoList(Date startTime, Integer pageNum, String openId) {
        Date endTime = new Date();
        //创建结果集
        Map<Date, List<Photo>> result = new ConcurrentHashMap<>(CustomerServiceImpl.pageSize);
        List<Date> dates = photoDao.selectPhotoFimingTime();
        //第一次访问
        if (pageNum.equals(1)) {
            //取第一个
            startTime = dates.get(0);
            if (dates.size() >= CustomerServiceImpl.pageSize) {
                endTime = dates.get(4);
            } else {
                endTime = dates.get(dates.size() - 1);
            }
        } else {
            //非第一次访问
            endTime = dates.get(pageNum * CustomerServiceImpl.pageSize - 1 > dates.size() ? dates.size() - 1
                    : pageNum * CustomerServiceImpl.pageSize - 1);
        }
        List<Photo> list = photoDao.selectPhotoList(startTime, endTime, openId);
        for (Photo obj : list) {
            List<Photo> temp;
            if (result.containsKey(obj.getFilmingTime())) {
                temp = result.get(obj.getFilmingTime());
                temp.add(obj);
                result.put(obj.getFilmingTime(), temp);
            } else {
                temp = new ArrayList<>();
                temp.add(obj);
                result.put(obj.getFilmingTime(), temp);
            }
        }
        Map<String, Object> data = new ConcurrentHashMap<>(2);
        data.put("result", result);
        data.put("nextTime", endTime);
        return new ApiResponse(200, "请求成功!", data);
    }

    /**
     * get all albums with unique openId
     *
     * @param openId
     * @return All albums
     */
    @Override
    public List<Album> getAlbumList(String openId) {
        return albumDao.selectAlbumListByOpenId(openId);
    }

    /**
     * get an album detail by a unique albumId
     *
     * @param albumId id
     * @return all detail
     */
    @Override
    public List<AlbumPhoto> getAlbumDetail(Integer albumId) {
        return albumPhotoDao.selectAlbumDatailByAlbumId(albumId);
    }

    /**
     * create a album and return this result
     *
     * @param openId    openId
     * @param albumName name of album
     * @return a result from create album
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ApiResponse createAlbum(String openId, String albumName) {
        int num = albumDao.selectByName(albumName);
        if (num == 0) {
            int result = albumDao.insertByName(openId, albumName);
            if (result > 0) {
                return new ApiResponse(ApiResponse.Status.SUCCESS, "插入数据成功!");
            } else {
                return new ApiResponse(ApiResponse.Status.INTERNAL_SERVER_ERROR, "插入失败!");
            }
        } else {
            return new ApiResponse(ApiResponse.Status.INTERNAL_SERVER_ERROR, "相册名不能重复!");
        }
    }

    /**
     * Delete the corresponding data in the selected list
     *
     * @param ids ids[]
     * @return a result from deleted
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse deletePhotos(Integer[] ids) {
        int deleteNum = photoDao.deleteByIdList(ids);
        if (deleteNum > 0) {
            return new ApiResponse(ApiResponse.Status.SUCCESS, "删除了" + deleteNum + "张照片!");
        }
        return new ApiResponse(ApiResponse.Status.INTERNAL_SERVER_ERROR, "数据库操作失败!");
    }

    /**
     * Delete the corresponding data in the selected list
     *
     * @param albumIds ids[]
     * @return a result from deleted
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse deleteAlbums(Integer[] albumIds) {
        //删除所有对应的照片
        //删除对应的相册
        photoDao.deleteByAlbumsIdList(albumIds);
        albumPhotoDao.deleteByAlbumIdList(albumIds);
        albumDao.deleteByIdList(albumIds);
        return new ApiResponse(ApiResponse.Status.SUCCESS, "删除成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse editAlbumPhoto(String albumName, Integer[] photoIds) {
        /**
         * 利用mysql特性insert into 的增强，将所有数据进行 replace into
         */
        Integer id = albumDao.selectIdByName(albumName);
        albumPhotoDao.replaceAlbumPhoto(id, photoIds);
        return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
    }
}
