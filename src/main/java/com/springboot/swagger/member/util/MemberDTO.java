package com.springboot.swagger.member.util;
/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
public interface MemberDTO {

	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @return Long
	 */
	Long getMemberId();

	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @return String
	 */
	String getFirstName();

	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @return Integer
	 */
	Integer getAge();

	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @return String
	 */
	String getEmail();
}
