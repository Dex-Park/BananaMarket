package com.spring.banana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.productVO;
import com.spring.service.BuylistService;
import com.spring.service.LikeServiceImpl;
import com.spring.service.ProductService;
import com.spring.service.ProductServiceImpl;

@Controller
public class MypageController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BuylistService buylistservice;
	
	@Autowired
	private LikeServiceImpl likeserviceimpl;
	
	@Autowired
	private ProductServiceImpl ProductServiceImpl;
	
	/**
	 * 마이페이지 - 동네생활 글 삭제화면
	 * @return
	 */
	@RequestMapping(value ="/mypage_subjectList_delete.do", method = RequestMethod.GET)
	public String mypage_subjectList_delete() {
	
		return "mypage/mypage_subjectList_delete";
	}
	
	/**
	 * 마이페이지 - 동네생활 글 수정화면
	 * @return
	 */
	@RequestMapping(value ="/mypage_subjectList_update.do", method = RequestMethod.GET)
	public String mypage_subjectList_update() {
	
		return "mypage/mypage_subjectList_update";
	}
	
	
	/**
	 * 마이페이지 - 동네생활 ~~~주제로 글쓰기
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList_write.do", method=RequestMethod.GET)
	public String mypage_subjectList_write() {
		return "mypage/mypage_subjectList_write";
	}
	
	/**
	 * 마이페이지 - 동네생활 주제 Content
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
	 * 마이페이지 - 동네생활 주제 목록2
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList2.do", method=RequestMethod.GET)
	public String mypage_subjectList2() {
		return "mypage/mypage_subjectList2";
	}

	/**
	 * 마이페이지 - 동네생활 주제 목록
	 * @return
	 */
	@RequestMapping(value="/mypage_subjectList.do", method=RequestMethod.GET)
	public String mypage_subjectList() {
		return "mypage/mypage_subjectList";
	}
	
	/**
	 * 마이페이지 - 내 댓글
	 * @return
	 */
	@RequestMapping(value="/mypage_mycomment.do", method=RequestMethod.GET)
	public String mypage_comment() {
		return "mypage/mypage_mycomment";
	}
	
	/**
	 * 마이페이지 - 내 게시물
	 * @return
	 */
	@RequestMapping(value="/mypage_mypost.do", method=RequestMethod.GET)
	public String mypage_mypost() {
		return "mypage/mypage_mypost";
	}
	
	/**
	 * 마이페이지 - 모아보기
	 * @return
	 */
	@RequestMapping(value="/mypage_collectView.do", method=RequestMethod.GET)
	public String mypage_collectView() {
		return "mypage/mypage_collectView";
	}
	
	/**
	 * 마이페이지 - 키워드 알림설정
	 * @return
	 */
	@RequestMapping(value="/mypage_keyword.do", method=RequestMethod.GET)
	public String mypage_keyword() {
		return "mypage/mypage_keyword";
	}
	/**
	 * 마이페이지 - 동네인증
	 * @return
	 */
	@RequestMapping(value="/mypage_locationCheck.do", method=RequestMethod.GET)
	public String mypage_locationCheck() {
		return "mypage/mypage_locationCheck";
	}
	
	/**
	 * 마이페이지 - 동네설정
	 * @return
	 */
	@RequestMapping(value="/mypage_setLocation.do", method=RequestMethod.GET)
	public String mypage_setLocation() {
		return "mypage/mypage_setLocation";
	}
	
	/**
	 * 마이페이지 - 관심목록
	 * @return
	 */
	@RequestMapping(value="/mypage_like.do", method=RequestMethod.GET)
	public ModelAndView mypage_like(String mid) {
		return (ModelAndView)likeserviceimpl.getList(mid);
	}
	
	/**
	 * 관심목록 - 중고거래 취소
	 * @param mid
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mypage_unlike.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public ModelAndView product_unlike(String mid, String pid) {
		return ProductServiceImpl.product_unlike(mid, pid);
	}
	
	/**
	 * 마이페이지 - 판매내역 - 수정
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/updatePage.do", method=RequestMethod.GET)
	public ModelAndView updatePage(String pid) {
		return (ModelAndView)productService.getUpdateContent(pid);
	}
	/**
	 * 마이페이지 - 판매내역 - 수정 처리
	 * @return
	 */
	@RequestMapping(value="/updatePage_proc.do", method=RequestMethod.POST)
	public String updatePage_proc(productVO vo) {
		return (String)productService.update(vo);
	}
	
	/**
	 * 마이페이지 - 판매내역
	 * @return
	 */
	@RequestMapping(value="/mypage_contract.do", method=RequestMethod.GET)
	public String mypage_contract() {
		return "mypage/mypage_contract";
	}
	
	/**
	 * 마이페이지 - 구매내역
	 * @return
	 */
	/*
	 * @RequestMapping(value="/mypage_purchased.do", method=RequestMethod.GET)
	 * public ModelAndView mypage_purchased() { return
	 * (ModelAndView)buylistservice.getList(); }
	 */
	
	/**
	 * 마이페이지 - 프로필 수정
	 * @return
	 */
	@RequestMapping(value="/mypage_update.do", method=RequestMethod.GET)
	public String mypage_update() {
		return "mypage/mypage_update";
	}
	
	/**
	 * 마이페이지 - 홈화면
	 * @return
	 */
	@RequestMapping(value="/mypage.do", method=RequestMethod.GET)
	public String mypage() {
		return "mypage/mypage";
	}
	
	/**
	 * 마이페이지 - 내 댓글
	 * @return
	 */
	@RequestMapping(value="/mypage_review.do", method=RequestMethod.GET)
	public String mypage_review() {
		return "mypage/mypage_review";
	}

}
