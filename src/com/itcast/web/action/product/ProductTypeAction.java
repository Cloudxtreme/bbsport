package com.itcast.web.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.itcast.bean.PageView;
import com.itcast.bean.QueryResult;
import com.itcast.bean.product.ProductType;
import com.itcast.service.product.ProductTypeService;
import com.itcast.web.formbean.product.ProductTypeForm;

@Controller("/control/product/type/list")
public class ProductTypeAction  extends Action{
    
	@Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductTypeForm formbean = (ProductTypeForm)form;
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if(formbean.getParentid()!=null&&formbean.getParentid()>0){//有父类id传递过来,说明是查询子类,添加限制条件
			jpql.append("o.parent.typeid=?2"); 
			params.add(formbean.getParentid());
		}else{   
			jpql.append("o.parent is null");
		}
		PageView<ProductType> pageView = new PageView<ProductType>(2, formbean.getPage());
		int firstindex = (pageView.getCurrentpage()-1)* pageView.getMaxresult();
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String,String>();
		orderby.put("typeid", "desc");//将最新添加的显示在最前面
		QueryResult<ProductType> qr = productTypeService.getScrollData(ProductType.class, firstindex, pageView.getMaxresult(), jpql.toString(), new Object[]{true},orderby);
		pageView.setQueryResult(qr);
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}
}
