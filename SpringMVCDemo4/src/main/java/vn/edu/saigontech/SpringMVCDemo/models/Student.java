package vn.edu.saigontech.SpringMVCDemo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "students")
public class Student implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "is_male", nullable = false)
	private boolean isMale;
	@Column(name = "image", nullable = false)
	private String image;

	@ManyToOne
	@JoinColumn(name = "spec_id", nullable = true)
	private Specialization specialization;

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String email, boolean isMale, String image) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.image = image;
	}

	public Student(int id, String firstName, String lastName, String email, boolean isMale, String image) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.image = image;
	}

	public Student(String firstName, String lastName, String email, boolean isMale, String image,
			Specialization specialization) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.image = image;
		this.specialization = specialization;
	}

	public Student(int id, String firstName, String lastName, String email, boolean isMale, String image,
			Specialization specialization) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.image = image;
		this.specialization = specialization;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", isMale=" + isMale + ", image=" + image + ", specialization=" + specialization + "]";
	}
	
	

}
