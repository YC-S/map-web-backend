package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, String> { }
