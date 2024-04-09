package lk.ijse.model;


public class Employee {
    private String id;
    private String name;
    private String contact;
    private String NIC;
    private String address;

    public Employee() {
    }

    public Employee(String id, String name, String contact, String NIC, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.NIC = NIC;
        this.address = address;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", NIC='" + NIC + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
