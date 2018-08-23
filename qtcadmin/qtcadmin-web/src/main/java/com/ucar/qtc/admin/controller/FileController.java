package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.FileDO;
import com.ucar.qtc.admin.dto.FileDTO;
import com.ucar.qtc.admin.dto.do2dto.FileConvert;
import com.ucar.qtc.admin.service.FileService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author cong.zhou01
 * @name FileController
 * @description 文件服务
 * @date 2018-08-16 10:23
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.filePath}")
    String filePath;

    @Value("${file.pre}")
    String filePre;

    @Value("${file.server}")
    String fileServer;

    @Autowired
    private FileService fileService;

    /**
     * 获取文件
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id) {
        FileDTO fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return ResponseResult.data(fileDTO);
    }

    @GetMapping("getToken")
    public ResponseResult getToken() {
        return ResponseResult.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-admin/file/upload")
                .put("key", StringUtils.generateUUID());
    }

    /**
     * 上传文件
     * @param file
     * @param key
     * @return
     */
    @Log("上传文件")
    @PostMapping("upload")
    public ResponseResult upload(MultipartFile file, String key) {
        try {
            if (StringUtils.isBlank(key)) {
                key = StringUtils.generateUUID();
            }
            final String resPath = FileUtils.saveFile(file,file.getBytes(),filePath,key);
            final String url = fileServer + filePre + "/"+resPath;
            return ResponseResult.ok().put("fileUrl", url);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.error("文件上传失败");
        }
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
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
    @Log("删除文件数据")
    @DeleteMapping
    public ResponseResult remove(Long id) {
        return ResponseResult.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
        return ResponseResult.operate(fileService.batchRemove(ids) > 0);
    }
}
