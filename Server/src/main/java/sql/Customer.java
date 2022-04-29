package sql;

import javax.xml.crypto.Data;
import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String firstName;
    private String lastName;
    private int contactNumber;
    private String email;
    private String gender;

    public Customer(String firstName, String lastName, int contactNumber, String email, String gender){ }

    public Customer(int id, String firstName, String lastName, int contactNumber, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
