package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.dto.BannerDTO;
import com.ucar.qtc.admin.dto.do2dto.BannerConvert;
import com.ucar.qtc.admin.service.FileService;
import com.ucar.qtc.admin.service.impl.BannerServiceImpl;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pages")
public class FrontPageController {
    @Value("${file.filePath}")
    String filePath;

    @Value("${file.pre}")
    String filePre;

    @Value("${file.server}")
    String fileServer;

    @Autowired
    private BannerServiceImpl bannerService;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id) {
        BannerDTO bannerDTO = BannerConvert.MAPPER.do2dto(bannerService.get(id));
        return ResponseResult.data(bannerDTO);
    }

    @GetMapping("list")
    public ResponseResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<BannerDO> bannerList = bannerService.list(query);
        int total = bannerService.count(query);
        PageUtils pageUtils = new PageUtils(bannerList, total);
        return ResponseResult.page(pageUtils);
    }

}
