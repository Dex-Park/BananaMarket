package com.spring.banana;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.productVO;
import com.spring.service.ProductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	
	@RequestMapping(value="/productContent.do", method=RequestMethod.GET)
	public ModelAndView productContent(String pid) {
		return (ModelAndView)productService.getContent(pid);
	}
	
	@RequestMapping(value="/popularProduct.do", method=RequestMethod.GET)
	public ModelAndView popularProduct() {
		return (ModelAndView)productService.getList();
	}
	
	@RequestMapping(value="/writePage.do", method=RequestMethod.GET)
	public String writePage() {
		return "/popularProduct/writePage";
	}
	
	@RequestMapping(value="/writePage_proc.do", method=RequestMethod.POST)
	public String writePage_proc(productVO vo, HttpServletRequest request ) {
		//������ ������
		String path1 = request.getSession().getServletContext().getRealPath("/");
		String path2 = "\\resources\\upload\\";
		vo.setSavepath(path1+path2);
		
		return (String)productService.insert(vo);
	}
	/*
	@RequestMapping(value="/updatePage.do", method=RequestMethod.GET)
	public ModelAndView updatePage(String pid) {
		return (ModelAndView)productService.getUpdateContent(pid);
	}
	
	@RequestMapping(value="/updatePage_proc.do", method=RequestMethod.POST)
	public String updatePage_proc(productVO vo) {
		return (String)productService.getUpdate(vo);
	}
	*/
	@RequestMapping(value="/deletePage.do", method=RequestMethod.GET)
	public String deletePage() {
		return "/popularProduct/deletePage";
	}
	
	@RequestMapping(value="/promoteDongne.do", method=RequestMethod.GET)
	public String promoteDonge() {
		return "/popularProduct/promoteDongne";
	}
}
