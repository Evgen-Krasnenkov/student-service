package com.buzz.service;

import com.buzz.dto.AddressResponse;
import com.buzz.dto.CreateStudentRequest;
import com.buzz.dto.StudentResponse;
import com.buzz.feignclient.AddressFeignClient;
import com.buzz.model.Student;
import com.buzz.repos.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StudentService {

	StudentRepository studentRepository;
	WebClient webClient;
	AddressFeignClient addressFeignClient;

	public StudentService(StudentRepository studentRepository, WebClient webClient, AddressFeignClient addressFeignClient) {
		this.studentRepository = studentRepository;
		this.webClient = webClient;
		this.addressFeignClient = addressFeignClient;
	}

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		StudentResponse studentResponse = new StudentResponse(student);
		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).orElseThrow();
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
		return studentResponse;
	}

//	public AddressResponse getAddressById(Long addressId){
//		return webClient.get().uri("/getById/" + addressId).retrieve().bodyToMono(AddressResponse.class).block();
//	}
}
