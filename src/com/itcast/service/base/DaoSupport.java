package com.itcast.service.base;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itcast.bean.QueryResult;

@Transactional
public abstract class DaoSupport implements DAO{

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public <T> void delete(Class<T> entityClass, Object entityid) {
         delete(entityClass, new Object[]{entityid});	
	}

	@Override
	public <T> void delete(Class<T> entityClass,Object[] entityids) {  
		for(Object id : entityids){
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityid) {
		return em.find(entityClass, entityid);
	}

	@Override
	public void save(Object entity) {
       em.persist(entity);		
	}

	@Override
	public void update(Object entity) {
       em.merge(entity);
	}
   
	/*
	 * 普通分页功能
	 * wherejpql形式如下
	 * property1=?1 and property2=?2
	 * queryParams:new Object[]{true,2};
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxresult,String wherejpql,Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		QueryResult queryResult = new QueryResult<T>();
		String entityname = getEntityName(entityClass);
		Query query = em.createQuery("select o from "+ entityname + " o " + (wherejpql == null ? "":"where " + wherejpql)+ buildOrderBy(orderby));
		setQueryParams(query, queryParams);
		if(0!= maxresult){
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		queryResult.setResultlist(query.getResultList());
		query = em.createQuery("select count(o) from "+ entityname + " o " + (wherejpql == null ? "":"where " + wherejpql));
		setQueryParams(query, queryParams);
		queryResult.setTotalrecord((Long)query.getSingleResult());
		return queryResult;
	}
	
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderBy) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,orderBy);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams) {
		return getScrollData(entityClass,firstindex,maxresult,wherejpql,queryParams,null);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,null);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			LinkedHashMap<String, String> orderBy) {
		return getScrollData(entityClass,0,0,null,null,orderBy);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderBy) {
		return getScrollData(entityClass,0,0,wherejpql,queryParams,orderBy);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			String wherejpql, Object[] queryParams) {
		return getScrollData(entityClass,0,0,wherejpql,queryParams,null);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass,0,0,null,null,null);
	}

	/*
	 * 设置where查询条件的参数值:
	 * where username like ?1 and age = ?2
	 */
	protected void setQueryParams(Query query,Object[] queryParams){
		if(null != queryParams && queryParams.length>0){
			for(int i=0; i<queryParams.length;i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	
	
	/*
	 * 组装order by语句
	 * example:orderby.put("name", "desc")
	 */
	protected String buildOrderBy(LinkedHashMap<String, String> orderby){
		StringBuffer orderbysql = new StringBuffer();
		if(null != orderby && orderby.size() > 0){
			orderbysql.append(" order by ");
			//order by key1 desc,key2 asc
			for(String key : orderby.keySet()){
				orderbysql.append(key).append(" "+orderby.get(key)).append(",");
			}
			orderbysql.deleteCharAt(orderbysql.length()-1);
		}
		return orderbysql.toString();
	}
	
	/*
	 * 获取实体的名称
	 */
    protected <T> String getEntityName(Class<T> entityClass){
    	String entityname = entityClass.getSimpleName();
    	Entity entity = entityClass.getAnnotation(Entity.class);
    	if(entity.name()!=null && !"".equals(entity.name())){
    		entityname = entity.name();
    	}
    	return entityname;
    }
	 
}
