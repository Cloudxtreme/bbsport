package com.itcast.web.formbean.product;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
/*
 * 封装页面数据的formbean
 */
public class ProductTypeForm extends ActionForm {

	private int page;
	private Integer parentid;
	private String name;
	private String note;
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPage() {
		return page<1?1:page;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
