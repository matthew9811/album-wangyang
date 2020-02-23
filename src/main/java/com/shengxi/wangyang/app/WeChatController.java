package com.shengxi.wangyang.app;


import com.shengxi.wangyang.entity.Album;
import com.shengxi.wangyang.entity.AlbumPhoto;
import com.shengxi.wangyang.entity.Photo;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author y
 */

@RestController
@RequestMapping("/wechat")
@Api(value = "wechat控制器", tags = "微信小程序访问接口控制器")
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private CustomerService customerService;


    @GetMapping("/login")
    @ApiImplicitParam(name = "jsCode", required = true, paramType = "body", value = "唯一的jsCode")
    @ApiResponses(
            {@io.swagger.annotations.ApiResponse(code = 404, message = "找不到对应的页面"),
                    @io.swagger.annotations.ApiResponse(code = 200, message = "授权成功")}
    )
    public ApiResponse login(@RequestParam String jsCode) {
        return customerService.login(jsCode);
    }


    @PostMapping("/upload")
    @ApiOperation(value = "图片上传", notes = "多文件上传，适用于单文件上传，需要使用表单格式化上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "用户唯一标识", paramType = "form", required = true),
            @ApiImplicitParam(name = "uploadFiles", value = "照片文件数组", paramType = "form", required = true, dataTypeClass = MultipartFile[].class)
    })
    public ApiResponse upload(@RequestParam(value = "uploadFiles") MultipartFile[] uploadFiles, @RequestParam(value = "openId") String openId) {
        return customerService.uploadFiles(openId, uploadFiles);
    }

    @GetMapping("/getPhotoList")
    @ApiOperation(value = "获取图片列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "获取列表的开始时间，默认为当前时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "列表的结束时间分隔", required = true),
            @ApiImplicitParam(name = "jsCode", value = "jsCode", required = true)
    })
    public List<Photo> getPhotoList(@RequestParam("startTime") Date startTime, @RequestParam("endTime")
            Date endTime, @RequestParam("openId") String openId) {
        return customerService.getPhotoList(startTime, endTime, openId);
    }

    @GetMapping("/getAlbumList")
    @ApiOperation(value = "获取相册")
    @ApiImplicitParam(name = "openId", value = "openId", required = true)
    public List<Album> getAlbumList(@RequestParam("openId") String openId) {
        return customerService.getAlbumList(openId);
    }

    @GetMapping("/getAlbumDetail")
    @ApiOperation(value = "获取相册详情")
    @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataTypeClass = Integer.class)
    public List<AlbumPhoto> getAlbumDetail(Integer albumId) {
        return customerService.getAlbumDetail(albumId);
    }

    @GetMapping("/createAlbum")
    @ApiOperation("新增相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "openId", required = true, paramType = "query"),
            @ApiImplicitParam(name = "albumName", value = "相册名称", required = true, paramType = "query")
    })
    public ApiResponse createAlbum(@RequestParam("openId") String openId, @RequestParam("albumName") String albumName) {
        return customerService.createAlbum(openId, albumName);
    }

    @GetMapping("/editAlbumPhoto")
    @ApiOperation("修改照片和相册的关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumName", value = "相册名称", required = true),
            @ApiImplicitParam(name = "photoIds", value = "照片id", required = true, paramType = "Integer")
    })
    public ApiResponse editAlbumPhoto(String albumName, Integer[] photoIds) {
        return customerService.editAlbumPhoto(albumName, photoIds);

    }

    @GetMapping("/deletePhotos")
    @ApiOperation("批量删除照片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "照片id的数组", required = true, dataTypeClass = Integer.class)
    })
    public ApiResponse deletePhotos(Integer[] ids) {
        return customerService.deletePhotos(ids);
    }


    @GetMapping("/deleteAlbums")
    @ApiOperation("批量删除相册及其对应的所有照片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "相册id的数组", required = true, dataTypeClass = Integer.class)
    })
    public ApiResponse deleteAlbums(Integer[] ids) {
        return customerService.deleteAlbums(ids);
    }

}
