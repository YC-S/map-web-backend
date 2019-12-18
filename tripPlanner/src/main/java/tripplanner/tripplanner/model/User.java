package tripplanner.tripplanner.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Indexed;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = -3427787029195454928L;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  private int userId;
  private String username;
  private String email;
  private String password;

  @OneToOne private Profile cores_profile;

  public int getId() {
    return userId;
  }

  public void setId(int userId) {
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
