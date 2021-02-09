package com.banana.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.ChatContentVO;
import com.banana.vo.ChatVO;

public class ChatDAO extends DBConn{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.chat";
	
	
	/**ä�� �ϱ� **/
	public int getInsert(ChatVO vo) {
		return sqlSession.insert(namespace+".InsertChat", vo);
	}
	
	/** ä�� ����Ʈ **/
	public ArrayList<ChatVO> getChatList(){
		List <ChatVO> list = sqlSession.selectList(namespace+".ChatList");
		return (ArrayList<ChatVO>) list;
	}
	
	
	/** ä�� content **/
	public ChatContentVO getContent(String cid) {
		
		return sqlSession.selectOne(namespace+".ChatContent", cid);
	}	
}
