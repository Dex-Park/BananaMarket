package com.enroll.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.BananaShopDAO;
import com.banana.dao.BananaShopReviewDAO;
import com.banana.vo.BananaShopVO;
import com.banana.vo.LikeVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service("shopService")
public class EnrollstoreServiceImpl implements EnrollService {
	
	@Autowired
	private BananaShopDAO shopDAO;
	
	@Autowired
	private BananaShopReviewDAO shopReviewDAO;

	@Override
	public Object getList() {
		ModelAndView mv = new ModelAndView();
		ArrayList<BananaShopVO> list = shopDAO.getShopList();
		
		mv.addObject("list",list);
		mv.setViewName("/myNeighborhood/neighborhood");
		
		return mv;
	}

	@Override
	public Object insert(Object vo) {
		ModelAndView mv = new ModelAndView();

		BananaShopVO svo = (BananaShopVO)vo;
		if(svo.getFile1().getSize() != 0) {
			UUID uuid = UUID.randomUUID();	//�ߺ������� ���� ��� --> bsfile
			svo.setSmain_img(svo.getFile1().getOriginalFilename());
			svo.setSmain_simg(uuid + "_" + svo.getFile1().getOriginalFilename());
		}
		
		boolean dao_result = shopDAO.insertShop(svo);
		
		if(dao_result) {
			//3. ���� ���� --> upload ������ ����(������ġ)
			//String path1 = request.getSession().getServletContext().getRealPath("/");
			//String path2 = "\\resources\\upload\\";
			File file = new File(svo.getSavepath1()+svo.getSmain_simg());
			try {
				svo.getFile1().transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mv.setViewName("redirect:/neighborhood.do");
		}else
			mv.setViewName("errorPage");
		
		return mv;
	}

	@Override
	public Object getContent(Object sid) {
		ModelAndView mv = new ModelAndView();
		int result = shopDAO.likeResult("whtjdrnr010", (String)sid);
		mv.addObject("result",result);
		BananaShopVO svo = shopDAO.getShopContent((String)sid);
		svo.getSintro().replace("\r\n", "<br>");
		int review_count = shopReviewDAO.getShopReviewCount((String)sid);
		
		mv.addObject("review_count", review_count);
		mv.addObject("vo", svo);
		mv.setViewName("/myNeighborhood/neighborhoodStore");
		
		return mv;
	}

	@Override
	public Object update(Object vo) {
		ModelAndView mv = new ModelAndView();
		boolean result = false;

		BananaShopVO svo = (BananaShopVO)vo;
		
		if(svo.getFile1().getSize() != 0) {
			UUID uuid = UUID.randomUUID();	
			svo.setSmain_img(svo.getFile1().getOriginalFilename());
			svo.setSmain_simg(uuid + "_" + svo.getFile1().getOriginalFilename());
			
			result = shopDAO.shopUpdate(svo);
			
			if(result) {
				//3. ���� ���� --> upload ������ ����(������ġ)
				//String path1 = request.getSession().getServletContext().getRealPath("/");
				//String path2 = "\\resources\\upload\\";
				File file = new File(svo.getSavepath1()+svo.getSmain_simg());
				try {
					svo.getFile1().transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			//���� ���� ����
			result = shopDAO.shopUpdateNofile(svo);
		}
		
		if(result) {
			mv.setViewName("redirect:/neighborhood.do");
		}else
			mv.setViewName("errorPage");
		
		return mv;
	}

	@Override
	public Object getUpdateContent(Object sid) {
		ModelAndView mv = new ModelAndView();
		BananaShopVO svo = shopDAO.getShopContent((String)sid);
		
		mv.addObject("vo", svo);
		mv.addObject("sid", sid);
		mv.setViewName("/enrollstore/updateStore");
		
		return mv;
	}

	@Override
	public Object delete(Object sid) {
		boolean result = shopDAO.shopDelete((String)sid);
		
		String str="";
		if(result) {
			str="redirect:/neighborhood.do";
		}
		return str;
	}

	@Override
	public Object getSelectList(String sid) {
		// TODO Auto-generated method stub
		return null;
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
	
	/** ���ƿ� **/
	 public ModelAndView product_like(String mid, String sid) {
		 ModelAndView mv = new ModelAndView();
		 boolean result = shopDAO.getPickContent(mid,sid); 
			
			if(result) {
				//���ƿ� ��ư �� �ݿ�
				ArrayList<BananaShopVO> list = shopDAO.getLikelist(mid); 
				//list��ü�� �����͸� JSON ��ü�� ��ȯ�۾� �ʿ� ---> JSON ���̺귯�� ����(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				
				for(BananaShopVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("sname", vo.getSname()); 
					jobj.addProperty("skinds", vo.getSkinds()); 
					jobj.addProperty("saddr", vo.getSaddr());
					jobj.addProperty("sph", vo.getSph());
					jobj.addProperty("smain_img", vo.getSmain_img());
					jobj.addProperty("smain_simg", vo.getSmain_simg());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("sid", vo.getSid());
					
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java��ü
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
	 }
	 
	 /** ���ƿ� ��� **/
	 public ModelAndView product_unlike(String mid, String sid) {
		 ModelAndView mv = new ModelAndView();
		 boolean result = shopDAO.getDeleteContent(mid,sid); 
			
			if(result) {
				//���ƿ� ��ư �� �ݿ�
				ArrayList<BananaShopVO> list = shopDAO.getLikelist(mid); 
				//list��ü�� �����͸� JSON ��ü�� ��ȯ�۾� �ʿ� ---> JSON ���̺귯�� ����(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				
				for(BananaShopVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("sname", vo.getSname()); 
					jobj.addProperty("skinds", vo.getSkinds()); 
					jobj.addProperty("saddr", vo.getSaddr());
					jobj.addProperty("sph", vo.getSph());
					jobj.addProperty("smain_img", vo.getSmain_img());
					jobj.addProperty("smain_simg", vo.getSmain_simg());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("sid", vo.getSid());
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java��ü
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
	 }
}
