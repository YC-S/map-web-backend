package tripplanner.tripplanner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Indexed;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Indexed
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = -3427787029195454928L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String userId;

  private String username;
  private String email;
  private String password;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name="profile_id")
  @JsonBackReference
  private Profile cores_profile;
  
  @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonManagedReference(value="user_plan")
  private List<Plan> plans; 

  
  public void add(Plan plan) {
	  if(plans == null) {
		  plans = new ArrayList<>();
	  }
	  plans.add(plan);
	  plan.setUser(this);
  }

  public List<Plan> getPlans() {
	return plans;
  }

  public void setPlans(List<Plan> plans) {
	this.plans = plans;
  }

  public String getUserId() {
	return userId;
  }

  public void setUserId(String userId) {
	this.userId = userId;
  }

  public String getId() {
    return userId;
  }

  public void setId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Profile getCores_profile() {
    return cores_profile;
  }

  public void setCores_profile(Profile cores_profile) {
    this.cores_profile = cores_profile;
  }
}
