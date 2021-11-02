package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class AjaxMemberList implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// Json Type Data
		MemberService memberDao = new MemberServiceImpl();
		ObjectMapper objectMapper = new ObjectMapper(); // jackson 라이브러리: list 타입을 json 타입으로 변환
		String members = null;
		try {
			members = objectMapper.writeValueAsString(memberDao.selectMemberList()); // String으로 받기
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//members = "{ \"data\" : " + members + "}";
		System.out.println(members);

		return "ajax: " + members; // 실제 결과 => ajax:[{"id":"judy", "password":"1234"}]
	}

}
