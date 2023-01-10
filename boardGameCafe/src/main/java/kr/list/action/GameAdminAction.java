package kr.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class GameAdminAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_id = (Integer)session.getAttribute("mem_id");
		if(mem_id == null) {//�α��� ���� ���� ���  (�� ����� ������) (if�� �� ��)
			return "/WEB-INF/views/game/admin.jsp"; //�α����ϴ� ������ ��� �����ֱ�.
		}
		
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		if(mem_auth<9) {//�����ڷ� �α������� ���� ���
			return "/WEB-INF/views/game/admin.jsp";  //�˷��ִ� ������ ��� �����ֱ�.
		}
		//�����ڷ� �α����� ���
		return "/WEB-INF/views/game/admin.jsp";
	}

}

//�����ڷ� �α����ϸ� ���ӵ�� ���̴� ��� �����ְ� ���� ���� ��ư ������ admin.jsp(���� ���)������ ������.????
