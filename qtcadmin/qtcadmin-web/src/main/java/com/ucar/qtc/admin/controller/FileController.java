package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.FileDO;
import com.ucar.qtc.admin.dto.FileDTO;
import com.ucar.qtc.admin.dto.do2dto.FileConvert;
import com.ucar.qtc.admin.rpc.FileUploadService;
import com.ucar.qtc.admin.service.FileService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author cong.zhou01
 * @name FileController
 * @description 文件服务
 * @date 2018-08-16 10:23
 */
@Api(description = "文件服务API接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileUploadService fileUploadServie;

    /**
     * 获取文件
     * @param id
     * @return
     */
    @ApiOperation(value="获取ID获取文件信息", notes="获取ID获取文件信息")
    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id) {
        FileDTO fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return ResponseResult.data(fileDTO);
    }

    @ApiIgnore
    @GetMapping("getToken")
    public ResponseResult getToken() {
        return ResponseResult.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-admin/file/upload")
                .put("key", StringUtils.generateUUID());
    }

    /**
     * 上传banner
     * @param file
     * @param key
     * @return
     */
    @ApiOperation(value="上传文件", notes="上传文件，可以指定文件名")
    @Log("上传banner文件")
    @PostMapping("upload")
    public ResponseResult upload(MultipartFile file, String key) {
        ResponseResult result = fileUploadServie.upload(file, key);
        return result;
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value="获取文件列表", notes="获取文件列表")
    @Log("获取文件列表")
    @GetMapping
    public ResponseResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<FileDO> fileList = fileService.list(query);
        int total = fileService.count(query);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return ResponseResult.page(pageUtils);
    }

    /**
     * 保存
     * @param file
     * @return
     */
    @ApiOperation(value="保存文件", notes="保存文件")
    @Log("添加文件数据")
    @PostMapping
    public ResponseResult save(@RequestBody FileDO file) {
        file.setDelFlag(1);
        file.setType(1);
        return ResponseResult.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     * @param file
     * @return
     */
    @ApiOperation(value="修改文件", notes="修改文件")
    @Log("修改文件数据")
    @PutMapping
    public ResponseResult update(@RequestBody FileDO file) {
        return ResponseResult.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value="删除文件", notes="删除文件")
    @Log("删除文件数据")
    @DeleteMapping
    public ResponseResult remove(Long id) {
        return ResponseResult.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @ApiIgnore
    @DeleteMapping("/batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
        return ResponseResult.operate(fileService.batchRemove(ids) > 0);
    }
}
