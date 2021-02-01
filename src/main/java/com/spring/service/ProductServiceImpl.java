package com.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.productDAO;
import com.banana.vo.LikeVO;
import com.banana.vo.productVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private productDAO productDAO;
	
	public Object insert(Object vo) {
		String result="";
		productVO pvo = (productVO)vo;
		UUID uuid = UUID.randomUUID(); 
		
			System.out.println(pvo.getFile_list().length);
			for(CommonsMultipartFile file : pvo.getFile_list()) {
				//������ �����ϸ� nfile nsfile
				//System.out.println(file.getOriginalFilename());
				if(pvo.getFile_list().length != 0 ) {
					
					pvo.setPfile(file.getOriginalFilename());
					pvo.setPsfile(uuid+"_"+file.getOriginalFilename());
				}	
				//DB���� 
				boolean dao_result = productDAO.getInsert(pvo);
			
				if(dao_result){
					//�������� --> upload ������ ����(������ġ)
					File file1 = new File(pvo.getSavepath()+pvo.getPsfile());
					
					try {	
						file.transferTo(file1);
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					result= "redirect:/popularProduct.do";
				}else {
					result ="errorPage";
				}
			}
			
			return result;
		} 
	
	
	
	 public Object getList() {
		  ModelAndView mv = new ModelAndView();
		  ArrayList<productVO> list = productDAO.getProductList();
		  
			mv.addObject("list", list);
			mv.setViewName("/popularProduct/popularProduct");
			return mv;
	  }

	 public Object getContent(Object pid) {
		 ModelAndView mv = new ModelAndView();
		 productVO vo = productDAO.getProductContent((String)pid);
			mv.addObject("vo", vo);
			mv.setViewName("/popularProduct/productContent");
			return mv;
	 }
	 

	 public Object getUpdateContent(Object pid) {
		  ModelAndView mv = new ModelAndView();
			productVO vo = productDAO.getProductContent((String)pid);
			mv.addObject("vo", vo);
			mv.setViewName("/popularProduct/updatePage");
			return mv;
		  
	  }
	 
	 
	 public Object update(Object vo) {
			ModelAndView mv = new ModelAndView();
			boolean result = productDAO.getProductUpdate((productVO)vo);
			
			if(result) {
				//mv.setViewName("redirect:/productContent.do");
				mv.setViewName("/mypage/mypage_contract");
			}
			return mv;
		}
	 
	 
	 public Object delete(Object pid) {
	 
		 return "...";
	 }
	 /** ���ƿ� **/
	 public ModelAndView product_like(String mid, String pid) {
		 ModelAndView mv = new ModelAndView();
		 boolean result = productDAO.getPickContent(mid,pid); 
			
			if(result) {
				//���ƿ� ��ư �� �ݿ�
				ArrayList<LikeVO> list = productDAO.getLikelist(mid); 
				//list��ü�� �����͸� JSON ��ü�� ��ȯ�۾� �ʿ� ---> JSON ���̺귯�� ����(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				
				for(LikeVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("ptitle", vo.getPtitle()); 
					jobj.addProperty("maddr", vo.getMaddr());
					jobj.addProperty("pprice", vo.getPprice());
					jobj.addProperty("pfile", vo.getPfile());
					jobj.addProperty("psfile", vo.getPsfile());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("pid", vo.getPid());
					
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java��ü
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
	 }
	 
	 /** ���ƿ� ��� **/
	 public ModelAndView product_unlike(String mid, String pid) {
		 ModelAndView mv = new ModelAndView();
		 boolean result = productDAO.getDeleteContent(mid,pid); 
			
			if(result) {
				//���ƿ� ��ư �� �ݿ�
				ArrayList<LikeVO> list = productDAO.getLikelist(mid); 
				//list��ü�� �����͸� JSON ��ü�� ��ȯ�۾� �ʿ� ---> JSON ���̺귯�� ����(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				
				for(LikeVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("ptitle", vo.getPtitle()); 
					jobj.addProperty("maddr", vo.getMaddr());
					jobj.addProperty("pprice", vo.getPprice());
					jobj.addProperty("pfile", vo.getPfile());
					jobj.addProperty("psfile", vo.getPsfile());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("pid", vo.getPid());
					
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java��ü
				System.out.println(jarray);
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
	 }
	 
		/*
		 * public ModelAndView likeResult(String mid, String pid) { ModelAndView mv =
		 * new ModelAndView(); int result = productDAO.likeResult(mid, pid);
		 * 
		 * mv.addObject("result",result); mv.setViewName("productContent.do?pid="+pid);
		 * 
		 * return mv; }
		 */
	 
}
