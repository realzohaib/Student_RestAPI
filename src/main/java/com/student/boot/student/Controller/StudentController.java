package com.student.boot.student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.boot.student.Serice.ServiceLayer;
import com.student.boot.student.data.StudentInfo;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private ServiceLayer studentservice;

	@GetMapping("/student")
	public ResponseEntity<List<StudentInfo>> getAll() {
		List<StudentInfo> student = studentservice.getStudent();
		return new ResponseEntity<List<StudentInfo>>(student, HttpStatus.OK);
	}

	@PostMapping("/student")
	public ResponseEntity<Object> create(@RequestBody StudentInfo studentinfo) {
		studentservice.insert(studentinfo);
		return new ResponseEntity<Object>(" student inserted successfully", HttpStatus.OK);
	}

	@DeleteMapping("/student/{studentid}")
	public ResponseEntity<Object> delete(@PathVariable("studentid") int studentid) {
		studentservice.deletestudent(studentid);
		return new ResponseEntity<Object>(" student removed successfully", HttpStatus.OK);
	}

	@PutMapping("student/{studentid}")
	public ResponseEntity<Object> update(@RequestBody StudentInfo studentinfo, @PathVariable int studentid) {
		studentservice.update(studentinfo, studentid);
		ResponseEntity<Object> re = new ResponseEntity<Object>(" student updated successfully", HttpStatus.OK);
		return re;
	}

	@GetMapping("/student/{studentid}")
	public ResponseEntity<StudentInfo> singleStudent(@PathVariable int studentid) {
		StudentInfo studentInfo = (StudentInfo) studentservice.getbyid(studentid);
		return new ResponseEntity<>(studentInfo, HttpStatus.OK);
	}

}
