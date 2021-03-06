package com.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.BananaMemberDAO;
import com.banana.dao.dongneDAO;



import com.banana.vo.DongneCommentVO;
import com.banana.vo.BananaMemberVO;

import com.banana.vo.dongneSubjectVO;
import com.banana.vo.dongneVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



@Service("dongneService")
public class DongneServiceImpl implements BananaService{

	@Autowired
	private dongneDAO dongneDAO;
	
	@Autowired
	private BananaMemberDAO bananaMemberDAO;
	
	public String mypageUpdateProc(Object vo) {
		String result = "";
		
		BananaMemberVO bvo = (BananaMemberVO) vo;
		if(bvo.getFile1().getSize() != 0) {
			UUID uuid = UUID.randomUUID();
			bvo.setMfile(bvo.getFile1().getOriginalFilename());
			bvo.setMsfile(uuid+"_"+bvo.getFile1().getOriginalFilename());
		}
		
		boolean update_result = bananaMemberDAO.memberUpdate(bvo);
		if(update_result) {
			File file = new File(bvo.getSavepath()+bvo.getMsfile());
			try {
				bvo.getFile1().transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			result = "redirect:/mypage_update.do?mid="+bvo.getMid();
			result = "redirect:/mypage_update.do";
		} else {
			result = "errorPage";
		}
		return result;
	}
	
	public ModelAndView getMemberInfoUpdate(String mid) {
		ModelAndView mv = new ModelAndView();
		BananaMemberVO vo = bananaMemberDAO.getMember(mid);
		mv.addObject("vo",vo);
		mv.setViewName("mypage/mypage_update");
		
		return mv;
	}
	
	public ModelAndView getMemberInfo(String mid) {
		ModelAndView mv = new ModelAndView();
		BananaMemberVO vo = bananaMemberDAO.getMember(mid);
		mv.addObject("vo",vo);
		mv.setViewName("mypage/mypage");
		
		return mv;
	}
	
	public String insertcomment(String bid , String comment ,String mid) {
		ArrayList<DongneCommentVO> list = dongneDAO.insertcomment(bid , comment , mid);
		JsonArray jarray = new JsonArray();
		JsonObject jobj = new JsonObject();
		Gson gson = new Gson();
		String str="";
		for(DongneCommentVO vo : list) {
			int date = Integer.parseInt(vo.getBrdate());
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else  {
				str="방금";
			}
				
			vo.setBrdate(str);
		}
		for(DongneCommentVO vo : list) {
			JsonObject jdata = new JsonObject();
			jdata.addProperty("brid", vo.getBrid());
			jdata.addProperty("bid", vo.getBid());
			jdata.addProperty("mid", vo.getMid());
			jdata.addProperty("bcomment", vo.getBcomment());
			jdata.addProperty("brdate", vo.getBrdate());
			jdata.addProperty("nickname", vo.getNickname());
			jdata.addProperty("maddr", vo.getMaddr());
			jdata.addProperty("rno", vo.getRno());
			
			
			jarray.add(jdata);
		}
		
		jobj.add("jlist", jarray);
		
		return gson.toJson(jobj);
	}
	public String getSubjectListReview(String bid) {
		ArrayList<DongneCommentVO> list = dongneDAO.getSubReview(bid);
		JsonArray jarray = new JsonArray();
		JsonObject jobj = new JsonObject();
		Gson gson = new Gson();
		String str="";
		for(DongneCommentVO vo : list) {
			int date = Integer.parseInt(vo.getBrdate());
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else  {
				str="방금";
			}
				
			vo.setBrdate(str);
		}
		for(DongneCommentVO vo : list) {
			JsonObject jdata = new JsonObject();
			jdata.addProperty("brid", vo.getBrid());
			jdata.addProperty("bid", vo.getBid());
			jdata.addProperty("mid", vo.getMid());
			jdata.addProperty("bcomment", vo.getBcomment());
			jdata.addProperty("brdate", vo.getBrdate());
			jdata.addProperty("nickname", vo.getNickname());
			jdata.addProperty("maddr", vo.getMaddr());
			jdata.addProperty("rno", vo.getRno());
			
			
			jarray.add(jdata);
		}
		
		jobj.add("jlist", jarray);
		
		return gson.toJson(jobj);
	}
	
	public String deleteSubjectProc(String bsid) {
		boolean result = dongneDAO.deleteSubjectProc(bsid);
		return String.valueOf(result);
	}
	
	public ModelAndView getSubjectListContent( String bstitle ,String mid) {
		ModelAndView mv = new ModelAndView();
		
		int count = dongneDAO.getreviewCount(bstitle);
		dongneSubjectVO svo = dongneDAO.getSubjectContent(bstitle);
		ArrayList<dongneVO> list  = dongneDAO.getSubjectList(bstitle ,mid);
		String str ="";
		for(dongneVO vo : list) {
			int date = Integer.parseInt(vo.getBdate());
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else  {
				str="방금";
			}
				
			vo.setBdate(str);
		}
		
		mv.addObject("count", count);
		mv.addObject("mid", mid);
		mv.addObject("list", list);
		mv.addObject("vo", svo);
		mv.setViewName("mypage/mypage_subjectContent");
		return mv;
	}
	
	public ModelAndView getSubjectList2() {
		ModelAndView mv = new ModelAndView();
		ArrayList<dongneSubjectVO> list = dongneDAO.getDongneSubject();
		mv.addObject("list", list);
		mv.setViewName("mypage/mypage_subjectList2");
		return mv;
	}
	
	public ModelAndView getSubjectList() {
		ModelAndView mv = new ModelAndView();
		ArrayList<dongneSubjectVO> list = dongneDAO.getDongneSubject();
		
		mv.addObject("list", list);
		mv.setViewName("mypage/mypage_subjectList");
		return mv;
	}
	
	public ModelAndView getSubjectListAdmin() {
		ModelAndView mv = new ModelAndView();
		ArrayList<dongneSubjectVO> list = dongneDAO.getDongneSubject();
		mv.addObject("list", list);
		mv.setViewName("admin/boardSubjectManage");
		return mv;
	}
	
	public String updateSubjectProc(Object vo) {
		String result = "";
		
		dongneSubjectVO dvo = (dongneSubjectVO) vo;
		if(dvo.getFile1().getSize() != 0) {
			UUID uuid = UUID.randomUUID();
			dvo.setBsfile(dvo.getFile1().getOriginalFilename());
			dvo.setBssfile(uuid + "_" + dvo.getFile1().getOriginalFilename());
		}
		
		//DB연동
		boolean update_result = dongneDAO.updateSubjectProc(dvo);
		if(update_result) {
			File file = new File(dvo.getSavepath() + dvo.getBssfile());
			
			try {
				dvo.getFile1().transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = "redirect:/boardSubjectManage.do";
		} else {
			result = "errorPage";
		}
		
		return result;
	}

	public ModelAndView updateSubject(String bsid) {
		ModelAndView mv = new ModelAndView();
		dongneSubjectVO vo = dongneDAO.getSubjectContent(bsid);
		mv.addObject("vo", vo);
		mv.setViewName("/admin/boardSubjectManageUpdate");
		return mv;
	}
	
	public String writeSubject(Object vo) {
		String result = "";
		
		dongneSubjectVO dvo = (dongneSubjectVO) vo;
		if(dvo.getFile1().getSize() != 0) {
			UUID uuid = UUID.randomUUID();
			dvo.setBsfile(dvo.getFile1().getOriginalFilename());
			dvo.setBssfile(uuid + "_" + dvo.getFile1().getOriginalFilename());
		}
		
		boolean dvo_result = dongneDAO.writeSubject(dvo);
		
		if(dvo_result) {
			File file = new File(dvo.getSavepath() + dvo.getBssfile());
			System.out.println(file);
			try {
				dvo.getFile1().transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = "redirect:/boardSubjectManage.do";
		}else {
			result = "errorPage";
		}
		
		return result;
	}
	
	public Object  insert(Object vo) {
		String result="";
		dongneVO dvo = (dongneVO)vo;
		ArrayList<String> file_list = new ArrayList<String>();
		ArrayList<String> sfile_list = new ArrayList<String>();
		
		UUID uuid = UUID.randomUUID();
		if(dvo.getList().get(0).getSize() != 0)  {
			 	
		       for (MultipartFile mf : dvo.getList()) {    	   
		           		           
		           file_list.add(mf.getOriginalFilename());
		           sfile_list.add(uuid+ "_"+ mf.getOriginalFilename());
 
		       }
		      
		       dvo.setBfile(String.join(",", file_list));
		       dvo.setBsfile(String.join(",", sfile_list));
		}
		
		
		int  dao_result = dongneDAO.insertBoard(dvo);
		if(dao_result != 0) {
			try {
				 for (MultipartFile mf : dvo.getList()) { 
					File file = new File(dvo.getSavepath()+ uuid+ "_"+ mf.getOriginalFilename());
					mf.transferTo(file);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			result= "redirect:/dongneLife.do";
		}else
			result ="errorPage";
		
		return result;
	}
	
	public Object  getList() {
		ModelAndView mv = new ModelAndView();
		ArrayList<dongneVO> list =dongneDAO.getBoardList();
		
		String str ="";
		for(dongneVO vo : list) {
			int date = Integer.parseInt(vo.getBdate());
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else  {
				str="방금";
			}
				
			vo.setBdate(str);
		}
		
		
		mv.addObject("list",list);
		mv.setViewName("/dongneLife/dongneLife");
		
		return mv;
	}
	
	
	
	public Object getContent(Object bid, String mid) {
		ModelAndView mv = new ModelAndView();
		int result = dongneDAO.likeResult(mid, (String)bid);
		dongneVO vo = dongneDAO.getBoardContent((String)bid);
		if(vo.getBsfile() != null) {
			String[] sfile_list =vo.getBsfile().split(",");
	
			mv.addObject("sfile_list", sfile_list);
		}
		
		String str ="";	
		int date = Integer.parseInt(vo.getBdate());
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else  {
				str="방금";
			}
				
			vo.setBdate(str);
		
		
		mv.addObject("result",result);
		mv.addObject("vo", vo);
		mv.setViewName("/dongneLife/dongneLife_content");
		return mv;
	}
	
	public Object getUpdateContent(Object bid) {
		ModelAndView mv = new ModelAndView();
		
		dongneVO vo = dongneDAO.getBoardContent((String)bid);
		int count =0;
		if(vo.getBfile() != null) {
			String[] sfile_list =vo.getBsfile().split(",");
			for(int i=0; i<sfile_list.length; i++) {
				count++;
			}
		}
		mv.addObject("count", count);	
		mv.addObject("vo", vo);
		mv.setViewName("/dongneLife/dongneLife_update");
		return mv;
	}
	
	
	public Object  update(Object vo) {
		
		
		ModelAndView mv = new ModelAndView();
		int dao_result =0;
		ArrayList<String> file_list = new ArrayList<String>();
		ArrayList<String> sfile_list = new ArrayList<String>();
		dongneVO dvo = (dongneVO)vo;
		
		UUID uuid = UUID.randomUUID();
		
		if(dvo.getList().get(0).getSize() != 0) {
		
		       for (MultipartFile mf : dvo.getList()) {    	   
		    	   
		    		 file_list.add(mf.getOriginalFilename());
		    		 sfile_list.add(uuid+ "_"+ mf.getOriginalFilename());
		    	  
	          }
		      
		       dvo.setBfile(String.join(",", file_list));
		       dvo.setBsfile(String.join(",", sfile_list));
		       
		      dao_result = dongneDAO.boardUpdate((dongneVO)vo);
		       
		       if(dao_result !=0) {
		       try {
					 for (MultipartFile mf : dvo.getList()) { 									    		   
							 File file = new File(dvo.getSavepath()+ uuid+ "_"+ mf.getOriginalFilename());
							 mf.transferTo(file);		    	   				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		       }		       
		}else if(dvo.getCancel_img().equals("cancel")) {
			 dvo.setBfile("");
		     dvo.setBsfile("");
		     dao_result = dongneDAO.boardUpdate((dongneVO)vo);
		}else {
			dao_result = dongneDAO.boardUpdate((dongneVO)vo);
		
		}
			
		
		
		if(dao_result !=0) {			
			mv.setViewName("redirect:/dongneLife.do");
		}
		
		return mv;
	}
	public Object delete(Object bid) {
		int result = dongneDAO.boardDelete((String)bid);
		String str="";
		if(result!=0) {
			str="redirect:/dongneLife.do";
		}
		return str;
	}
	
	/** 좋아요 **/
	 public ModelAndView product_like(String mid, String bid) {
		 ModelAndView mv = new ModelAndView();
		 boolean result = dongneDAO.getPickContent(mid,bid); 
			
			if(result) {
				//좋아요 버튼 잘 반영
				ArrayList<dongneVO> list = dongneDAO.getLikelist(mid); 
				//list객체의 데이터를 JSON 객체로 변환작업 필요 ---> JSON 라이브러리 존재(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				for(dongneVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("btitle", vo.getBtitle()); 
					jobj.addProperty("nickname", vo.getBtitle()); 
					jobj.addProperty("maddr", vo.getMaddr());
					jobj.addProperty("btopic", vo.getBtopic());
					jobj.addProperty("bfile", vo.getBfile());
					jobj.addProperty("bsfile", vo.getBsfile());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("bid", vo.getBid());
					
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java객체
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
	 }
	 
	 /** 좋아요 취소 **/
	 public String product_unlike(String mid, String bid) {
		 boolean result = dongneDAO.getDeleteContent(mid,bid); 
		 /*ModelAndView mv = new ModelAndView();
			
			if(result) {
				//좋아요 버튼 잘 반영
				ArrayList<dongneVO> list = dongneDAO.getLikelist(mid); 
				//list객체의 데이터를 JSON 객체로 변환작업 필요 ---> JSON 라이브러리 존재(gson)
				JsonArray jarray = new JsonArray();
				JsonObject jdata = new JsonObject();
				Gson gson = new Gson();
				
				for(dongneVO vo : list){
					JsonObject jobj = new JsonObject();
					jobj.addProperty("btitle", vo.getBtitle()); 
					jobj.addProperty("nickname", vo.getBtitle()); 
					jobj.addProperty("maddr", vo.getMaddr());
					jobj.addProperty("btopic", vo.getBtopic());
					jobj.addProperty("bfile", vo.getBfile());
					jobj.addProperty("bsfile", vo.getBsfile());
					jobj.addProperty("mid", vo.getMid());
					jobj.addProperty("bid", vo.getBid());
					
					jarray.add(jobj);
				}
				jdata.add("jlist", jarray);		//java객체
				
				mv.setViewName(gson.toJson(jdata));
				
			}
			return mv;
			*/
		 return String.valueOf(result);
	 }
	 
	public ModelAndView getMyPost(String mid) {
		ModelAndView mv = new ModelAndView();
		ArrayList<dongneVO> list = dongneDAO.getMyPost(mid);
		ArrayList<DongneCommentVO> clist = dongneDAO.getMyComment(mid);
		
		 countDate(list,clist); 
		 
		 
		mv.addObject("list", list);
		mv.addObject("clist", clist);
		mv.setViewName("mypage/mypage_mypost");
		return mv;
		
	}
	
	
	
	public void countDate(ArrayList<dongneVO> list ,ArrayList<DongneCommentVO> clist) {
		
		String str="";
		int date=0;
		for(dongneVO vo : list) {		
			date = Integer.parseInt(vo.getBdate());
			str = count(date);
			vo.setBdate(str);
		}
		for(DongneCommentVO vo : clist) {
			
			date = Integer.parseInt(vo.getBrdate());
			str = count(date);
			vo.setBrdate(str);
		}
		
		
		
	}
		
	public String count(int date) {
		String str ="";
		if(60>date && date>0) {
			str = date +"분";
		}else if(1440 > date && date>60) {
			str = date/60 +"시간";
		}else if (1440<date) {
			str= date/60/24 + "일";
		}else {
			str="방금";
		}
		
		return str;
		}
	
	
	public String subjectBoardlike(String bid ,String mid) {
		return String.valueOf(dongneDAO.subjectBoardlike(bid,mid));
	}
	
	public String subjectBoardlikecancel(String bid ,String mid) {
		return String.valueOf(dongneDAO.subjectBoardlikecancel(bid,mid));
	}
	
	public String insertAddr(String addr , String mid) {	
		return String.valueOf(bananaMemberDAO.insertAddr(addr, mid));
	}
	
}
