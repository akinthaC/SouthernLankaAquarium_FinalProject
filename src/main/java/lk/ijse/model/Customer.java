package lk.ijse.model;

public class Customer {
    private String id;
    private String name;
    private String contact;
    private String NIC;
    private String address;
    private String Type;

    public Customer() {
    }
    public Customer(String id, String name, String address, String contact, String NIC, String type) {
        this.id = id;
        this.contact = contact;
        this.name = name;
        this.NIC = NIC;
        this.address = address;
        Type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
