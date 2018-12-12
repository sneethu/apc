package com.ingg.appaunthentication.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingg.appaunthentication.model.JwtUser;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

	
	@PostMapping({"/","/index"})
	public String showMeetingsBoard(){
		return "Meetings Board is displayed";
	}
	
}
