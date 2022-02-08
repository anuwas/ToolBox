package com.org.pack.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.pack.service.ConvertionService;

@RestController
@RequestMapping(value="/api")
public class TestController {
	
	@Autowired
	ConvertionService convertionService;
	
	@GetMapping("/test")
	public void convertionTest() throws IOException {
		File jsonfile = new File("input.json");
		convertionService.JsonToCsv(jsonfile);
	}

}
