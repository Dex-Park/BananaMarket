package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MypageController {
	
	/**
	 * ���������� - ���׻�Ȱ ���� Content
	 * @return
	 */
	@RequestMapping(value="/mypage_mannerGrade.do", method=RequestMethod.GET)
	public String mypage_mannerGrade() {
		return "mypage/mypage_mannerGrade";
	}
	
	@RequestMapping(value="/mypage_subjectContent.do", method=RequestMethod.GET)
	public String mypage_subjectContent() {
		return "mypage/mypage_subjectContent";
	}
	
	/**
	 * ���������� - ���׻�Ȱ ���� ���2
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList2.do", method=RequestMethod.GET)
	public String mypage_subjectList2() {
		return "mypage/mypage_subjectList2";
	}

	/**
	 * ���������� - ���׻�Ȱ ���� ���
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList.do", method=RequestMethod.GET)
	public String mypage_subjectList() {
		return "mypage/mypage_subjectList";
	}
	
	/**
	 * ���������� - �� ���
	 * @return
	 */
	@RequestMapping(value="/mypage_mycomment.do", method=RequestMethod.GET)
	public String mypage_comment() {
		return "mypage/mypage_mycomment";
	}
	
	/**
	 * ���������� - �� �Խù�
	 * @return
	 */
	@RequestMapping(value="/mypage_mypost.do", method=RequestMethod.GET)
	public String mypage_mypost() {
		return "mypage/mypage_mypost";
	}
	
	/**
	 * ���������� - ��ƺ���
	 * @return
	 */
	@RequestMapping(value="/mypage_collectView.do", method=RequestMethod.GET)
	public String mypage_collectView() {
		return "mypage/mypage_collectView";
	}
	
	/**
	 * ���������� - Ű���� �˸�����
	 * @return
	 */
	@RequestMapping(value="/mypage_keyword.do", method=RequestMethod.GET)
	public String mypage_keyword() {
		return "mypage/mypage_keyword";
	}
	/**
	 * ���������� - ��������
	 * @return
	 */
	@RequestMapping(value="/mypage_locationCheck.do", method=RequestMethod.GET)
	public String mypage_locationCheck() {
		return "mypage/mypage_locationCheck";
	}
	
	/**
	 * ���������� - ���׼���
	 * @return
	 */
	@RequestMapping(value="/mypage_setLocation.do", method=RequestMethod.GET)
	public String mypage_setLocation() {
		return "mypage/mypage_setLocation";
	}
	
	/**
	 * ���������� - ���ɸ��
	 * @return
	 */
	@RequestMapping(value="/mypage_like.do", method=RequestMethod.GET)
	public String mypage_like() {
		return "mypage/mypage_like";
	}
	
	/**
	 * ���������� - �Ǹų���
	 * @return
	 */
	@RequestMapping(value="/mypage_contract.do", method=RequestMethod.GET)
	public String mypage_contract() {
		return "mypage/mypage_contract";
	}
	
	/**
	 * ���������� - ���ų���
	 * @return
	 */
	@RequestMapping(value="/mypage_purchased.do", method=RequestMethod.GET)
	public String mypage_purchased() {
		return "mypage/mypage_purchased";
	}
	
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
	
	/**
	 * ���������� - �� ���
	 * @return
	 */
	@RequestMapping(value="/mypage_review.do", method=RequestMethod.GET)
	public String mypage_review() {
		return "mypage/mypage_review";
	}

}
