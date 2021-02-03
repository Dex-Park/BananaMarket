package com.spring.banana;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.DongneCommentVO;
import com.banana.vo.SessionVO;
import com.enroll.service.EnrollService;

@Controller
public class DongneCommentController {
	
	@Autowired
	private EnrollService dongneCommentService;
	
	/**
	 * ���׻�Ȱ - ��� ���� ó��
	 */
	@RequestMapping(value="/comment_delete_proc.do", method=RequestMethod.GET)
	public String comment_delete_proc(String brid) {
		return (String)dongneCommentService.delete(brid);
	}
	
	/**
	 * ���׻�Ȱ - ��� ���� ó��
	 */
	@RequestMapping(value="/comment_update_proc.do", method=RequestMethod.POST)
	public ModelAndView comment_update_proc(DongneCommentVO vo) {
		return (ModelAndView)dongneCommentService.update(vo);
	}
	
	/**
	 * �� ��ó - ��� ���� ȭ��
	 * @return
	 */
	@RequestMapping(value="/dongneLifeComment_update.do",method=RequestMethod.GET)
	public ModelAndView dongneLifeComment_update(String brid, String rno) {
		return (ModelAndView)dongneCommentService.getUpdateContent(brid, rno); 
	}
	
	/**
	 * ���׻�Ȱ - ��� ����Ʈ
	 */
	@ResponseBody
	@RequestMapping(value="/comment_list_ajax_proc.do", method=RequestMethod.GET,
					produces="text/plain;charset=UTF-8")
	public String comment_list_ajax_proc(String bid) {
		return dongneCommentService.getCommentListAjaxProc(bid);
	}
	
	/**
	 * ���׻�Ȱ - ��� �ۼ� ó��
	 */
	@RequestMapping(value="/dongneLife_review_write_proc.do", method=RequestMethod.POST)
	public ModelAndView dongneLife_review_write_proc(DongneCommentVO vo, String bid, HttpSession session) {
		vo.setBid(bid);
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		vo.setMid(svo.getMid());
		return (ModelAndView)dongneCommentService.insert(vo);
	}
	
}
