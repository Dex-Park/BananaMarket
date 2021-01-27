package com.spring.banana;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.dongneVO;
import com.spring.service.BananaService;

@Controller
public class DongneController  {
	
	
	@Autowired
	private BananaService bananaService;
	
	/**
	 * 동네생활 - 글정보 삭제화면
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_delete.do", method = RequestMethod.GET)
	public String dongneLife_delete(String bid) {	
		return (String)bananaService.delete(bid);
	}
	
	/**
	 * 동네생활 - 글정보 수정화면
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_update.do", method = RequestMethod.GET)
	public ModelAndView dongneLife_update(String bid) {
		
		return (ModelAndView)bananaService.getUpdateContent(bid);
	}
	
	@RequestMapping(value ="/dongneLife_update_proc.do", method = RequestMethod.POST)
	public ModelAndView dongneLife_update_proc(dongneVO vo ,  MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {	
		
		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		
		return (ModelAndView) bananaService.update(vo);
	}

	/**
	 * 동네생활 - 글정보 화면
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_content.do", method = RequestMethod.GET)
	public ModelAndView dongneLife_content(String bid) {
	
		return (ModelAndView)bananaService.getContent(bid);
	}
	
	/**
	 * 동네생활 - 글쓰기 화면
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_write.do", method = RequestMethod.GET)
	public String dongnelife_write() {
	
		return "/dongneLife/dongneLife_write";
	}
	
	@RequestMapping(value ="/dongneLife_write_proc.do", method = RequestMethod.POST)
	public String dongneLife_write_proc(dongneVO vo , MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {
		

		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		return (String) bananaService.insert(vo);
	}

	/**
	 * 동네생활 - 홈 화면
	 * @return
	 */
	@RequestMapping(value ="/dongneLife.do", method = RequestMethod.GET)
	public ModelAndView dongnelife() {
		
		return (ModelAndView)bananaService.getList();
	}
	
	
	
	
}

