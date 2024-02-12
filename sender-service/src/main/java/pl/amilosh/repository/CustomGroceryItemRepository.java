package pl.amilosh.repository;

public interface CustomGroceryItemRepository {

    void updateGroceryItemQuantity(String name, float newQuantity);
}
