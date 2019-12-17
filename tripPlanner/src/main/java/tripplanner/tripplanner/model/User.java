package tripplanner.tripplanner.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{


  private static final long serialVersionUID = -3427787029195454928L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name="emailId")
  private String emailId;

  @Column(name="password")
  private String password;

  @OneToOne
  private Profile cores_profile;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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