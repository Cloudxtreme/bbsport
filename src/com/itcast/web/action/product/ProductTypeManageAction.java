package com.itcast.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.itcast.bean.product.ProductType;
import com.itcast.service.product.ProductTypeService;
import com.itcast.web.formbean.product.ProductTypeForm;

@Controller("/control/product/type/manage")
public class ProductTypeManageAction extends DispatchAction {

	@Resource(name="productTypeServiceBean")
	private ProductTypeService productTypeService;
	
	/*
	 * 显示类别添加界面
	 */
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("add");
	}
	

	/*
	 * 类别添加
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductTypeForm formbean = (ProductTypeForm)form;
		ProductType productType = new ProductType(formbean.getName(),formbean.getNote());
		if(formbean.getParentid()!=null&& formbean.getParentid()>0){
			productType.setParent(new ProductType(formbean.getParentid()));
		}
		productTypeService.save(productType);
		request.setAttribute("message", "添加类别成功");
		return mapping.findForward("message");
	}
}
