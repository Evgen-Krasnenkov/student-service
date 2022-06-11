package com.buzz.controller;

import com.buzz.dto.CreateStudentRequest;
import com.buzz.dto.StudentResponse;
import com.buzz.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}


	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.createStudent(createStudentRequest);
	}
	
	@GetMapping("getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}
	
}
