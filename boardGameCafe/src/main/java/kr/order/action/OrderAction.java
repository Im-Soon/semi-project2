package kr.order.action;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;

public class OrderAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�α��� Ȯ��
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//�α��� ���� ���� ����
			return "redirect:/member/loginForm.do";
		}
		
		//���۵� ������ ���ڵ� ó��
		request.setCharacterEncoding("utf-8");
		
		/* ���� ���� ����
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		if(all_total<=0) {
			request.setAttribute("notice_msg", "�������� �ֹ��� �ƴϰų� ��ǰ�� ������ �����մϴ�.");
			request.setAttribute("notice_url", request.getContextPath()+"/game/gameList.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		
		//��ٱ��Ͽ� ��� �ִ� ��ǰ ���� ��ȯ
		List<CartVO> cartList = CartDAO.getListCart(user_num);
		//�ֹ� ��ǰ�� ��ǥ ��ǰ�� ����
		String item_name;
		
		if(cartList.size()==1) {
			item_name = cartList.get(0).getProductVo().getName();
		}else {
			item_name = cartList.get(0).getProductVo().getName() + "�� " + (cartList.size()-1) + "��";
		}
		
		
		//���� ��ǰ ���� ���
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		for(CartVO cart : cartList) {
			ProductDAO productDao = ProductDAO.getINstance();
			
		}
		
		
		//���� ���� ���
		OrderVO order = new OrderVO();
		order.setOrder_main_name(item_name);
		order.setOrder_main_total(all_total);
		order.setPayment(Integer.parseInt(request.getParameter("payment")));
		order.setReceive_name(request.getParameter("receive_name"));
		order.setReceive_zipcode(request.getParameter("receive_zipcode"));
		order.setReceive_address1(request.getParameter("receive_address1"));
		order.setReceive_address2(request.getParameter("receive_address2"));
		order.setReceive_phone(request.getParameter("receive_phone"));
		order.setNotice(request.getParameter("notice"));
		order.setMem_num(user_num);
		
		OrderDAO orderDao = OrderDAO.getInstance();
		orderDao.insertOrder(order, orderDetailList);
		
		//refresh ������ ���� ����� �߰�
		response.addHeader("Refresh", "2:url=../main.main.do");
		request.setAttribute("accessMsg", "�ֹ��� �Ϸ�Ǿ����ϴ�.");
		request.setAttribute("accessUrl", request.getContextPath()+"/main/main.do");
		*/
		return "/WEB-INF/views/common/notice.jsp";
		
	}

}