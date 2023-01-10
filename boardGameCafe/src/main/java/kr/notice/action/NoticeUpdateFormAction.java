package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.StringUtil;
 
public class NoticeUpdateFormAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noti_num = Integer.parseInt(request.getParameter("noti_num"));

		NoticeDAO dao = NoticeDAO.getInstance();
		
		NoticeVO notice = dao.getNotice(noti_num);
		
		//�α����� �Ǿ� �ְ� �α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
		
		//���� ū ����ǥ�� ������ input �±׿� �����͸� ǥ���� ��
		//�������� �Ͼ�� ������ " -> &quot; �� ��ȯ
		notice.setNoti_title(StringUtil.parseQuot(notice.getNoti_title()));
		
		request.setAttribute("notice", notice);

		return "/WEB-INF/views/notice/noticeUpdateForm.jsp";
	}
}
