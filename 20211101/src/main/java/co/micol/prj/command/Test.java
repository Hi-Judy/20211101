package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;

public class Test implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// .do 호출 테스트
		return "home.do";
	}

}
