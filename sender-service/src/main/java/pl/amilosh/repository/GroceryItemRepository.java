package pl.amilosh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.amilosh.entity.GroceryItem;

import java.util.List;

public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

    @Query("{name:'?0'}")
    GroceryItem findGroceryItemByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'category' : 1}")
    List<GroceryItem> findAll(String category);

    long count();
}
