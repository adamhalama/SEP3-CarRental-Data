package com.SEP3.CarRentalAPI.Model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Customer
{
    private long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String licenceNumber;

    private boolean deleted = Boolean.FALSE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String firstName) {
        this.name = firstName;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }
    public void setEmail(String emailId) {
        this.email = emailId;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "licence_number", nullable = false)
    public String getLicenceNumber() {
        return licenceNumber;
    }
    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    @Column(name = "deleted")
    public boolean isDeleted()
    {
        return deleted;
    }
    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                '}';
    }
}
