package tripplanner.tripplanner.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = -3427787029195454928L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  @Size(min = 2, max = 30)
  @Field
  @Column(name = "username")
  private String userName;

  @NotNull
  @Size(min = 10, max = 50)
  @Field
  @Column(name = "email")
  private String email;

  @NotNull
  @Size(min = 3)
  @Field
  @Column(name = "password")
  private String password;

  @OneToOne private Profile cores_profile;

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
