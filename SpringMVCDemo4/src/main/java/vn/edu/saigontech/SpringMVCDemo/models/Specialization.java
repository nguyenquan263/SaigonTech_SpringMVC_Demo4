package vn.edu.saigontech.SpringMVCDemo.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "specializations")
public class Specialization implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "credit_number", nullable = false)
	private int creditNumber;
	
	@OneToMany(mappedBy = "specialization", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Student> students = new ArrayList<>(0);
	public Specialization(int id, String name, int creditNumber, List<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.creditNumber = creditNumber;
		this.students = students;
	}
	public Specialization(String name, int creditNumber, List<Student> students) {
		super();
		this.name = name;
		this.creditNumber = creditNumber;
		this.students = students;
	}
	public Specialization(String name, int creditNumber) {
		super();
		this.name = name;
		this.creditNumber = creditNumber;
	}
	public Specialization() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(int creditNumber) {
		this.creditNumber = creditNumber;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	

	

}
