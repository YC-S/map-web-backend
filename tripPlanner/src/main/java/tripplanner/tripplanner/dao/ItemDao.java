package tripplanner.tripplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tripplanner.tripplanner.model.Item;

public interface ItemDao extends JpaRepository<Item, String>  {}
