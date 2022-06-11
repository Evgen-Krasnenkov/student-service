package com.buzz.service;

import com.buzz.dto.CreateStudentRequest;
import com.buzz.dto.StudentResponse;
import com.buzz.model.Student;
import com.buzz.repos.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		return new StudentResponse(student);
	}
	
	public StudentResponse getById (long id) {

		return new StudentResponse(studentRepository.findById(id).orElseThrow());
	}
}
