package com.enroll.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.BananaShopReviewDAO;
import com.banana.vo.BananaShopReviewVO;
import com.banana.vo.DongneCommentVO;

@Service("shopReviewService")
public class BananaShopReviewServiceImpl implements EnrollService {
	
	@Autowired
	private BananaShopReviewDAO shopReviewDAO;

	@Override
	public Object getList() {
		ModelAndView mv = new ModelAndView();
		ArrayList<BananaShopReviewVO> list = shopReviewDAO.getShopReviewList();
		
		mv.addObject("list",list);
		mv.setViewName("/myNeighborhood/neighborStoreReview_list");
		
		return mv;
	}
	
	//Ư�� ��ü �ı� ����Ʈ
	public Object getSelectList(String sid) {
		ModelAndView mv = new ModelAndView();
		ArrayList<BananaShopReviewVO> list = shopReviewDAO.getShopReviewList(sid);
		int review_count = shopReviewDAO.getShopReviewCount(sid);

		mv.addObject("review_count", review_count);
		mv.addObject("list",list);
		mv.setViewName("/myNeighborhood/neighborStoreReview_list");
		
		return mv;
	}

	@Override
	public Object insert(Object vo) {
		ModelAndView mv = new ModelAndView();

		BananaShopReviewVO srvo = (BananaShopReviewVO)vo;
		
		boolean dao_result = shopReviewDAO.insertShopReview(srvo);
		
		if(dao_result) {
			mv.setViewName("redirect:/neighborStoreReview_list.do?sid="+srvo.getSid());
		}else
			mv.setViewName("errorPage");
		
		return mv;
	}

	@Override
	public Object getContent(Object srid) {
		ModelAndView mv = new ModelAndView();
		BananaShopReviewVO srvo = shopReviewDAO.getShopReviewContent((String)srid);
		srvo.getSrcontent().replace("\r\n", "<br>");
		BananaShopReviewVO srvo1 = shopReviewDAO.getShopReviewDetail((String)srid);
		
		mv.addObject("srvo1", srvo1);
		mv.addObject("vo", srvo);
		mv.setViewName("/myNeighborhood/neighborStoreReview_content");
		
		return mv;
	}

	@Override
	public Object update(Object vo) {
		ModelAndView mv = new ModelAndView();
		boolean result = false;

		BananaShopReviewVO srvo = (BananaShopReviewVO)vo;
		String sid = shopReviewDAO.getSid(srvo);
		result = shopReviewDAO.shopReviewUpdate(srvo);

		if(result) {
			mv.setViewName("redirect:/neighborStoreReview_list.do?sid="+sid);
		}else
			mv.setViewName("errorPage");
		
		return mv;
	}

	@Override
	public Object getUpdateContent(Object srid) {
		ModelAndView mv = new ModelAndView();
		BananaShopReviewVO srvo = shopReviewDAO.getShopReviewContent((String)srid);
		
		mv.addObject("vo", srvo);
		mv.addObject("srid", srid);
		mv.setViewName("/myNeighborhood/neighborStoreReview_update");
		
		return mv;
	}

	@Override
	public Object delete(Object srid) {
		BananaShopReviewVO srvo = new BananaShopReviewVO();
		srvo.setSrid((String)srid);
		
		String sid = shopReviewDAO.getSid(srvo);
		boolean result = shopReviewDAO.shopReviewDelete((String)srid);
		
		String str="";
		if(result) {
			str="redirect:/neighborStoreReview_list.do?sid="+sid;
		}
		return str;
	}

	@Override
	public String getCommentListAjaxProc(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getUpdateContent(Object id, String rno) {
		// TODO Auto-generated method stub
		return null;
	}

}