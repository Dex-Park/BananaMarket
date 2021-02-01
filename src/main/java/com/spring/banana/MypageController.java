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

import com.banana.vo.ReviewVO;
import com.banana.vo.productVO;
import com.spring.service.DongneServiceImpl;
import com.spring.service.MypageReviewServiceImpl;
import com.spring.service.ProductService;

@Controller
public class MypageController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DongneServiceImpl dongneService;
	
	
	@Autowired

	private MypageReviewServiceImpl MypageReviewService ;
	
	
	
	/**
	 * ���������� - ���׻�Ȱ �� ����ȭ��
	 * @return
	 */
	@RequestMapping(value ="/mypage_subjectList_delete.do", method = RequestMethod.GET)
	public String mypage_subjectList_delete() {
	
		return "mypage/mypage_subjectList_delete";
	}
	
	/**
	 * ���������� - ���׻�Ȱ �� ����ȭ��
	 * @return
	 */
	@RequestMapping(value ="/mypage_subjectList_update.do", method = RequestMethod.GET)
	public String mypage_subjectList_update() {
	
		return "mypage/mypage_subjectList_update";
	}
	
	
	/**
	 * ���������� - ���׻�Ȱ ~~~������ �۾���
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList_write.do", method=RequestMethod.GET)
	public String mypage_subjectList_write() {
		return "mypage/mypage_subjectList_write";
	}
	
	/**
	 * ���������� - ���׻�Ȱ ���� Content
	 * @return
	 */
	@RequestMapping(value="/mypage_mannerGrade.do", method=RequestMethod.GET)
	public String mypage_mannerGrade() {
		return "mypage/mypage_mannerGrade";
	}
	
	@RequestMapping(value="/mypage_subjectContent.do", method=RequestMethod.GET)
	public ModelAndView mypage_subjectContent(String bsid) {
		return dongneService.getSubjectListContent(bsid);
	}
	
	/**
	 * ���������� - ���׻�Ȱ ���� ���2
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList2.do", method=RequestMethod.GET)
	public ModelAndView mypage_subjectList2() {
		return dongneService.getSubjectList2();
	}

	/**
	 * ���������� - ���׻�Ȱ ���� ���
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList.do", method=RequestMethod.GET)
	public ModelAndView mypage_subjectList() {
		return dongneService.getSubjectList();
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
	 * ���������� - �Ǹų��� - ����
	 * @return
	 */
	@RequestMapping(value ="/deletePage.do", method = RequestMethod.GET)
	public String deletePage(String pid) {	
		return (String)productService.delete(pid);
	}
	
	
	/**
	 * ���������� - �Ǹų��� - ����
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/updatePage.do", method=RequestMethod.GET)
	public ModelAndView updatePage(String pid) {
		return (ModelAndView)productService.getUpdateContent(pid);
	}
	/**
	 * ���������� - �Ǹų��� - ���� ó��
	 * @return
	 */
	@RequestMapping(value="/updatePage_proc.do", method=RequestMethod.POST)
	public ModelAndView updatePage_proc(productVO vo,  MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {
			
		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		
		return (ModelAndView)productService.update(vo);
	}
	
	/**
	 * ���������� - �Ǹų���
	 * @return
	 */
	@RequestMapping(value="/mypage_contract.do", method=RequestMethod.GET)
	public ModelAndView mypage_contract() {
		return (ModelAndView)productService.getSellList();
	}
	@RequestMapping(value="/mypage_contract_review.do", method=RequestMethod.GET)
	public String mypage_contract_review() {
		return "mypage/mypage_contract_review";
	}
	@RequestMapping(value="/contract_reivew_write_proc.do", method=RequestMethod.POST)
	public String contract_reivew_write_proc(ReviewVO vo , MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {
		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		 
		vo.setParam("�Ǹ��ڸ���");
		return (String)MypageReviewService.insert(vo); 
	}
	/**
	 * ���������� - ���ų���
	 * @return
	 */
	@RequestMapping(value="/mypage_purchased.do", method=RequestMethod.GET)
	public String mypage_purchased() {
		return "mypage/mypage_purchased";
	}
	// ���ų��� ���� ����
	@RequestMapping(value="/mypage_purchase_review.do", method=RequestMethod.GET)
	public String mypage_purchase_review() {
		return "mypage/mypage_purchase_review";
	}
	@RequestMapping(value="/purchase_reivew_write_proc.do", method=RequestMethod.POST)
	public String purchase_reivew_write_proc(ReviewVO vo , MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {
		

		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		 vo.setParam("�����ڸ���");
		return (String)MypageReviewService.insert(vo); 
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
	// ��ü ����
	@RequestMapping(value="/mypage_review.do", method=RequestMethod.GET)
	public ModelAndView mypage_review() {
		return (ModelAndView)MypageReviewService.getList();
		
	}
	
	// ������
	@RequestMapping(value="/mypage_myReview.do", method=RequestMethod.GET)
	public ModelAndView mypage_myRreview() {
		String mid ="qqq123";
		return (ModelAndView)MypageReviewService.getMyReviewList(mid);
		
	}
	// ���� ����
	@RequestMapping(value="myReview_update.do", method=RequestMethod.GET)
	public ModelAndView myReview_update_proc(String rid ) {
		
		return (ModelAndView)MypageReviewService.getUpdateContent(rid);	
	}
	
	
		
	@RequestMapping(value="update_myReview_proc.do", method=RequestMethod.POST)
	public ModelAndView update_myReview_proc(ReviewVO vo ,MultipartHttpServletRequest mtfRequest ,HttpServletRequest request) {	
		
		 List<MultipartFile> fileList = mtfRequest.getFiles("file1");
		
		 String path1 = request.getSession().getServletContext().getRealPath("/");
		 String path2 = "\\resources\\upload\\";
		
		
		 vo.setSavepath(path1+path2);
		 vo.setList(fileList);
		return (ModelAndView)MypageReviewService.update(vo);
		
	}
	// ���� ����
		@RequestMapping(value="myReview_delete_proc.do", method=RequestMethod.GET)
		public ModelAndView myReview_delete_proc(String rid) {
			
			return (ModelAndView)MypageReviewService.delete(rid); 
		
			
		}
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/mypage_review.do", method=RequestMethod.GET) public
	 * String mypage_review() { return (String)MypageReviewService.getList();
	 * 
	 * }
	 */
}
