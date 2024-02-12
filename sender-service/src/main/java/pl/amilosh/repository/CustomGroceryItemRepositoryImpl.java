package pl.amilosh.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import pl.amilosh.entity.GroceryItem;

@Repository
@RequiredArgsConstructor
public class CustomGroceryItemRepositoryImpl implements CustomGroceryItemRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateGroceryItemQuantity(String name, float newQuantity) {
        var query = new Query(Criteria.where("name").is(name));
        var update = new Update();
        update.set("quantity", newQuantity);

        var result = mongoTemplate.updateFirst(query, update, GroceryItem.class);

        System.out.println(result.getModifiedCount() + " document(s) was updated");
    }
}
