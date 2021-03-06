package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class RegisterMember implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//멤버 가입 처리
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setAuthor(request.getParameter("author"));
		
		int n = memberDao.insertMember(vo);
		String viewPage = null;
		
		if(n != 0) {
			viewPage = "memberLoginForm.do";
		}else {
			request.setAttribute("message", "회원가입이 정상적으로 처리되이 않았습니다.");
			viewPage = "member/memberError";
		}
		return viewPage;
	}

}
