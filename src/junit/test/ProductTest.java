package junit.test;

import java.util.LinkedHashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itcast.bean.QueryResult;
import com.itcast.bean.product.ProductType;
import com.itcast.service.product.ProductTypeService;


public class ProductTest {
	
	private static ApplicationContext ctx;
	private static ProductTypeService productTypeService;  
	
	@BeforeClass
	public static void init(){
		try {
			ctx = new ClassPathXmlApplicationContext("beans.xml");
			productTypeService = (ProductTypeService) ctx.getBean("productTypeServiceBean");
		} catch (BeansException e) {
			e.printStackTrace();
		}  
	}

	@Test
	public void testrun(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ProductType productType = new ProductType();
		em.persist(productType);
		em.getTransaction().commit();
        em.close();
        factory.close();
	}
	
	@Test
	public void testSave(){
		ProductType type = new ProductType();
		type.setName("瑜伽用品");
		type.setNote("好产品");
		productTypeService.save(type);		
	}
	
	@Test
	public void testFind(){
		ProductType productType = productTypeService.find(ProductType.class, 1);
		System.out.println(productType.getName());	
	}
	
	@Test
	public void testUpdate(){
		ProductType  productType = productTypeService.find(ProductType.class, 1);
		productType.setName("足球");
		productType.setNote("好足球");
		productTypeService.update(productType);
	}
	
	@Test
	public void testDelete(){
	   productTypeService.delete(ProductType.class, new Object[]{2,3,4});
	}
	
	@Test
	public void testgetScrollData(){
	   LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
	   orderby.put("typeid", "desc");
	   orderby.put("name", "desc");
	   //QueryResult<ProductType> queryResult = productTypeService.getScrollData(ProductType.class, 0, 3," o.visible=?1 and o.name like ?2 ",new Object[]{true,"瑜伽用品"},orderby);
	   QueryResult<ProductType> queryResult = productTypeService.getScrollData(ProductType.class);
	   System.out.println(queryResult.getResultlist().size());
	   for(ProductType productType : queryResult.getResultlist()){
		   System.out.println(productType.getTypeid());
		   System.out.println(productType.getName());
		   System.out.println(productType.getNote());
		   System.out.println(productType.getVisible());
	   }
	   System.out.println(queryResult.getTotalrecord());
	}
}
