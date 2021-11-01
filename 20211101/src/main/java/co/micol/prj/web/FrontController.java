package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.command.HomeCommand;
import co.micol.prj.command.Logout;
import co.micol.prj.command.MemberList;
import co.micol.prj.command.MemberLogin;
import co.micol.prj.command.MemberLoginForm;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>(); 
       
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 실제 수행할 명령들 넣어두는 곳
		map.put("/home.do", new HomeCommand()); // 첫 페이지 호출 
		map.put("/logout.do", new Logout()); // 로그아웃 처리
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); //로그인 처리
		map.put("/memberList.do", new MemberList()); //멤버 목록
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 분석 실행
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.run(request, response);
		
		//view reserve
		if(viewPage != null && !viewPage.endsWith(".do")) {
			if (viewPage.startsWith("ajax:")) { // ajax리턴
				response.setContentType("text/plain; charset=utf-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			if(viewPage.endsWith(".jsp")) {//타일즈 적용 안할때
				viewPage = "WEB-INF/views/" +viewPage;
			} else {
				viewPage = viewPage + ".tiles"; //타일즈를 적용
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
