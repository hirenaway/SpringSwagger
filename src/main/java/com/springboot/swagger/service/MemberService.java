package com.springboot.swagger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.springboot.swagger.exception.CustomException;
import com.springboot.swagger.member.util.MemberDTO;
import com.springboot.swagger.model.Member;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
public interface MemberService {

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
	void delete(Long id) throws CustomException;

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
	Member save(Member member) throws CustomException;

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id takes Long data type which is acceptable for Member Entity id which acts as a primary key in the database table
	 *
	 * @param member as the Member entity
	 *
	 * @return	Member Entity
	 *
	 * @throws CustomException in the case of unacceptable data is sent 
	 *
	 * @since 1.0
	 *
	 */
	Member update(Long id, Member member) throws CustomException;

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param id, the method takes one parameter in order to access the data of one particular member 
	 *
	 * @return ResponseEntity
	 *
	 * @throws CustomException in case unacceptable data is sent 
	 *
	 * @since 1.0
	 *
	 */
	ResponseEntity<Member> get(Long id) throws CustomException;

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param memberId takes Long data type which is acceptable for Member Entity id which acts as a primary key in the database table
	 * 
	 * @param firstName takes String data type which is acceptable for Member Entity firstName which acts as one of the column in the database table
	 * 
	 * @param age takes Integer data type which is acceptable for Member Entity age which acts as one of the column in the database table
	 * 
	 * @param email takes String data type which is acceptable for Member Entity email which acts as one of the column in the database table
	 *
	 * @param pageable 
	 *
	 * @return ResponseEntity
	 *
	 * @since 1.0
	 *
	 */
	ResponseEntity<Page<MemberDTO>> searchMembers(Long memberId, 
				String firstName, 
				Integer age, 
				String email, 
				Pageable pageable);

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param pageable which gives user access to sort the data 
	 *
	 * @return	ResponseEntity
	 *
	 * @since 1.0
	 *
	 */
	ResponseEntity<Page<MemberDTO>> getMembersDetails(Pageable pageable);
}
