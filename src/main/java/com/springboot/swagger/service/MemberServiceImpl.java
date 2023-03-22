package com.springboot.swagger.service;

import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.swagger.exception.CustomException;
import com.springboot.swagger.member.util.MemberDTO;
import com.springboot.swagger.model.Member;
import com.springboot.swagger.repository.MemberRepository;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id The method takes 1 parameter as in order to access the data of one particular member and remove that member
	 *
	 * @throws CustomException in case unacceptable data is sent throws  class which calls its constructor with passed string
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public void delete(Long id) throws CustomException {
		checkMemberExistsOrNot(id);
		memberRepository.deleteById(id);
	}

	/**
	 * @author Hiren Solanki
	 * 
	 * @return Member Entity
	 * 
	 * @param member the method accepts one parameter in the json format and calls service to save the given values via Object 
	 *
	 * @since 1.0
	 *
	 * @throws CustomException in case unacceptable data, which calls its constructor with passed string
	 *
	 */
	@Override
	public Member save(Member member) throws CustomException {
		checkAgeAndEmail(member.getEmail(), member.getAge());
		return memberRepository.save(member);
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id, member the method takes 2 parameters 
	 *
	 * @return	Member Entity
	 *
	 * @throws CustomException in the case of unacceptable data is sent 
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public Member update(Long id, Member member) throws CustomException {
		checkMemberExistsOrNot(id);
		Optional<Member> optional = memberRepository.findById(id);
		if (optional.isPresent()) {
			Member mem = optional.get();
			mem.setFirstName(member.getFirstName());
			mem.setAge(member.getAge());
			mem.setEmail(member.getEmail());
			memberRepository.save(mem);
		}
		return null;
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id, the method takes one parameter in order to access the data of one particular member 
	 *
	 * @return ResponseEntity<Member> 
	 *
	 * @throws CustomException in case unacceptable data is sent 
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public ResponseEntity<Member> get(Long id) throws CustomException {
		checkMemberExistsOrNot(id);
		Optional<Member> optional = memberRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return null;
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param memberId, firstName, age, email, page, size, sortField, sortOrder parameters in which none of them are mandatory 
	 *
	 * @return ResponseEntity<Page<MemberDTO>>
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public ResponseEntity<Page<MemberDTO>> searchMembers(Long memberId, String firstName, Integer age, String email,
			Pageable pageable) {
		Page<MemberDTO> member = memberRepository.searchMembers(memberId, firstName, age, email, pageable);
		return ResponseEntity.ok(member);
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param pageable which gives user access to sort the data 
	 *
	 * @return	ResponseEntity<Page<MemberDTO>>
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public ResponseEntity<Page<MemberDTO>> getMembersDetails(Pageable pageable) {
		Page<MemberDTO> member = memberRepository.getMembersDetails(pageable);
		return ResponseEntity.ok(member);
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param email, age the method take two parameters that checks if the entered email-string is valid or not via Regex expression
	 *
	 * @throws CustomException class in the case unacceptable data is sent, which calls its constructor with passed string
	 *
	 * @since 1.0
	 *
	 */
	public void checkAgeAndEmail(String email, Integer age) throws CustomException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; 
		Pattern pat = Pattern.compile(emailRegex);
		if (age <= 0) {
			throw new CustomException("Age is not valid");
		}
		if(!pat.matcher(email).matches()) {
			throw new CustomException("Email is not valid");
		}
	}

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id the method takes one parameter to check if entered memberId exists or not with the help of JPA 
	 *
	 * @throws CustomException in case unacceptable data is sent The class which calls its constructor with passed string
	 *
	 * @since 1.0
	 *
	 */
	public void checkMemberExistsOrNot(Long id) throws CustomException {
		Optional<Member> member = memberRepository.findById(id);
		if(!member.isPresent()) {
			throw new CustomException("Member with id "+ id +" does not exists");
		}
	}

}
