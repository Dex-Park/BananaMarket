package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestionController {
	
	/**
	 * ������ ���ֹ������� ����Ʈ
	 * @return
	 */
	@RequestMapping(value="/admin_question_list.do",method=RequestMethod.GET)
	public String admin_question_list() {
		return "/question/admin_question_list";
	}
	
	/**
	 * ������ ���ֹ������� ����
	 * @return
	 */
	@RequestMapping(value="/admin_question_content.do",method=RequestMethod.GET)
	public String admin_question_content() {
		return "/question/admin_question_content";
	}
	
	/**
	 * ������ ���ֹ������� ����
	 * @return
	 */
	@RequestMapping(value="/admin_question_write.do",method=RequestMethod.GET)
	public String admin_question_write() {
		return "/question/admin_question_write";
	}
	
	/**
	 * ������ ���ֹ������� ����
	 * @return
	 */
	@RequestMapping(value="/admin_question_delete.do",method=RequestMethod.GET)
	public String admin_question_delete() {
		return "/question/admin_question_delete";
	}
	
	/**
	 * ������ ���ֹ������� ����
	 * @return
	 */
	@RequestMapping(value="/admin_question_update.do",method=RequestMethod.GET)
	public String admin_question_update() {
		return "/question/admin_question_update";
	}
	
	/**
	 * ���ֹ������� ����Ʈ
	 * @return
	 */
	@RequestMapping(value="/question_list.do",method=RequestMethod.GET)
	public String question_list() {
		return "/question/question_list";
	}
	
	
	/**
	 * ���ֹ������� ����
	 * @return
	 */
	@RequestMapping(value="/question_content.do" , method=RequestMethod.GET)
	public String question_content() {
		
		return "/question/question_content";
	}
}
