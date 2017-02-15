package com.teamtreehouse.contactmgr.model;

import javax.persistence.*;

/**
 * Created by karinfernandez on 2/9/17.
 */
@Entity
public class Contact {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column
        private String firstName;

        @Column
        private String lastName;

        @Column
        private String email;

        @Column
        private Long phone;

    //Default constructor for JPA
    public Contact(){}

    public Contact(ContactBuilder builder){
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.email=builder.email;
        this.phone=builder.phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    public static class ContactBuilder{

        private String firstName;
        private String lastName;
        private String email;
        private Long phone;

        public ContactBuilder(String firstName, String lastName){

            this.firstName=firstName;
            this.lastName=lastName;
        }

        public ContactBuilder withEmail(String email){
            this.email=email;
            return this;
        }

        public ContactBuilder withPhone(Long phone){
            this.phone=phone;
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }


    }
}
