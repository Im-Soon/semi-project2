package kr.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class GameWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_id = (Integer)session.getAttribute("mem_id");
		if(mem_id == null) {//�α��� ���� ���� ���	(if�� �۵� �ȵ�)(�� ����� ����)
			return "/WEB-INF/views/game/admin_gameWrite.jsp"; //�α����ϴ� ������ ��� �����ֱ�.
		}
		
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		if(mem_auth<9) {//�����ڷ� �α������� ���� ���
			return "";  //�˷��ִ� ������ ��� �����ֱ�.
		}
		//�����ڷ� �α����� ���
		return "/WEB-INF/views/game/admin_gameWrite.jsp";
	}

}
//�����ڰ� ���� ���� �޴��� �ִ� ���� ��� ������ ������ üũ �ϰ� ���� ����ϴ� ������ ������. 