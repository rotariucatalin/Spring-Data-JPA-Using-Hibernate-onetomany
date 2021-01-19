package com.example.onetomany.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhoneNumber> phoneNumberList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(Set<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }


    public void addPhoneNumber(PhoneNumber phoneNumber) {

        if (phoneNumber != null) {

            if (phoneNumberList == null) {
                phoneNumberList = new HashSet<PhoneNumber>();
            }

            phoneNumber.setCustomer(this);
            phoneNumberList.add(phoneNumber);
        }

    }
}
