package com.ro.learn.model;

import org.joda.time.DateTime;
import javax.persistence.*;

/**
 * Created by Pankaja Gamage on 11/28/2017.
 */

@Entity
@Table(name = "CUSTOMERS")
@NamedQueries({
        @NamedQuery(name = "Customer.findByFullName", query = "SELECT o FROM Customer o WHERE o.fullName = :fullName ORDER BY o.customerId ASC"),
        @NamedQuery(name = "Customer.findByCustomerCode", query = "SELECT COUNT(o) FROM Customer o WHERE o.customerCode = :customerCode")
})

public class Customer {

    @Id
    @TableGenerator(name = "CUSTOMER_ID_GEN", table = "SEQ_STORE", pkColumnName = "SEQ_NAME", pkColumnValue = "CUSTOMER.CUSTOMER_ID", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOMER_ID_GEN")
    @Column(name = "CUSTOMER_ID", insertable = true, updatable = false, nullable = false)
    private long customerId;
    @Column(name="CUSTOMER_FULLNAME", insertable = true,updatable = true,nullable = false)
    private String fullName;
    @Column(name = "CUSTOMER_CODE", insertable = true,updatable = false,nullable = false,unique = true)
    private String customerCode;
    @Column(name = "CUSTOMER_ADDRESS",insertable = true,updatable = true,nullable = true)
    private String Address;
    @Column(name = "STATUS", insertable = true, updatable = true, nullable = false)
    private short status;
    @Column(name = "CREATED_DATE", insertable = true,updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private DateTime createdDate;


    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }
}
