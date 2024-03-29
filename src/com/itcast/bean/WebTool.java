package com.itcast.bean;

public class WebTool {

	/*
	 * viewpagecount:当前页面实际显示的记录条数,不是每页显示的最大记录数
	 * currentPage
	 * totalpage
	 * 功能生成页面索引
	 */
	public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage){
		long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
		long endpage = currentPage+viewpagecount/2;
		if(startpage<1){
			startpage = 1;
			if(totalpage>=viewpagecount) endpage = viewpagecount;
			else endpage = totalpage;
		}
		if(endpage>totalpage){
			endpage = totalpage;
			if((endpage-viewpagecount)>0) startpage = endpage-viewpagecount+1;
			else startpage = 1;
		}
		return new PageIndex(startpage, endpage);		
   }
}
