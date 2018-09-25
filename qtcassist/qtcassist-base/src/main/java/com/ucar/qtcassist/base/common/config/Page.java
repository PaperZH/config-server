package com.ucar.qtcassist.base.common.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月17日
 */
@Configuration
public class Page {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
//        默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。
//        当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
        p.setProperty("offsetAsPageNum", "true");
//        默认值为false，该参数对使用 RowBounds 作为分页参数时有效。
//        当该参数设置为true时，使用 RowBounds 分页会进行 count 查询。
        p.setProperty("rowBoundsWithCount", "true");

        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        p.setProperty("supportMethodsArguments", "false");
//        当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0
//        就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是 Page 类型）
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
