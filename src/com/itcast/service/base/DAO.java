package com.itcast.service.base;

import java.util.LinkedHashMap;

import com.itcast.bean.QueryResult;

public interface DAO {

	/*
	 * 保存实体
	 */
	public void save(Object entity);
	/*
	 * 更新实体
	 */
	public void update(Object entity);
	/*
	 * 删除实体
	 */
	public <T> void delete(Class<T> entityClass,Object entityid);
	/*
	 * 删除实体(实体id数组)
	 */
	public <T> void delete(Class<T> entityClass,Object[] entityids);
	
	/*
	 * 获取实体(实体类,实体id)
	 */
	public <T> T find(Class<T> entityClass,Object entityid);
	
	/*
	 * 获取分页数据(实体类,开始索引,需要获取的记录数)
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
