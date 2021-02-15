package com.banana.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.BananaShopReviewVO;

public class BananaShopReviewDAO extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.shop";
	
	/**
	 * srid�� sid ���ϱ�
	 * @param vo
	 * @return
	 */
	public String getSid(BananaShopReviewVO vo) {
		String sid = "";
		sid = sqlSession.selectOne(namespace + ".getSid", vo);
		return sid;
	}
	
	/**
	 * sid�� sname ���ϱ�
	 * @param vo
	 * @return
	 */
	public String getSname(String sid) {
		String sname = "";
		sname = sqlSession.selectOne(namespace + ".getSname", sid);
		return sname;
	}
	
	/**
	 * Delete - ��ü���� ����
	 * @param srid
	 * @return
	 */
	public boolean shopReviewDelete(String srid) {
		boolean result = false;
		int val = sqlSession.delete(namespace + ".shopReviewDelete", srid);
		if (val != 0)
			result = true;
		return result;
	}
	
	
	/**
	 * Update - ��ü���� ����
	 * @param vo
	 * @return
	 */
	public boolean shopReviewUpdate(BananaShopReviewVO vo) {
		boolean result = false;
		int value = sqlSession.update(namespace + ".shopReviewUpdate", vo);
		if (value != 0)
			result = true;
		return result;
	}
	
	/**
	 * Select - ��ü���� �� ����
	 * @param srid
	 * @return
	 */
	public BananaShopReviewVO getShopReviewDetail(String srid) {
		return sqlSession.selectOne(namespace + ".getShopReviewDetail", srid);
	}
	
	/**
	 * Select - ��ü���� �� ����
	 * @param srid
	 * @return
	 */
	public BananaShopReviewVO getShopReviewContent(String srid) {
		return sqlSession.selectOne(namespace + ".getShopReviewContent", srid);
	}
	
	/**
	 * Select - Ư�� ��ü���� count
	 * @param sid
	 * @return
	 */
	public int getShopReviewCount(String sid) {
		int count = 0;
		count = sqlSession.selectOne(namespace + ".getShopReviewCount", sid);
		return count;
	}
	
	/**
	 * List - Ư�� ��ü���� ��� �ҷ�����
	 * @return
	 */
	public ArrayList<BananaShopReviewVO> getShopReviewList(String sid){
		List<BananaShopReviewVO> list = sqlSession.selectList(namespace+".getShopReviewList2", sid);
		return (ArrayList<BananaShopReviewVO>)list;
	}	
	
	/**
	 * List - ��ü���� ��� �ҷ�����
	 * @return
	 */
	public ArrayList<BananaShopReviewVO> getShopReviewList(){
		List<BananaShopReviewVO> list = sqlSession.selectList(namespace+".getShopReviewList");
		return (ArrayList<BananaShopReviewVO>)list;
	}	
	
	/**
	 * Insert - ��ü���� ���
	 * @param vo
	 * @return
	 */
	public boolean insertShopReview(BananaShopReviewVO vo) {
		boolean result = false;
		int value = sqlSession.insert(namespace + ".insertShopReview", vo);
		if (value != 0)
			result = true;
		return result;
	}

}
