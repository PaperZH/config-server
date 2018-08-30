package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.service.BannerService;
import com.ucar.qtc.admin.service.FileService;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pages")
public class FrontPageController {

    int limit = 6;
    int offset = 0;
    int page = 1;
    @Autowired
    private BannerService bannerService;

    @Autowired
    private FileService fileService;

    @GetMapping("banner/list")
    public ResponseResult bannerList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit",limit);
        params.put("offset",offset);
        params.put("page",1);
        Query query = new Query(params);
        List<BannerDO> bannerList = bannerService.list(query);
        int total = bannerService.count(query);
        PageUtils pageUtils = new PageUtils(bannerList, total);
        return ResponseResult.page(pageUtils);
    }

}
