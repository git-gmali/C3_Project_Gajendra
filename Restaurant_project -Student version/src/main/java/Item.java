public class Item {
  private String name;
  private int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name + ":" + price + "\n";
  }

  // Added the get price method with double return type as mostly the price is double type
  public Double getPrice() {
    return Double.valueOf(price);
  }
}
