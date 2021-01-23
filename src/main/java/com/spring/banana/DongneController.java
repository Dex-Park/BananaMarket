package com.spring.banana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.BananaService;
import com.spring.vo.dongneVO;

@Controller
public class DongneController  {
	
	
	@Autowired
	private BananaService bananaService;
	/**
	 * ���׻�Ȱ - ������ ����ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_delete.do", method = RequestMethod.GET)
	public String dongneLife_delete() {
	
		return "/dongneLife/dongneLife_delete";
	}
	
	/**
	 * ���׻�Ȱ - ������ ����ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_update.do", method = RequestMethod.GET)
	public String dongneLife_update() {
	
		return "/dongneLife/dongneLife_update";
	}
	
	/**
	 * ���׻�Ȱ - ������ ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_content.do", method = RequestMethod.GET)
	public ModelAndView dongneLife_content(String bid) {
	
		return (ModelAndView)bananaService.getContent(bid);
	}
	
	/**
	 * ���׻�Ȱ - �۾��� ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_write.do", method = RequestMethod.GET)
	public String dongnelife_write() {
	
		return "/dongneLife/dongneLife_write";
	}
	
	@RequestMapping(value ="/dongneLife_write_proc.do", method = RequestMethod.POST)
	public String dongneLife_write_proc(dongneVO vo) {	
		return (String) bananaService.insert(vo);
	}

	/**
	 * ���׻�Ȱ - Ȩ ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife.do", method = RequestMethod.GET)
	public ModelAndView dongnelife() {
		
		return (ModelAndView)bananaService.getList();
	}
	
	
	
	
}

