package FitnessClub;

import java.io.Serializable;

public class Member implements Serializable {
    
    // USed to store the member's information.
    private String name;       
    private String address;    
    private String phoneNumber; 

    public Member() {
    }

    // Creates a new instance with the provided name, address, and phone number.
    public Member(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Gets the name of the member.
    public String getName() {
        return name;
    }

    // Sets the name of the member.
    public void setName(String name) {
        this.name = name;
    }

    // Gets the address of the member.
    public String getAddress() {
        return address;
    }

    // Sets the address of the member.
    public void setAddress(String address) {
        this.address = address;
    }

    // Gets the phone number of the member.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Sets the phone number of the member.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
