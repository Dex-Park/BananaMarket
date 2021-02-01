package com.banana.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.BananaMemberVO;
import com.banana.vo.SessionVO;

public class BananaMemberDAO extends DBConn{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.member";
	
	public ArrayList<BananaMemberVO> getMemberList() {
		List<BananaMemberVO> list = sqlSession.selectList(namespace+".memberlist");
		return (ArrayList<BananaMemberVO>)list;
	}
	
	public int getLoginChk(String mid, String pw) {
		Map<String,String> param = new HashMap<String, String>();
		param.put("mid", mid);
		param.put("pw", pw);
		return sqlSession.selectOne(namespace+".logincheck", param);
	}
	
	/**
	 * �α���
	 */
	public SessionVO getLogin(BananaMemberVO vo) {
		return sqlSession.selectOne(namespace+".login", vo);
	}
	
	/**
	 * �г��� �ߺ�üũ
	 */
	public int getNickCheck(String nickname) {
		return sqlSession.selectOne(namespace+".nickcheck", nickname);
	}
	
	/** 
	 * ���̵� �ߺ�üũ
	 */
	public int getIdCheck(String mid) {
		return sqlSession.selectOne(namespace+".idcheck",mid);
	}
	
	/**
	 * ȸ������
	 */
	public boolean InsertMember(BananaMemberVO vo) {
		boolean result = false;
		int val = sqlSession.insert(namespace+".join", vo);
		if(val != 0) result = true;
		return result;
	}
}
