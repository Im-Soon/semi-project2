package kr.reserve.dao;

 
public class ReserveDAO {
	   //�̱��� ����
	   private static ReserveDAO instance = new ReserveDAO();
	   
	   public static ReserveDAO getInstance() {
	      return instance;
	   }
	   private ReserveDAO() {}
}
