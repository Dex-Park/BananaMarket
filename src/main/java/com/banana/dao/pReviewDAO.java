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
	// ���� ���� ������
	public ReviewVO getMyReview(String rid) {
		return sqlSession.selectOne(namespace+".getmyreview" , rid);
		
	}
	// ���� ����
	public int updateBuyMyReview(ReviewVO vo) {
		return sqlSession.update(namespace+".updatebuymyreview" , vo);
	}
	public int updateSellMyReview(ReviewVO vo) {
		return sqlSession.update(namespace+".updatesellmyreview" , vo);
	}
	
	// ���� ����
	public int deleteBuyMyReview(String rid) {
		return sqlSession.delete(namespace+".deletebuymyreview" , rid);
	}
	public int deleteSellMyReview(String rid) {
		return sqlSession.delete(namespace+".deletesellmyreview" , rid);
	}
	
	
	//�ų����� �ҷ�����
	public ArrayList<ReviewVO> getGradeList(String mid){
		List<ReviewVO> list =sqlSession.selectList(namespace + ".gradelist" , mid);
		return (ArrayList<ReviewVO>)list;
	}
	
	
}
