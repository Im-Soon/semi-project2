package kr.room.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reserve.dao.ReserveDAO;
import kr.reserve.vo.ReserveVO;
import kr.room.dao.RoomDAO;
import kr.room.vo.RoomVO;
import kr.util.StringUtil;

public class RoomDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//�α����� ���� ���� ���
			return "redirect:/member/loginForm.do";
		}
		
		int room_num = Integer.parseInt(request.getParameter("room_num"));
		
		RoomDAO dao = RoomDAO.getInstance();
		RoomVO room = dao.getRoom(room_num);
		ReserveDAO daoo = ReserveDAO.getInstance();
		ReserveVO detail = daoo.getMemDetail(user_num);
		
		//�ٹٲ�ó��
		room.setRoom_detail(StringUtil.useBrHtml(room.getRoom_detail()));
		
		request.setAttribute("detail", detail);
		request.setAttribute("room", room);
		
		return "/WEB-INF/views/room/roomDetail.jsp";
	}

}
