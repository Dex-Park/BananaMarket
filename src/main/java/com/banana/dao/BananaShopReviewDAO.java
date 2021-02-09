package com.banana.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.vo.BananaShopAlarmVO;
import com.banana.vo.BananaShopReviewVO;

public class BananaShopReviewDAO extends DBConn {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.store";
	
	/*
	public boolean shopAlarmWrite(BananaShopAlarmVO vo) {
		boolean result = false;
		int val = sqlSession.insert(namespace+".shopAlarmWrite", vo);
		if(val != 0) result = true;
		return result;
	}
	*/
	
	public boolean shopAlarmWrite(BananaShopAlarmVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into banana_shop_alarm values(?,?,?,to_char(sysdate, 'YYYY-MM-DD AM HH12:MI'))";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getSid());
			pstmt.setString(3, vo.getSrid());
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getShopId(String sid) {
		String srid = "";
		try {
			String sql ="select srid from banana_shop_review where sid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				srid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return srid;
	}
	
	/**
	 * srid�� sid ���ϱ�
	 * @param vo
	 * @return
	 */
	public String getSid(BananaShopReviewVO vo) {
		String sid = "";
		
		try {
			String sql ="select sid from banana_shop_review where srid=?";
			
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getSrid());
				
			rs=pstmt.executeQuery();
			if(rs.next()) {
				sid = (rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return sid;
	}
	
	/**
	 * Delete - ��ü���� ����
	 * @param srid
	 * @return
	 */
	public boolean shopReviewDelete(String srid) {
		boolean result = false;
		try {
			String sql="delete from banana_shop_review where srid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, srid);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Update - ��ü���� ����
	 * @param vo
	 * @return
	 */
	public boolean shopReviewUpdate(BananaShopReviewVO vo) {
		boolean result = false;
		try {
			String sql ="UPDATE BANANA_SHOP_REVIEW SET SRCONTENT=?\r\n" + 
					"WHERE SRID=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1,vo.getSrcontent());
			pstmt.setString(2,vo.getSrid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return result;
	}
	
	/**
	 * Select - ��ü���� �� ����
	 * @param srid
	 * @return
	 */
	public BananaShopReviewVO getShopReviewDetail(String srid) {
		BananaShopReviewVO vo = new BananaShopReviewVO();
		try {
			String sql ="select *\r\n" + 
					"from (select mem.mid, mem.nickname, mem.maddr, mem.msfile, rev.srid, rev.sid, shop.sname\r\n" + 
					"from banana_member mem, banana_shop_review rev, banana_shop shop\r\n" + 
					"where mem.mid = rev.mid and rev.sid = shop.sid)\r\n" + 
					"where srid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, srid);
				
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo.setMid(rs.getString(1));
				vo.setNickname(rs.getString(2));
				vo.setMaddr(rs.getString(3));
				vo.setMsfile(rs.getString(4));
				vo.setSrid(rs.getString(5));
				vo.setSid(rs.getString(6));
				vo.setSname(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return vo;
	}
	
	/**
	 * Select - ��ü���� �� ����
	 * @param srid
	 * @return
	 */
	public BananaShopReviewVO getShopReviewContent(String srid) {
		BananaShopReviewVO vo = new BananaShopReviewVO();
		try {
			String sql ="select srid, sid , mid, srcontent, srdate\r\n"
						+ "from banana_shop_review where srid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, srid);
				
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo.setSrid(rs.getString(1));
				vo.setSid(rs.getString(2));
				vo.setMid(rs.getString(3));
				vo.setSrcontent(rs.getString(4));
				vo.setSrdate(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return vo;
	}
	
	/**
	 * Select - Ư�� ��ü���� count
	 * @param sid
	 * @return
	 */
	public int getShopReviewCount(String sid) {
		int count = 0;
		
		try {
			String sql ="select count(*) from banana_shop_review where sid=?";
			
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
				
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return count;
	}
	
	/**
	 * List - Ư�� ��ü���� ��� �ҷ�����
	 * @return
	 */
	public ArrayList<BananaShopReviewVO> getShopReviewList(String sid){
		ArrayList<BananaShopReviewVO> list = new ArrayList<BananaShopReviewVO>();
		try {
			String sql = "select *\r\n" + 
					"from (select mem.mid, mem.nickname, mem.maddr, mem.msfile, rev.srid, rev.sid, rev.srdate, shop.sname, rev.srcontent\r\n" + 
					"from banana_member mem, banana_shop_review rev, banana_shop shop\r\n" + 
					"where mem.mid = rev.mid and rev.sid = shop.sid\r\n" + 
					"order by rev.srdate desc)\r\n" + 
					"where sid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BananaShopReviewVO vo = new BananaShopReviewVO();
				vo.setMid(rs.getString(1));
				vo.setNickname(rs.getString(2));
				vo.setMaddr(rs.getString(3));
				vo.setMsfile(rs.getString(4));
				vo.setSrid(rs.getString(5));
				vo.setSid(rs.getString(6));
				vo.setSrdate(rs.getString(7));
				vo.setSname(rs.getString(8));
				vo.setSrcontent(rs.getString(9));
					
				list.add(vo);
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return list;
	}	
	
	/**
	 * List - ��ü���� ��� �ҷ�����
	 * @return
	 */
	public ArrayList<BananaShopReviewVO> getShopReviewList(){
		ArrayList<BananaShopReviewVO> list = new ArrayList<BananaShopReviewVO>();
		try {
			String sql = "select srid, sid, mid, srcontent, srdate\r\n"
						+ "from banana_shop_review order by srdate desc";
			getStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
				BananaShopReviewVO vo = new BananaShopReviewVO();
				vo.setSrid(rs.getString(1));
				vo.setSid(rs.getString(2));
				vo.setMid(rs.getString(3));
				vo.setSrcontent(rs.getString(4));
				vo.setSrdate(rs.getString(5));
					
				list.add(vo);
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return list;
	}	
	
	/**
	 * Insert - ��ü���� ���
	 * @param vo
	 * @return
	 */
	public boolean insertShopReview(BananaShopReviewVO vo) {
		boolean result = false;
		try {
			String sql ="insert into banana_shop_review "
					+ " values('sr_'||SQE_BANANA_SHOP_REVIEW.NEXTVAL,?,?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getSid());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getSrcontent());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
