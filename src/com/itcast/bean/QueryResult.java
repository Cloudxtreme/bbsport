package com.itcast.bean;

import java.util.List;

public class QueryResult<T> {
	private List<T> resultlist;//存放查询结果
	private long totalrecord;//总记录数

	public List<T> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}

}
