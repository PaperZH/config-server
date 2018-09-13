package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.dto.BannerDTO;
import com.ucar.qtc.admin.dto.do2dto.BannerConvert;
import com.ucar.qtc.admin.rpc.FileUploadService;
import com.ucar.qtc.admin.service.BannerService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * guodong.zhang
 */
@Api(description = "首页轮播图推荐API接口")
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private FileUploadService fileUploadServie;

    @ApiOperation(value="根据id获取Banner信息", notes="根据id获取Banner信息")
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
    @ApiOperation(value="上传banner文件", notes="上传banner文件")
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
    @ApiOperation(value="获取banner文件列表", notes="获取banner文件列表")
    @Log("获取banner文件列表")
    @GetMapping
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
    @ApiOperation(value="添加banner文件数据", notes="添加banner文件数据")
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
    @ApiOperation(value="修改banner文件数据", notes="修改banner文件数据")
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
    @ApiOperation(value="删除banner文件数据", notes="删除banner文件数据")
    @Log("删除banner文件数据")
    @DeleteMapping
    public ResponseResult remove(Long id) {
        return ResponseResult.operate(bannerService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @ApiOperation(value="批量删除", notes="批量删除")
    @DeleteMapping("/batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
        return ResponseResult.operate(bannerService.batchRemove(ids) > 0);
    }
}
