package lk.ijse.model.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CustomerTm {

    private String id;
    private String name;
    private String contact;
    private String NIC;
    private String address;
    private String Type;


    public CustomerTm(String id, String name, String contact, String NIC, String address, String type) {
        this.id = id;
        this.name = name;
        this.contact = contact;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", NIC='" + NIC + '\'' +
                ", address='" + address + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
