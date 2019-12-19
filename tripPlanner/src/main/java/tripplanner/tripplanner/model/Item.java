package tripplanner.tripplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    @Column (name = "yelp_id")
    private String id;
    private String alias;
    private String name;
    private String price;
    private String image_url;
    private boolean is_closed;
    private String url;
    private int review_count;
    private double rating;
    private String phone;
    private String display_phone;
    private double distance;
    private Coordinate coordinates;

    @Column (nullable = false, updatable = false)
    @Temporal (TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column (nullable = false, updatable = false)
    @Temporal (TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


//    @ManyToMany(cascade = {CascadeType.PERSIST})
//    private List<Category> categories;

    @ManyToMany (cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable (
        name = "item_category",
        joinColumns = @JoinColumn(name ="item_id"),
        inverseJoinColumns = @JoinColumn (name = "category_id"))
    private Set<Category> categories;


    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn (name = "location_id")
    private Location location;
    
    @ManyToOne
    @JsonIgnore
    private Plan plan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_closed() {
		return is_closed;
	}

	public void setIs_closed(boolean is_closed) {
		this.is_closed = is_closed;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_closed=" + is_closed +
                ", url='" + url + '\'' +
                ", review_count=" + review_count +
                ", rating=" + rating +
                ", phone='" + phone + '\'' +
                ", display_phone='" + display_phone + '\'' +
                ", distance=" + distance +
                ", coordinates=" + coordinates +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", categories=" + categories +
                '}';
    }
}
