package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;
 
public class NoticeUpdateAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int noti_num = Integer.parseInt(multi.getParameter("noti_num"));
		String noti_file = multi.getFilesystemName("noti_file");
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		//수정전 데이터 호출
		NoticeVO notice = dao.getNotice(noti_num);
		
		if(user_auth != 9) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			FileUtil.removeFile(request, noti_file);//업로드된 파일이 있으면 파일 삭제
			return "/WEB-INF/views/notice/noticeList.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setNoti_num(noti_num);
		noticeVO.setNoti_title(multi.getParameter("noti_title"));
		noticeVO.setNoti_content(multi.getParameter("noti_content"));
		noticeVO.setNoti_file(noti_file);
		
		dao.updateNotice(noticeVO);
		
		if(noti_file != null) {
			//새파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, notice.getNoti_file());
		}
		return "redirect:/notice/noticeDetail.do?noti_num=" + noti_num;
	}
}
