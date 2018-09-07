package com.ucar.qtcassist.course.util;

import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.VO.QueryVO;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;

public class QueryConvertUtil {

    public static QueryDO convertToQueryDO(QueryVO queryVO) {
        QueryDO queryDO = new QueryDO();
        if(queryVO.getUserId() != null) {
            queryDO.setUserId(queryVO.getUserId());
        }
//        queryDO.setCourseId();
        if(queryVO.getCourseIds() != null) {
            queryDO.setCourseIds(queryVO.getCourseIds());
        }
        if(queryVO.getCourseName() != null) {
            queryDO.setCourseName(queryVO.getCourseName());
        }
        if(queryVO.getType() != null) {
            queryDO.setOrderType(queryVO.getType());
        }
        if(queryVO.getStartDate() != null) {
            queryDO.setStartDate(queryVO.getStartDate());
        }
        if(queryVO.getEndDate() != null) {
            queryDO.setEndDate(queryVO.getEndDate());
        }
        if(queryVO.getCurrentPage() != null && queryVO.getPageSize() != null) {
            queryDO.setStartIndex((queryVO.getCurrentPage() - 1) * queryVO.getPageSize());
        }
        if(queryVO.getPageSize() != null) {
            queryDO.setPageSize(queryVO.getPageSize());
        }
        return queryDO;
    }
}
