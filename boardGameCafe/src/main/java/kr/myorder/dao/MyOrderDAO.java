/*

���Ÿ�� �������� ������ ���翡 ����� ���翡 �ϱ�




package kr.myorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.myorder.vo.MyOrderVO;
import kr.myrev.vo.MyrevVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MyOrderDAO {
	//�̱��� ����
	private static MyOrderDAO instance = new MyOrderDAO();
	
	public static MyOrderDAO getInstance() {
		return instance;
	}
	private MyOrderDAO() {}
			
	//�� ���ڵ� ��
	public int getOrderCount(int mem_num, int order_main_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ� �Ҵ�
			conn = DBUtil.getConnection();
			
			//SQL�� �ۼ�
			sql = "SELECT COUNT(*) FROM order_main WHERE mem_num=? AND order_main_num=? ";
			//PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setInt(1, order_main_num);

			//SQL���� �����ϰ� ������� ResultSet ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
			
	//���� �� ��� ���
	public List<MyOrderVO> getOrderListBoard(int start, int end, int mem_num, int order_main_num)
		            								throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyOrderVO> list = null;
		String sql = null;
		
		try {
		//Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ��� �Ҵ�
		conn = DBUtil.getConnection();
		
		
		sql = "SELECT * FROM (SELECT a.*, rownum rnum, p.pro_name FROM "
			+ "(SELECT * FROM review WHERE mem_num=? ORDER BY rev_num DESC) a "
			+ "JOIN product p ON a.pro_num=p.pro_num) "
			+ "WHERE rnum>=? AND rnum<=?";
		
		sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
			+ "(SELECT * FROM inquiry WHERE mem_num=? ORDER BY inqu_num DESC)a) "
			+ "WHERE rnum>=? AND rnum<=?";
		
		//SQL�� �ۼ�
		sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
			+ "(SELECT * FROM order_main WHERE mem_num=? AND order_main_num=? ORDER BY order_main_num DESC)a) "
			+ "WHERE rnum>=? AND rnum<=?";
		
		//PreparedStatement ��ü ����
		pstmt = conn.prepareStatement(sql);
		//?�� ������ ���ε�
		pstmt.setInt(1, mem_num);
		pstmt.setInt(2, order_main_num);
		pstmt.setInt(3, start);
		pstmt.setInt(4, end);
		
		//SQL���� �����ؼ� �������� ResultSet�� ����
		rs = pstmt.executeQuery();
		list = new ArrayList<MyrevVO>();
		
		while(rs.next()) {
		MyrevVO Myrev = new MyrevVO();
		Myrev.setRev_num(rs.getInt("rev_num"));
		Myrev.setRev_content(StringUtil.useNoHtml(rs.getString("rev_content")));
		Myrev.setRev_date(rs.getDate("rev_date"));
		Myrev.setPro_name(StringUtil.useNoHtml(rs.getString("pro_name")));
		list.add(Myrev);
		}
		
		}catch(Exception e) {
		throw new Exception(e);
		}finally {
		DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}	
	
	
	
	
	
	
	
	
	
}


*/
