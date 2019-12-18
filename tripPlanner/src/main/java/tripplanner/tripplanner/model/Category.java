package tripplanner.tripplanner.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @Column (name = "category_id")
    private String alias;
    
    private String title;

    @ManyToMany(mappedBy = "categories")
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
