package tripplanner.tripplanner.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -3427787029195454928L;
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="password")
	private String password;

	@OneToOne
	@JoinTable (
	        name = "user_profile",
	        joinColumns = @JoinColumn(name ="user_id"),
	        inverseJoinColumns = @JoinColumn (name = "profile_id"))
	private Profile cores_profile;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable (
	        name = "user_plan",
	        joinColumns = @JoinColumn(name ="user_id"),
	        inverseJoinColumns = @JoinColumn (name = "plan_id"))
	private Set<Plan> plans;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getCores_profile() {
		return cores_profile;
	}

	public void setCores_profile(Profile cores_profile) {
		this.cores_profile = cores_profile;
	}

	
}