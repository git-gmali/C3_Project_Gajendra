import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
  private static List<Restaurant> restaurants = new ArrayList<>();

  public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

    // Loop through the restaurant list to find the restaurant name for which user has queried.

    for (Restaurant aRestaurant : restaurants) {
      if (aRestaurant.getName().equals(restaurantName)) {
        return aRestaurant;
      }
    }

    throw new restaurantNotFoundException(restaurantName);
  }


  public Restaurant addRestaurant(String name, String location, LocalTime openingTime,
      LocalTime closingTime) {
    Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
    restaurants.add(newRestaurant);
    return newRestaurant;
  }

  public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
    Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
    restaurants.remove(restaurantToBeRemoved);
    return restaurantToBeRemoved;
  }

  public List<Restaurant> getRestaurants() {
    return restaurants;
  }


  public Double calculateTotalPriceForSelectedMenuItems(String aRestaurantName,
      ArrayList<String> selectedMenuItems)
      throws restaurantNotFoundException, itemNotFoundException {

    // If the no menu item is not selected then return the price as 0
    if (selectedMenuItems.size() <= 0) {
      return 0.0;
    }

    // Find the restaurant for which the price to be calculated.
    Restaurant aRestaurant = this.findRestaurantByName(aRestaurantName);
    Double totalPrice = 0.0;

    // Iterate through the selected menu list to calculate the sum of price.
    for (String menuItemName : selectedMenuItems) {
      Double menuItemPrice = aRestaurant.getPriceOfMenuItem(menuItemName);
      totalPrice += menuItemPrice;
    }

    return totalPrice;
  }
}
