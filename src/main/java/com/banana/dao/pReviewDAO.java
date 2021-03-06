package com.banana.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.ChatVO;
import com.banana.vo.ReviewVO;

public class pReviewDAO extends DBConn{

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private static String namespace ="mapper.preview";
	
	// 리뷰 등록
	public int reviewInsert(ReviewVO vo) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("mid", vo.getMid());
		param.put("pid", vo.getPid());
		int b=0;
		int a = sqlSession.update(namespace+".insertbuyid" ,param);
		if(a !=0) {
			if(vo.getParam().equals("구매자리뷰")) {
				b= sqlSession.insert(namespace+".buyreviewinsert" , vo);
			}else {
				b= sqlSession.insert(namespace+".sellreviewinsert" , vo);
			}
			
		}
		
		return b;
	}
	// 리스트 불러오기
	public ArrayList<ReviewVO> getReviewList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getlist" , mid);
		return (ArrayList<ReviewVO>)list ; 
	}
	// 판매자 리뷰
	public ArrayList<ReviewVO> getSellReviewList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getselllist" ,mid);
		return (ArrayList<ReviewVO>)list ; 
	}
	// 구매자 리뷰
	public ArrayList<ReviewVO> getBuyReviewList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getbuylist" , mid);
		return (ArrayList<ReviewVO>)list ; 
	}
	// 내가 쓴 리뷰
	public ArrayList<ReviewVO> getMyReviewList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getmyreviewlist" , mid);
		return (ArrayList<ReviewVO>)list ; 
	}
	// 리뷰 수정 페이지
	public ReviewVO getMyReview(String rid) {
		return sqlSession.selectOne(namespace+".getmyreview" , rid);
		
	}

	/*
	 * public productVO getmid(String pid) { return
	 * sqlSession.selectOne(namespace+".getmid" ,pid);
	 * 
	 * }
	 */
	// 리뷰 수정
	public int updateBuyMyReview(ReviewVO vo) {
		return sqlSession.update(namespace+".updatebuymyreview" , vo);
	}
	public int updateSellMyReview(ReviewVO vo) {
		return sqlSession.update(namespace+".updatesellmyreview" , vo);
	}
	
	// 리뷰 삭제
	public int deleteBuyMyReview(String rid) {
		return sqlSession.delete(namespace+".deletebuymyreview" , rid);
	}
	public int deleteSellMyReview(String rid) {
		return sqlSession.delete(namespace+".deletesellmyreview" , rid);
	}
	
	
	//매너점수 불러오기
	public ArrayList<ReviewVO> getGradeList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace + ".gradelist" , mid);
		return (ArrayList<ReviewVO>)list;
	}
	
	//리뷰작성시 buy_mid 불러오기
	public ArrayList<ChatVO> getBuyMidList(String pid){
		ArrayList<ChatVO> list = new ArrayList<ChatVO>();
		try {
			//String sql = "select distinct nickname, buy_mid from banana_chat where pid=? ";
			String sql = "select distinct m.nickname, c.buy_mid from banana_chat c, banana_member m\r\n"
							+ "where c.buy_mid = m.mid and  c.pid=? " ;
			getPreparedStatement(sql);
			pstmt.setString(1, pid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ChatVO vo = new ChatVO();
				vo.setNickname(rs.getString(1));
				vo.setBuy_mid(rs.getString(2));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
		//List<ChatVO> list =sqlSession.selectList(namespace + ".buymidlist" , pid);
		//return (ArrayList<ChatVO>)list;
	}
	
}
