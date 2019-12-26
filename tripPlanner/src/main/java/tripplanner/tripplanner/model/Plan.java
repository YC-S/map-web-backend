package tripplanner.tripplanner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="plan")
public class Plan implements Serializable {
	
	private static final long serialVersionUID = -8430923408836109416L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="plan_title")
	private String planTitle;
	private String city;
	
//	@JoinTable (
//		        name = "plan_item",
//		        joinColumns = @JoinColumn(name ="plan_id"),
//		        inverseJoinColumns = @JoinColumn (name = "item_id"))
//	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//	@JoinColumn(name = "plan_id") // create a column in Table Item with column name "plan_id"
	@Column(name="plan_items")
	private String planItems;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
			CascadeType.DETACH, CascadeType.REFRESH}, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value="user_plan")
	private User user;
	

	public String getPlanItems() {
		return planItems;
	}

	public void setPlanItems(String planItems) {
		this.planItems = planItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
