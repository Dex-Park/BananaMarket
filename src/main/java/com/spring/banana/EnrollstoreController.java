package com.spring.banana;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.BananaShopVO;
import com.enroll.service.EnrollService;

@Controller
public class EnrollstoreController {
	
	@Autowired
	private EnrollService shopService;
	
	@RequestMapping(value="/enroll_jusoPopup.do",method= {RequestMethod.POST, RequestMethod.GET})
	public String enroll_jusoPopup() {
		return "/enrollstore/jusoPopup";
	}
	
	/**
	 * ��ü ���� ó��
	 * @return
	 */
	@RequestMapping(value ="/deleteStore_delete_proc.do", method = RequestMethod.GET)
	public String deleteStore_delete_proc(String sid) {	
		return (String)shopService.delete(sid);
	}
	
	/**
	 * ��ü ���� ȭ��
	 * @return
	 */
	@RequestMapping(value ="/deleteStore_delete.do", method = RequestMethod.GET)
	public ModelAndView deleteStore_delete(String sid) {	
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("sid", sid);
		mv.setViewName("/enrollstore/deleteStore");
		
		return mv;
	} 
	
	/**
	 * ��ü ���� ó��
	 * @return
	 */
	@RequestMapping(value ="/updateStore_update_proc.do", method = RequestMethod.POST)
	public ModelAndView updateStore_update_proc(BananaShopVO vo, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) {	
		List<MultipartFile> fileList = mtfRequest.getFiles("file_list");
		//������ ������
		String path1 = request.getSession().getServletContext().getRealPath("/");
		String path2 = "\\resources\\upload\\";
		
		System.out.println(fileList.size());
		//vo�� ������ �߰�
		vo.setSavepath1(path1+path2);
		vo.setFlie_list(fileList);
		/*
		 * System.out.println(vo.getFlie_list().get(0));
		 * System.out.println(vo.getFlie_list().get(1));
		 */
		
		return (ModelAndView)shopService.update(vo);
	}
	
	/**
	 * ��ü ���� ȭ��
	 * @return
	 */
	@RequestMapping(value="/updateStore.do",method=RequestMethod.GET)
	public ModelAndView updateStore(String sid) {
		return (ModelAndView)shopService.getUpdateContent(sid);
	}
	
	/**
	 * ��ü ��� ó��
	 * @return
	 */
	@RequestMapping(value="/enrollstore_write_proc.do",method=RequestMethod.POST)
	public ModelAndView enrollstore_write_proc(BananaShopVO vo, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) {
		List<MultipartFile> fileList = mtfRequest.getFiles("file_list");
		//������ ������
		String path1 = request.getSession().getServletContext().getRealPath("/");
		String path2 = "\\resources\\upload\\";
				
		//vo�� ������ �߰�
		vo.setSavepath1(path1+path2);
		vo.setFlie_list(fileList);
		
		return (ModelAndView)shopService.insert(vo);
	}

	/**
	 * ��ü ��� ȭ��
	 * @return
	 */
	@RequestMapping(value="/enrollstore.do",method=RequestMethod.GET)
	public String enrollStore() {
		return "/enrollstore/enrollstore";
	}
}
