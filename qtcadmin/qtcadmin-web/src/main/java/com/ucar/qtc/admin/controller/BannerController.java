package com.ucar.qtc.admin.controller;


import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.dto.BannerDTO;
import com.ucar.qtc.admin.dto.do2dto.BannerConvert;
import com.ucar.qtc.admin.service.impl.BannerServiceImpl;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * guodong.zhang
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Value("${file.filePath}")
    String filePath;

    @Value("${file.pre}")
    String filePre;

    @Value("${file.server}")
    String fileServer;

    @Autowired
    private BannerServiceImpl bannerService;

    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id) {
        BannerDTO bannerDTO = BannerConvert.MAPPER.do2dto(bannerService.get(id));
        return ResponseResult.data(bannerDTO);
    }

    /**
     * 上传banner
     * @param file
     * @param key
     * @return
     */
    @Log("上传banner文件")
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
    @Log("获取banner文件列表")
    @GetMapping("list")
    public ResponseResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<BannerDO> bannerList = bannerService.list(query);
        int total = bannerService.count(query);
        PageUtils pageUtils = new PageUtils(bannerList, total);
        return ResponseResult.page(pageUtils);
    }

    /**
     * 保存
     * @param banner
     * @return
     */
    @Log("添加banner文件数据")
    @PostMapping
    public ResponseResult save(@RequestBody BannerDO banner) {
        banner.setDelFlag(1);
        return ResponseResult.operate(bannerService.save(banner) > 0);
    }

    /**
     * 修改
     * @param banner
     * @return
     */
    @Log("修改banner文件数据")
    @PutMapping
    public ResponseResult update(@RequestBody BannerDO banner) {
        return ResponseResult.operate(bannerService.update(banner) > 0);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Log("删除banner文件数据")
    @DeleteMapping
    public ResponseResult remove(Long id) {
        return ResponseResult.operate(bannerService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
        return ResponseResult.operate(bannerService.batchRemove(ids) > 0);
    }


}
