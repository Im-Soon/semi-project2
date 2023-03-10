package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.StringUtil;

public class InquiryDetailAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int inqu_num = Integer.parseInt(request.getParameter("inqu_num"));
						
		InquiryDAO dao = InquiryDAO.getInstance();
						
		//조회수 증가
		dao.updateReadcount(inqu_num);
						
		InquiryVO inquiry = dao.getInquiry(inqu_num);
		
		//HTML 태그를 허용하지 않음
		inquiry.setInqu_title(StringUtil.useNoHtml(inquiry.getInqu_title()));
						
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		inquiry.setInqu_content(StringUtil.useBrNoHtml(inquiry.getInqu_content()));
					
		request.setAttribute("inquiry", inquiry);
					
		return "/WEB-INF/views/inquiry/inquiryDetail.jsp";
	}
}
