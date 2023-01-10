package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;
 
public class NoticeWriteAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartRequest multi = FileUtil.createFile(request);
		
		NoticeVO notice = new NoticeVO();
		notice.setNoti_title(multi.getParameter("noti_title"));
		notice.setNoti_content(multi.getParameter("noti_content"));
		notice.setNoti_file(multi.getFilesystemName("noti_file"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.insertNotice(notice);
		
		//refresh ������ ���� ����� �߰�
		response.addHeader("Refresh", "2;url=noticeList.do");
		
		request.setAttribute("accessMsg", "���������� ��ϵǾ����ϴ�.");
		request.setAttribute("accessUrl", "list.do");
		
		return "/WEB-INF/views/notice/noticeList.jsp";
	}
}
