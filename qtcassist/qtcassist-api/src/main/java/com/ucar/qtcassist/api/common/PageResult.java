package com.ucar.qtcassist.api.common;

import com.ucar.qtcassist.api.model.Result;

import java.util.List;

/**
 * 版权声明： Copyright©2017 www.ucarinc.com All Rights Reserved.
 *
 * @author bei.zhang@ucarinc.com
 * @version V1.0
 * @date 2018/6/22
 * Description : 人员
 */

public class PageResult<T> extends Result<T> {

    /**
     * 构建成功返回分页结果
     * @param list
     * @param total
     * @param <T>
     * @return
     */
    public static <T> Result<Page<T>> getSuccessResult(List<T> list, int total) {
        Page<T> page = new Page();
        page.setTotal(total);
        page.setRows(list);
        return Result.getSuccessResult(page);
    }
}
