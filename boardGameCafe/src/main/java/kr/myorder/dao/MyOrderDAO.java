package kr.myorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.myorder.vo.MyOrderVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MyOrderDAO {
	//싱글턴 패턴
	private static MyOrderDAO instance = new MyOrderDAO();
	
	public static MyOrderDAO getInstance() {
		return instance;
	}
	private MyOrderDAO() {}
		
	//총 레코드 수
	public int getOrderCount(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥셔풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM order_main WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);

			//SQL문을 실행하고 결과행을 ResultSet 담음
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
	
	//나의 주문 목록(최종)
	public List<MyOrderVO> getOrderListBoard(int start, int end, int mem_num)
	                                   throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyOrderVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM order_main WHERE mem_num=? ORDER BY order_main_num DESC)a) "
					+ "WHERE rnum>=? AND rnum<=?";
					
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			//SQL문을 실행해서 결과행들을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<MyOrderVO>();
			while(rs.next()) {
				MyOrderVO Myorder = new MyOrderVO();
				
				Myorder.setStatus(rs.getInt("status"));
				Myorder.setOrder_main_num(rs.getInt("order_main_num"));
				Myorder.setOrder_main_total(rs.getInt("order_main_total"));
				Myorder.setOrder_main_name(StringUtil.useNoHtml(rs.getString("order_main_name")));
				Myorder.setOrder_main_date(rs.getDate("order_main_date"));				
				
				list.add(Myorder);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}	
		
	
}


