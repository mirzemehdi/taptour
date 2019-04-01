package Model;

public class Tour {


    private String id;
    private String name;
    private String price;
    private String imageLink;
    private String companyId;

    public Tour() {
    }

    public Tour(String id, String name, String price, String imageLink, String companyId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
