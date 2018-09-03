package com.ucar.qtcassist.course.util;

import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.VO.QueryVO;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;

public class QueryConvertUtil {

    public static QueryDO convertToQueryDO(QueryVO queryVO) {
        QueryDO queryDO = new QueryDO();
        queryDO.setUserId(queryVO.getUserId());
//        queryDO.setCourseId();
        queryDO.setCourseIds(queryVO.getCourseIds());
        queryDO.setCourseName(queryVO.getCourseName());
        queryDO.setOrderType(queryVO.getType());
        queryDO.setStartDate(queryVO.getStartDate());
        queryDO.setEndDate(queryVO.getEndDate());
        queryDO.setStartIndex((queryVO.getCurrentPage() - 1) * queryVO.getPageSize());
        queryDO.setPageSize(queryVO.getPageSize());
        return queryDO;
    }
}
