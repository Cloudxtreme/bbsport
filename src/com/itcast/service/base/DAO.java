package com.itcast.service.base;

import java.util.LinkedHashMap;

import com.itcast.bean.QueryResult;

public interface DAO {

	/*
	 * ����ʵ��
	 */
	public void save(Object entity);
	/*
	 * ����ʵ��
	 */
	public void update(Object entity);
	/*
	 * ɾ��ʵ��
	 */
	public <T> void delete(Class<T> entityClass,Object entityid);
	/*
	 * ɾ��ʵ��(ʵ��id����)
	 */
	public <T> void delete(Class<T> entityClass,Object[] entityids);
	
	/*
	 * ��ȡʵ��(ʵ����,ʵ��id)
	 */
	public <T> T find(Class<T> entityClass,Object entityid);
	
	/*
	 * ��ȡ��ҳ����(ʵ����,��ʼ����,��Ҫ��ȡ�ļ�¼��)
	 * 
	 * order by key1 desc,key2,asc
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,String wherejpql,Object[] queryParams); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,LinkedHashMap<String,String> orderBy); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,int maxresult); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, String wherejpql,Object[] queryParams,
			LinkedHashMap<String,String> orderBy); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,int maxresult,String wherejpql,Object[] queryParams); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,int maxresult,
			LinkedHashMap<String,String> orderBy); 
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex,int maxresult,String wherejpql,Object[] queryParams,
			LinkedHashMap<String,String> orderBy); 
}
