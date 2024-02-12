package pl.amilosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.entity.GroceryItem;
import pl.amilosh.repository.GroceryItemRepository;

@RestController
@RequestMapping("/grocery-item")
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemRepository groceryItemRepository;

    @GetMapping(value = "/create")
    public String create() {
        groceryItemRepository.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepository.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepository.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        groceryItemRepository.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepository.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        return "Grocery items have been created";
    }

    @GetMapping(value = "/get-all")
    public String getAll() {
        var groceryItems = groceryItemRepository.findAll();
        var result = new StringBuilder();
        groceryItems.forEach(item -> result.append(getItemDetails(item)));
        return result.toString();
    }

    @GetMapping(value = "/get-by-name/{name}")
    public String getByName(@PathVariable("name") String name) {
        var item = groceryItemRepository.findGroceryItemByName(name);
        return getItemDetails(item);
    }

    @GetMapping(value = "/get-by-category/{category}")
    public String getCategory(@PathVariable("category") String category) {
        var groceryItems = groceryItemRepository.findAll(category);
        var result = new StringBuilder();
        groceryItems.forEach(item -> result.append(getItemDetails(item)));
        return result.toString();
    }

    @GetMapping(value = "/update-category/{category}/{newCategory}")
    public String updateCategory(@PathVariable("category") String category, @PathVariable("newCategory") String newCategory) {
        var groceryItems = groceryItemRepository.findAll(category);
        groceryItems.forEach(item -> {
            item.setCategory(newCategory);
        });
        var groceryItemsUpdated = groceryItemRepository.saveAll(groceryItems);
        var result = new StringBuilder();
        groceryItemsUpdated.forEach(item -> result.append(getItemDetails(item)));
        return result.toString();
    }

    @GetMapping(value = "/delete-by-id/{id}")
    public String deleteById(@PathVariable("id") String id) {
        groceryItemRepository.deleteById(id);
        return "Item with id '" + id + "' was deleted";
    }

    @GetMapping(value = "/get-count")
    public String deleteById() {
        long count = groceryItemRepository.count();
        return "Number of grocery items: " + count;
    }

    @GetMapping(value = "/get-all")
    public String deleteAll() {
        groceryItemRepository.deleteAll();
        return "All grocery items were deleted";
    }

    public String getItemDetails(GroceryItem item) {
        return "Item Name: " + item.getName() +
            ", \nItem Quantity: " + item.getQuantity() +
            ", \nItem Category: " + item.getCategory() +
            "\n";
    }
}
