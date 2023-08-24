package com.student.boot.student.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.student.boot.student.Controller.StudentController;
import com.student.boot.student.Serice.ServiceLayer;
import com.student.boot.student.data.StudentInfo;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private ServiceLayer studentservice;

    @InjectMocks
    private StudentController studentController;

	/*
	 * @BeforeEach public void init() { MockitoAnnotations.openMocks(this); }
	 *///this init() is used to initialise  mock object but this is old methhod instead of this, use
   // @ExtendWith(MockitoExtension.class)


    @Test
    void testGetAll() {
        
        List<StudentInfo> studentList = new ArrayList<>();
        studentList.add(new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023"));
        studentList.add(new StudentInfo(2, "haseeb", "ghar", "0000000000", "01/02/2023"));
        when(studentservice.getStudent()).thenReturn(studentList);

       
        ResponseEntity<List<StudentInfo>> response = studentController.getAll();

       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentList, response.getBody());
    }

    @Test
    void testCreate() {
        
        StudentInfo studentInfo = new StudentInfo(1, "zohaib", "ghar", "0000000000", "01/03/2023");

      
        ResponseEntity<Object> response = studentController.create(studentInfo);

     
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(" student inserted successfully", response.getBody());
    }

    @Test
    void testDelete() {
        
        int studentId = 1;

     
        ResponseEntity<Object> response = studentController.delete(studentId);

     
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(" student removed successfully", response.getBody());
    }

    @Test
    void testUpdate() {
        
        StudentInfo studentInfo = new StudentInfo(1, "zohaib", "ghar", "0000000000", "01/03/2023");
        int studentId = 1;

      
        ResponseEntity<Object> response = studentController.update(studentInfo, studentId);

      
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(" student updated successfully", response.getBody());
    }

    @Test
    void testSingleStudent() {
     
        StudentInfo studentInfo = new StudentInfo(1, "zohaib", "ghar", "000000000", "02/03/2023");
        int studentId = 1;
        when(studentservice.getbyid(studentId)).thenReturn(studentInfo);

       
        ResponseEntity<StudentInfo> response = studentController.singleStudent(studentId);

       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentInfo, response.getBody());
    }

}
