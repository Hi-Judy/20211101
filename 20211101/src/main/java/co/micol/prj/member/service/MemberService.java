package co.micol.prj.member.service;

import java.util.List;

public interface MemberService {
	
	List<MemberVO> selectMemberList();
	MemberVO selectMember(MemberVO vo);
	int insertMember(MemberVO vo);
	int deleteMember(MemberVO vo);
	int updateMember(MemberVO vo);
}
