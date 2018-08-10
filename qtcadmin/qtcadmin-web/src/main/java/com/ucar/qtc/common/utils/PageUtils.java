package com.ucar.qtc.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 分页工具类
 * @date: 2018/8/7 10:19
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
