package com.itcast.bean;

import java.util.List;

public class PageView<T> {
    /** ��ҳ����*/
	private List<T> records;
	/** ҳ�濪ʼ�����ͽ�������*/
	private PageIndex pageIndex;
	/** ��ҳ��*/
	private long totalpage =1;
	/**ÿҳ��ʾ��¼��*/
	private int maxresult=12;
	/**��ǰҳ*/
	private int currentpage=1;
	/**�ܼ�¼��*/
	private long totalrecord;
	
	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public void setQueryResult(QueryResult qr){
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public PageIndex getPageIndex() {
		return pageIndex;
	}
	public long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageIndex = WebTool.getPageIndex(maxresult,currentpage, totalpage);
	}
	public int getMaxresult() {
		return maxresult;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.maxresult == 0 ? this.totalrecord/this.maxresult:this.totalrecord/this.maxresult+1);
	}
	
}
