package pro.abacus.webRestProject.models;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@NotBlank(message="Name is required")
	@Size(min=2, max=30,message="Name must be at least 2 characters long")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message="Email is required")
	@Email
	@Column(name = "email")
	private String email;

	@NotBlank(message="Password is required")
	@Size(min=8, message="Password must be at least 8 characters long")
	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private int active;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {

	}

	public User(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(User users) {
		this.name = users.getName();
		this.email = users.getEmail();
		// this.active = users.getActive();
		this.roles = users.getRoles();
		this.userId = users.getUserId();
		this.password = users.getPassword();
	}

	public int getUserId() {
		return userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
