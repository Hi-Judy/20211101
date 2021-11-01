package co.micol.prj.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("select * from member")
	List<MemberVO> selectMemberList();
	
	MemberVO selectMember(MemberVO vo);
	int insertMember(MemberVO vo);
	int deleteMember(MemberVO vo);
	int updateMember(MemberVO vo);
}
