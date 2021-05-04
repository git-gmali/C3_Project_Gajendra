import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RestaurantServiceTest {

  RestaurantService service = new RestaurantService();
  Restaurant restaurant;
  // REFACTOR ALL THE REPEATED LINES OF CODE

  @BeforeEach
  public void setup() {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);
  }

  // >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
      throws restaurantNotFoundException {
    restaurant = service.findRestaurantByName("Amelie's cafe");
    assertNotNull(restaurant);
  }

  // You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and
  // Version control: Optional content
  @Test
  public void searching_for_non_existing_restaurant_should_throw_exception()
      throws restaurantNotFoundException {
    assertThrows(restaurantNotFoundException.class,
        () -> service.findRestaurantByName("Any restaurant"));


  }
  // <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>



  // >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1()
      throws restaurantNotFoundException {
    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.removeRestaurant("Amelie's cafe");
    assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
  }

  @Test
  public void removing_restaurant_that_does_not_exist_should_throw_exception()
      throws restaurantNotFoundException {
    assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
  }

  @Test
  public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
    int initialNumberOfRestaurants = service.getRestaurants().size();
    service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"),
        LocalTime.parse("23:00:00"));
    assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
  }
  // <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>


  // TDD
  // When user selects the menu items, calculate the price of the order and display it to user
  // One of the inputs shall be the restaurant name to get the correct price related to selected
  // items
  // The other input shall be the selected menu items
  // Based on the restaurant availability and selected items price, calculate the total price of the
  // order and return the double value.

  @Test
  public void calculate_price_shoulld_be_388_for_selected_menu_items_as_sweet_corn_soup_and_vegetable_lasagne_from_amelies_cafe_restaurant() {
    String aRestaurantName = "Amelie's cafe";
    ArrayList<String> selectedMenuItems = new ArrayList<String>();
    selectedMenuItems.add("Sweet corn soup");
    selectedMenuItems.add("Vegetable lasagne");

    Double totalPrice =
        service.calculateTotalPriceForSelectedMenuItems(aRestaurantName, selectedMenuItems);
    assertEquals(388, totalPrice);
  }

  @Test
  public void calculate_price_shoulld_be_0_if_selected_menu_items_are_0() {
    String aRestaurantName = "Amelie's cafe";
    ArrayList<String> selectedMenuItems = new ArrayList<String>();

    Double totalPrice =
        service.calculateTotalPriceForSelectedMenuItems(aRestaurantName, selectedMenuItems);
    assertEquals(0, totalPrice);
  }

}
