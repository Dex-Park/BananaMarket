package com.spring.banana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.banana.vo.BananaShopReviewVO;
import com.enroll.service.EnrollService;

@Controller
public class NeighborhoodController {
	
	@Autowired
	private EnrollService shopService;
	
	@Autowired
	private EnrollService shopReviewService;
	
	/**
	 * �� ��ó - ��ü �ı� ���� ó��
	 * @return
	 */
	@RequestMapping(value ="/neighborStoreReview_delete_proc.do", method = RequestMethod.GET)
	public String deleteStore_delete_proc(String srid) {	
		return (String)shopReviewService.delete(srid);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ���� ȭ��
	 * @return
	 */
	@RequestMapping(value ="/neighborStoreReview_delete.do", method = RequestMethod.GET)
	public ModelAndView deleteStore_delete(String srid) {	
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("srid", srid);
		mv.setViewName("/myNeighborhood/neighborStoreReview_delete");
		
		return mv;
	} 
	
	/**
	 * �� ��ó - ��ü �ı� ���� ó��
	 * @return
	 */
	@RequestMapping(value ="/neighborStoreReview_update_proc.do", method = RequestMethod.POST)
	public ModelAndView neighborStoreReview_update_proc(BananaShopReviewVO vo) {	
		return (ModelAndView)shopReviewService.update(vo);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ���� ȭ��
	 * @return
	 */
	@RequestMapping(value="/neighborStoreReview_update.do",method=RequestMethod.GET)
	public ModelAndView neighborStoreReview_update(String srid) {
		return (ModelAndView)shopReviewService.getUpdateContent(srid);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ���� ȭ��
	 */
	@RequestMapping(value="/neighborStoreReview_content.do", method=RequestMethod.GET)
	public ModelAndView neighborStoreReview_content(String srid) {
		return (ModelAndView)shopReviewService.getContent(srid);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ����Ʈ
	 * @return
	 */
	@RequestMapping(value="/neighborStoreReview_list.do" , method=RequestMethod.GET)
	public ModelAndView neighborhoodStoreReview_list(String sid) {
		return (ModelAndView)shopReviewService.getSelectList(sid);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ��� ó��
	 * @return
	 */
	@RequestMapping(value="/neighborStoreReview_write_proc.do",method=RequestMethod.POST)
	public ModelAndView neighborStoreReview_write_proc(BananaShopReviewVO vo, String sid, String mid) {
		vo.setSid(sid);
		vo.setMid(mid);
		
		return (ModelAndView)shopReviewService.insert(vo);
	}
	
	/**
	 * �� ��ó - ��ü �ı� ��� ȭ��
	 */
	@RequestMapping(value="/neighborStoreReview_write.do" , method=RequestMethod.GET)
	public ModelAndView neighborStoreReview_write(String sid, String mid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("sid", sid);
		mv.addObject("mid", mid);
		mv.setViewName("/myNeighborhood/neighborStoreReview_write");
		
		return mv;
	}
	
	/**
	 * �� ��ó - ��ü���� ȭ��
	 * @return
	 */
	@RequestMapping(value="/neighborhoodStore.do" , method=RequestMethod.GET)
	public ModelAndView neighborhoodStore(String sid) {
		return (ModelAndView)shopService.getContent(sid);
	}
	
	@RequestMapping(value="/neighborhood.do" , method=RequestMethod.GET)
	public ModelAndView neighborhood() {
		return (ModelAndView)shopService.getList();
	}
	
	@RequestMapping(value="/footer.do", method=RequestMethod.GET)
	public String index() {
		return "footer";
	}
}
