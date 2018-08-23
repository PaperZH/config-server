package com.ucar.test.controller;

import com.ucar.qtcassist.api.DemoApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/21
 */

@FeignClient(value = "qtcassist")
public interface HelloBackgroundService extends DemoApi {
}
