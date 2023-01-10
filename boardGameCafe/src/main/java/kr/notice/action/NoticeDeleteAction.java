package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;
  
public class NoticeDeleteAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noti_num = Integer.parseInt(request.getParameter("noti_num"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO notice = dao.getNotice(noti_num);
		
		//�α����� ȸ����ȣ�� �ۼ��� ȸ����ȣ�� ��ġ
		dao.deleteNotice(noti_num);
		
		//���� ����
		FileUtil.removeFile(request, notice.getNoti_file());
		
		return "redirect:/notice/noticeList.do";
	}
}
