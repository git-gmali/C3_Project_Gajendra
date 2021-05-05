import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
  private String name;
  private String location;
  public LocalTime openingTime;
  public LocalTime closingTime;
  private List<Item> menu = new ArrayList<Item>();

  public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
    this.name = name;
    this.location = location;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
  }

  public boolean isRestaurantOpen() {

    // Get the current time
    // Check if current time is within the limits os opening time and closing time to declare
    // that the restaurant is open.
    LocalTime currentTime = this.getCurrentTime();
    long secondsRemainingtoClose = ChronoUnit.SECONDS.between(currentTime, closingTime);
    long secondsPassedOpeningTime = ChronoUnit.SECONDS.between(openingTime, currentTime);

    // System.out.println(openingTime + " " + currentTime + " " + closingTime + " "
    // + minutesRemainingtoClose + " " + minutesPassedOpeningTime);
    if (secondsPassedOpeningTime >= 0 && secondsRemainingtoClose >= 0)
      return true;

    return false;
  }

  public LocalTime getCurrentTime() {
    return LocalTime.now();
  }

  public List<Item> getMenu() {
    // Get the object of unmodifiable list to make sure the list is not modified outside.
    return Collections.unmodifiableList(menu);
  }

  private Item findItemByName(String itemName) {
    for (Item item : menu) {
      if (item.getName().equals(itemName))
        return item;
    }
    return null;
  }

  public void addToMenu(String name, int price) {
    Item newItem = new Item(name, price);
    menu.add(newItem);
  }

  public void removeFromMenu(String itemName) throws itemNotFoundException {

    Item itemToBeRemoved = findItemByName(itemName);
    if (itemToBeRemoved == null)
      throw new itemNotFoundException(itemName);

    menu.remove(itemToBeRemoved);
  }

  // TO-DO think about the approach to test this method.
  public void displayDetails() {
    System.out.println("Restaurant:" + name + "\n" + "Location:" + location + "\n" + "Opening time:"
        + openingTime + "\n" + "Closing time:" + closingTime + "\n" + "Menu:" + "\n" + getMenu());

  }

  public String getName() {
    return name;
  }

  public Double getPriceOfMenuItem(String menuItemName) throws itemNotFoundException {
    Item menuItem = findItemByName(menuItemName);

    if (null == menuItem) {
      throw new itemNotFoundException(menuItemName);
    }

    return menuItem.getPrice();
  }

}
