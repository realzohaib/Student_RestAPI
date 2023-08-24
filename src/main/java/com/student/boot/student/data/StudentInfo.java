package com.student.boot.student.data;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Student_Id")
	private int studentid;
	private String name;
	private String address;
	@Column(name = "Contact")
	private String mobileno;
	private String date;
	
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddres(String address) {
		this.address = address;
	}
	
	public StudentInfo(int studentid, String name, String address, String mobileno, String date) {
		super();
		this.studentid = studentid;
		this.name = name;
		this.address = address;
		this.mobileno = mobileno;
		this.date = date;
	}
	
	
	
	@Override
	public String toString() {
		return "StudentInfo [studentid=" + studentid + ", name=" + name + ", address=" + address + ", mobileno="
				+ mobileno + ", date=" + date + "]";
	}
	public StudentInfo(){}

	
	  @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (!(obj instanceof StudentInfo)) {
	            return false;
	        }
	        StudentInfo other = (StudentInfo) obj;
	        return this.studentid == other.studentid
	                && Objects.equals(this.name, other.name)
	                && Objects.equals(this.address, other.address)
	                && Objects.equals(this.mobileno, other.mobileno)
	                && Objects.equals(this.date, other.date);
	    }
}
