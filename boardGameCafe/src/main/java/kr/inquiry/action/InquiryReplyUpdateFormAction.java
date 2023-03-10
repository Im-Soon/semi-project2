package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.StringUtil;

public class InquiryReplyUpdateFormAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		Integer user_num = (Integer)session.getAttribute("user_num");

		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int inqu_num = Integer.parseInt(request.getParameter("inqu_num"));

		InquiryDAO dao = InquiryDAO.getInstance();
		InquiryVO inquiry = dao.getInquiry(inqu_num);
		
		if(user_num != inquiry.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/inquiry/inquiryList.jsp";			
		}
		
		//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호가 일치
		
		//제목에 큰 따옴표가 있으면 input 태그에 데이터를 표시할 때
		//오동작이 일어나기 때문에 " -> &quot; 로 변환
		inquiry.setInqu_title(StringUtil.parseQuot(inquiry.getInqu_title()));
		
		String inquiryContent = StringUtil.useBrNoHtml(dao.getInqu_content(inqu_num));
		
		request.setAttribute("inquiry", inquiry);
		request.setAttribute("inquiryContent", inquiryContent);
		
		return "/WEB-INF/views/inquiry/inquiryReplyUpdateForm.jsp";
	}
}
