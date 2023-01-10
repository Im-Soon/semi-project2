package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import kr.order.vo.OrderVO;
import kr.util.DBUtil;

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
	//�ֹ����
	public void insertOrder(OrderVO order)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//Ŀ�ؼ�Ǯ�κ��� Ŀ�ؼ� �Ҵ�
			conn = DBUtil.getConnection();
			//SQL�� �ۼ�
			sql = "INSERT INTO order_main (order_main_num,receive_name,receive_zipcode,receive_address1,receive_address2,receive_phone,notice,mem_num) VALUES (order_main_seq.nextval,?,?,?,?,?,?,?)";
			//PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//?�� ������ ���ε�
			pstmt.setString(1, order.getReceive_name());
			pstmt.setString(2, order.getReceive_zipcode());
			pstmt.setString(3, order.getReceive_address1());
			pstmt.setString(4, order.getReceive_address2());
			pstmt.setString(5, order.getReceive_phone());
			pstmt.setString(6, order.getNotice());
			pstmt.setInt(7, order.getMem_num());
			//SQL�� ����
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//������ - ��ü �� ����/�˻��� ����
	//������ - ���/�˻��� ���
	//����� - ��ü �� ����/�˻��� ����
	//����� - ���/�˻��� ���
	//���� ��ǰ ���
	//�ֹ� ����(���� �� ��� ���� ���ͽ�Ű�� ����, �ֹ� ����� �� ���� ����)
	//������/�ֹ��� �ֹ� ��
	//�ֹ� ���� 
	
	
	
	
	
}
