package com.shengxi.wangyang.app;


import com.shengxi.wangyang.entity.Album;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private CustomerService customerService;


    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", required = true, paramType = "body", value = "唯一的jsCode"),
            @ApiImplicitParam(name = "secret", value = "小程序密钥", required = true),
            @ApiImplicitParam(name = "appid", value = "appid", required = true)
    })
    @ApiResponses(
            {@io.swagger.annotations.ApiResponse(code = 404, message = "找不到对应的页面"),
                    @io.swagger.annotations.ApiResponse(code = 200, message = "授权成功")}
    )
    public ApiResponse login(String jsCode, String secret, String appid) {
        return customerService.login(jsCode, secret, appid);
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
            @ApiImplicitParam(name = "tempTime", value = "获取列表的开始时间，默认为当前时间.如果第一次传递时候，不要传递时分秒，其余的直接返回即可", required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码，默认为1， 往后+1", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "openId", value = "openId", required = true)
    })
    public ApiResponse getPhotoList(@RequestParam("tempTime") Date tempTime, @RequestParam("pageNum") Integer pageNum, @RequestParam("openId") String openId) {
        return customerService.getPhotoList(tempTime, pageNum, openId);
    }

    @GetMapping("/getAlbumList")
    @ApiOperation(value = "获取相册")
    @ApiImplicitParam(name = "openId", value = "openId", required = true)
    public List<Album> getAlbumList(@RequestParam("openId") String openId) {
        return customerService.getAlbumList(openId);
    }

    @GetMapping("/getAlbumDetail")
    @ApiOperation(value = "获取相册详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "tempTime", value = "获取列表的开始时间，默认为当前时间.如果第一次传递时候，不要传递时分秒，其余的直接返回即可",
                    required = true, paramType = "Date"),
            @ApiImplicitParam(name = "pageNum", value = "页码，默认为1， 往后+1", required = true, paramType = "Integer"),
    })
    public ApiResponse getAlbumDetail(@RequestParam("albumId") Integer albumId,
                                      @RequestParam("tempTime") Date tempTime, @RequestParam("pageNum") Integer pageNum) {
        return customerService.getAlbumDetail(albumId, tempTime, pageNum);
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
    public ApiResponse editAlbumPhoto(@RequestParam("albumName") String albumName, @RequestParam("photoIds") Integer[] photoIds) {
        return customerService.editAlbumPhoto(albumName, photoIds);

    }

    @GetMapping("/deletePhotos")
    @ApiOperation("批量删除照片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "照片id的数组", required = true, dataTypeClass = Integer.class)
    })
    public ApiResponse deletePhotos(@RequestParam("ids") Integer[] ids) {
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

    @ApiOperation("搜索")
    @GetMapping("/search")
    public ApiResponse search(String temp, String openid){
        return customerService.search(temp, openid);
    }

}
