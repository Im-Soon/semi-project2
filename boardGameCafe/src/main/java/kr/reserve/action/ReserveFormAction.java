package kr.reserve.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reserve.dao.ReserveDAO;
import kr.reserve.vo.ReserveVO;

public class ReserveFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α����� ���� ���� ���
			return "redirect:/member/loginForm.do";
		}
		ReserveDAO dao = ReserveDAO.getInstance();
		ReserveVO detail = dao.getMemDetail(user_num);
		
		request.setAttribute("detail", detail);
		
		return "/WEB-INF/views/reserve/reserveForm.jsp";
	}
}
