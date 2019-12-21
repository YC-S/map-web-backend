package tripplanner.tripplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.search.annotations.Field;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    private String alias;
    @Field
    private String title;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Item> items;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
