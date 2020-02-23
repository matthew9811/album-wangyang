package com.shengxi.wangyang.service;


import com.shengxi.wangyang.entity.Album;
import com.shengxi.wangyang.entity.AlbumPhoto;
import com.shengxi.wangyang.entity.Photo;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户服务层对外接口
 *
 * @author y
 * @version 1.0.0
 * @date 2020-02-16 22:46:47
 */
public interface CustomerService {


    /**
     * 利用jscode调用对应的api获取唯一的openid
     * 实现登录
     *
     * @param jsCode 临时的jscode
     */
    ApiResponse login(String jsCode);

    /**
     * 上传照片
     *
     * @param openId      用户唯一标识
     * @param uploadFiles 照片文件数组
     * @return a result from upload
     */
    ApiResponse uploadFiles(String openId, MultipartFile[] uploadFiles);

    /**
     * 根据opendid获取时间区间内的照片
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @param openId    openId
     * @return list of selected photos
     */
    List<Photo> getPhotoList(Date startTime, Date endTime, String openId);

    /**
     * get all albums with same openId
     *
     * @param openId openID
     * @return All albums
     */
    List<Album> getAlbumList(String openId);

    /**
     * get an album detail by a unique albumId
     *
     * @param albumId id
     * @return all detail
     */
    List<AlbumPhoto> getAlbumDetail(Integer albumId);

    /**
     * create a album and return this result
     *
     * @param openId    openId
     * @param albumName name of album
     * @return a result from create album
     */
    ApiResponse createAlbum(String openId, String albumName);

    /**
     * Delete the corresponding data in the selected list
     *
     * @param ids ids[]
     * @return a result from deleted
     */
    ApiResponse deletePhotos(Integer[] ids);

    /**
     * Delete the corresponding data in the selected list
     *
     * @param albumIds ids[]
     * @return a result from deleted
     */
    ApiResponse deleteAlbums(Integer[] albumIds);

    /**
     * Modify the association of albums and photos
     *
     * @param albumName album name
     * @param photoIds  photo id list
     * @return a result from edit
     */
    ApiResponse editAlbumPhoto(String albumName, Integer[] photoIds);
}
