package com.student.boot.student.Serice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.boot.student.Repository.StudentJpaRepository;
import com.student.boot.student.data.StudentInfo;

@Service
public class ServiceLayer {
	@Autowired
	private StudentJpaRepository service;

	public List<StudentInfo> getStudent() {
		return service.getStudent();
	}

	public StudentInfo insert(StudentInfo studentInfo) {
		return service.insert(studentInfo);
	}

	public StudentInfo getbyid(int studentid) {
		return service.getbyid(studentid);
	}

	public void deletestudent(int studentid) {
		service.deletestudent(studentid);
	}

	public void update(StudentInfo studentInfo, int studentid) {
		service.update(studentInfo, studentid);

	}

}
