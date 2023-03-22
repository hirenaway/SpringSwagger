package com.springboot.swagger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.swagger.member.util.MemberDTO;
import com.springboot.swagger.model.Member;
/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param The method Search Member takes memberId, firstName, age, email, pageable parameters in which none of them are mandatory
	 *
	 * @return	Method returns Page With MemberDTO, The Method uses helps you with searching and sorting Member Data with various fields
	 *
	 * @since 1.0
	 *
	 */
	@Query(value = "SELECT " 
			+ " m.id AS memberId," 
			+ " m.first_name AS firstName," 
			+ " m.age AS age,"
			+ " m.email AS email" 
			+ " FROM" 
			+ " member_main m" 
			+ " WHERE"
			+ " (:memberId is null or m.id = :memberId)"
			+ " AND (coalesce(:firstName, null) is null or m.first_name like concat('%', :firstName,'%'))"
			+ " AND (coalesce(:age, null) is null or m.age like concat('%', :age,'%'))"
			+ " AND (coalesce(:email, null) is null or m.email like concat('%', :email,'%'))", nativeQuery = true)
	Page<MemberDTO> searchMembers(@Param("memberId") Long memberId, 
			@Param("firstName") String firstName,
			@Param("age") Integer age, 
			@Param("email") String email, 
			Pageable pageable);

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param The method takes pageable as parameter
	 *
	 * @return	Method returns Page With MemberDTO, The Method uses helps you with searching and sorting
	 *
	 * @since 1.0
	 *
	 */
	@Query(value = "SELECT " 
			+ " m.id AS memberId," 
			+ " m.first_name AS firstName," 
			+ " m.age AS age,"
			+ " m.email AS email" 
			+ " FROM" 
			+ " member_main m", nativeQuery = true)
	Page<MemberDTO> getMembersDetails(Pageable pageable);
}
