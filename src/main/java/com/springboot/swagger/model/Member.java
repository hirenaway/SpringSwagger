package com.springboot.swagger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "MEMBER_MAIN")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	private Integer age;

	private String firstName;

	private String email;

}
