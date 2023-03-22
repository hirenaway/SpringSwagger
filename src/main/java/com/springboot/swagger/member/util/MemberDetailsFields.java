package com.springboot.swagger.member.util;
/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
public enum MemberDetailsFields {

	MEMBER_ID("memberId"), MEMBER_NAME("firstName"), MEMBER_AGE("age"), MEMBER_EMAIL("email");

	private String sortField;
	
	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @return String sortField
	 * 
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @author Hiren Solanki
	 * 
	 * @since 1.0
	 * 
	 * @param takes sortField String as parameter
	 * 
	 */
	MemberDetailsFields(String sortField) {
		this.sortField = sortField;
	}
}
