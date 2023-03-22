package com.springboot.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.swagger.exception.CustomException;
import com.springboot.swagger.member.util.MemberDTO;
import com.springboot.swagger.member.util.MemberDetailsFields;
import com.springboot.swagger.model.Member;
import com.springboot.swagger.service.MemberService;

import io.swagger.annotations.ApiOperation;
/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("/api")
public class SpringSwaggerRestController {

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "Save Member")
	@PostMapping(value = "/savemember", consumes = MediaType.APPLICATION_JSON_VALUE)
	private Member saveMember(@RequestBody Member member) throws CustomException {
		return memberService.save(member);
	}

	@ApiOperation(value = "Search Member", response = MemberDTO.class, responseContainer = "List")
	@GetMapping(value = "/searchmembers", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Page<MemberDTO>> searchMembers(
			@RequestParam(required = false) Long memberId,
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String email,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size,
			@RequestParam(required = false, defaultValue = "MEMBER_ID") MemberDetailsFields sortField,
			@RequestParam(required = false, defaultValue = "DESC") Direction sortOrder) {
		return memberService.searchMembers(memberId, firstName, age, email,
				PageRequest.of(page, size, sortOrder, sortField.getSortField()));
	}

	@ApiOperation(value = "Members details", response = MemberDTO.class, responseContainer = "List")
	@GetMapping(value = "/viewmembers", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Page<MemberDTO>> getMembersDetails(
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size,
			@RequestParam(required = false, defaultValue = "MEMBER_ID") MemberDetailsFields sortField,
			@RequestParam(required = false, defaultValue = "DESC") Direction sortOrder) {
		return memberService.getMembersDetails(PageRequest.of(page, size, sortOrder, sortField.getSortField()));
	}

	@ApiOperation(value = "Member Information")
	@GetMapping(value = "/member/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Member> getMembersDetails(@PathVariable Long id ) throws CustomException {
		return memberService.get(id);
	}

	@ApiOperation(value = "Update Member")
	@PutMapping(value = "/updatemember/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	private Member updateMemeber(@RequestBody Member member, @PathVariable Long id) throws CustomException {
		return memberService.update(id, member);
	}

	@ApiOperation(value = "Delete Member")
	@DeleteMapping(value = "/deletemember/{id}")
	private void deleteMemeber(@PathVariable Long id) throws CustomException {
		memberService.delete(id);
	}
}
