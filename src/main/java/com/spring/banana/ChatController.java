package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
	
	/**
	 * ä�� - �� ����
	 * @return
	 */
	@RequestMapping(value ="/chat_list_content.do", method = RequestMethod.GET)
	public String chat_list_content() {
		return "/chatBanner/chat_list_content";
	}
	
	/**
	 * ä�� - Ȩ ȭ��
	 * @return
	 */
	@RequestMapping(value ="/chat_list.do", method = RequestMethod.GET)
	public String chat_list() {
		return "/chatBanner/chat_list";
	}
	
	/**
	 * ä��
	 * @return
	 */
	@RequestMapping(value ="/chat.do", method = RequestMethod.GET)
	public String chat() {
		return "/chatBanner/chat";
	}
	
}
