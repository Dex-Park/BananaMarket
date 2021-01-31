package com.banana.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.ReviewVO;

public class pReviewDAO {

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private static String namespace ="mapper.preview";
	
	// ���� ���
	public int reviewInsert(ReviewVO vo) {	
		
		if(vo.getParam().equals("�����ڸ���")) {
			return sqlSession.insert(namespace+".buyreviewinsert" , vo);
		}else {
			return sqlSession.insert(namespace+".sellreviewinsert" , vo);
		}
	}
	// ����Ʈ �ҷ�����
	public ArrayList<ReviewVO> getReviewList(){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getlist");
		return (ArrayList<ReviewVO>)list ; 
	}
	// �Ǹ��� ����
	public ArrayList<ReviewVO> getSellReviewList(){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getselllist");
		return (ArrayList<ReviewVO>)list ; 
	}
	// ������ ����
	public ArrayList<ReviewVO> getBuyReviewList(){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getbuylist");
		return (ArrayList<ReviewVO>)list ; 
	}
	// ���� �� ����
	public ArrayList<ReviewVO> getMyReviewList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace+".getmyreviewlist" , mid);
		return (ArrayList<ReviewVO>)list ; 
	}
}
