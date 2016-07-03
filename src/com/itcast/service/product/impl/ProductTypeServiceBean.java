package com.itcast.service.product.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itcast.service.base.DaoSupport;
import com.itcast.service.product.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceBean extends DaoSupport implements ProductTypeService {
	
	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		if(null != entityids && entityids.length > 0){
			StringBuffer jpql = new StringBuffer();
			for(int i = 1; i <=entityids.length;i++){		
				if(i == entityids.length){
					jpql.append("?").append(i+1);
				}else{
					jpql.append("?").append(i+1).append(",");
				}
			}
			Query query = em.createQuery("update ProductType o set o.visible = ?1 where o.typeid in("+jpql.toString()+")"); 	
			query.setParameter(1, false);
			for(int i=1; i<= entityids.length; i++){
				query.setParameter(i+1, entityids[i-1]);
			}
			query.executeUpdate();
		}
	}
	
}
