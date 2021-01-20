package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DongneController {
	
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
	public String dongneLife_content() {
	
		return "/dongneLife/dongneLife_content";
	}
	
	/**
	 * ���׻�Ȱ - �۾��� ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife_write.do", method = RequestMethod.GET)
	public String dongnelife_write() {
	
		return "/dongneLife/dongneLife_write";
	}

	/**
	 * ���׻�Ȱ - Ȩ ȭ��
	 * @return
	 */
	@RequestMapping(value ="/dongneLife.do", method = RequestMethod.GET)
	public String dongnelife() {
	
		return "/dongneLife/dongneLife";
	}
	
}

