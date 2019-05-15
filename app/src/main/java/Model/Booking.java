package Model;

public class Booking {

    private String name;
    private String price;
    private String userId;

    public Booking() {
    }


    public Booking(String name, String price, String userId) {
        this.name = name;
        this.price = price;
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
