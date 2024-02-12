package pl.amilosh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("grocery_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItem {

    @Id
    private String id;
    private String name;
    private int quantity;
    private String category;
}
