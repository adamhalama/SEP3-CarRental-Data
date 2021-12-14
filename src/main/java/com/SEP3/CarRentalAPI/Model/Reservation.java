package com.SEP3.CarRentalAPI.Model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
@SQLDelete(sql = "UPDATE reservation SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Reservation
{
    private long id;
//    private long vehicleId;
//    private long customerId;
//    private long employeeId;
    private Vehicle vehicle;
    private Customer customer;
    private Employee employee;
    private int securityDeposit;
    private long dateCreated;
    private long dateStart;
    private long dateEnd;
    private int allowedKm;
    private float paymentAmount;
    private long billDate;
    private boolean isPaid;

    private boolean deleted = Boolean.FALSE;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "vehicle_id", nullable = true, updatable = true)
    public Vehicle getVehicle()
    {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_id", nullable = true, updatable = true)
    public Customer getCustomer()
    {
        return customer;
    }
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_id", nullable = true, updatable = true)
    public Employee getEmployee()
    {
        return employee;
    }
    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    /*@OneToOne(mappedBy = "reservation")
    @Column(name = "vehicle_id", nullable = false)
    public long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Column(name = "customer_id", nullable = false)
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "employee_id", nullable = true)
    public long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }*/
    
    @Column(name = "security_deposit", nullable = false)
    public int getSecurityDeposit() {
        return securityDeposit;
    }
    public void setSecurityDeposit(int securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
    
    @Column(name = "date_created", nullable = false)
    public long getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "date_start", nullable = false)
    public long getDateStart() {
        return dateStart;
    }
    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "date_end", nullable = false)
    public long getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Column(name = "allowed_km", nullable = false)
    public int getAllowedKm() {
        return allowedKm;
    }
    public void setAllowedKm(int allowedKm) {
        this.allowedKm = allowedKm;
    }

    @Column(name = "payment_amount", nullable = false)
    public float getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column(name = "bill_date", nullable = false)
    public long getBillDate() {
        return billDate;
    }
    public void setBillDate(long billDate) {
        this.billDate = billDate;
    }

    @Column(name = "isPaid", nullable = false)
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
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
        return "Reservation{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                ", employee=" + employee +
                ", securityDeposit=" + securityDeposit +
                ", dateCreated=" + dateCreated +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", allowedKm=" + allowedKm +
                ", paymentAmount=" + paymentAmount +
                ", billDate=" + billDate +
                ", isPaid=" + isPaid +
                ", deleted=" + deleted +
                '}';
    }

    /*@Override
    public String toString()
    {
        return "Reservation{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", securityDeposit=" + securityDeposit +
                ", dateCreated=" + dateCreated +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", allowedKm=" + allowedKm +
                ", paymentAmount=" + paymentAmount +
                ", billDate=" + billDate +
                ", isPaid=" + isPaid +
                '}';
    }*/


}
