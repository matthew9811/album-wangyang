package com.shengxi.wangyang.app;


import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author y
 */
@Api(value = "wechat控制器", tags = "微信小程序访问接口控制器")
@RequestMapping("/wechat")
@RestController
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private CustomerService customerService;


    @ApiImplicitParam(name = "jsCode", required = true, paramType = "body", value = "唯一的jsCode")
    @ApiResponses(
            {@io.swagger.annotations.ApiResponse(code = 404, message = "找不到对应的页面"),
                    @io.swagger.annotations.ApiResponse(code = 200, message = "授权成功")}
    )
    @GetMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestParam String jsCode) {
        return customerService.login(jsCode);
    }

    @ApiOperation(value = "图片上传", notes = "多文件上传，适用于单文件上传，需要使用表单格式化上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", value = "用户唯一标识", paramType = "form", required = true),
            @ApiImplicitParam(name = "uploadFiles", value = "照片文件数组", paramType = "form", required = true, dataTypeClass = MultipartFile[].class)
    })

    @ResponseBody
    @PostMapping("/upload")
    public Object upload(@RequestParam(value = "uploadFiles") MultipartFile[] uploadFiles, @RequestParam(value = "jsCode") String jsCode) {
        customerService.uploadFiles(jsCode, uploadFiles);
        return uploadFiles;
    }

    @ApiOperation(value = "图片上传2", notes = "多文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", value = "用户唯一标识", paramType = "form", required = true),
            @ApiImplicitParam(name = "uploadFiles", value = "照片文件数组", paramType = "form", required = true, dataType = "MultipartFile[]")
    })
    @PostMapping("/upload2")
    public void upload2(String[] uploadFiles, String jsCode) {
        System.out.println(jsCode);
        System.out.println(uploadFiles);
    }
}
