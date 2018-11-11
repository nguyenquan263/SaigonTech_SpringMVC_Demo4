package vn.edu.saigontech.SpringMVCDemo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "USERNAME", nullable = false)
	private String userName;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "ROLE", nullable = false)
	private String role;
	@Column(name = "STATUS", nullable = false)
	private int status;
	public User(int id, String userName, String password, String role, int status) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	public User(String userName, String password, String role, int status) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + ", status="
				+ status + "]";
	}
	
	
	

}
