package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MypageController {
	
	/**
	 * ���������� - ������ ����
	 * @return
	 */
	@RequestMapping(value="/mypage_update.do", method=RequestMethod.GET)
	public String mypage_update() {
		return "mypage/mypage_update";
	}
	
	/**
	 * ���������� - Ȩȭ��
	 * @return
	 */
	@RequestMapping(value="/mypage.do", method=RequestMethod.GET)
	public String mypage() {
		return "mypage/mypage";
	}

}
