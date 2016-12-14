package com.ro.learn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rohan on 2016-12-13.
 */
@Entity
@Table(name = "ORDERS")
@NamedQueries({
        @NamedQuery(name = "Order.findByProduct", query = "SELECT o FROM Order o WHERE o.productCode = :productCode ORDER BY o.orderId DESC"),
        @NamedQuery(name = "Order.findByProductCount", query = "SELECT COUNT(o) FROM Order o WHERE o.productCode = :productCode")
})
public class Order {
    @Id
    @TableGenerator(name = "ORDER_ID_GEN", table = "SEQ_STORE", pkColumnName = "SEQ_NAME", pkColumnValue = "ORDERS.ORDER_ID", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDER_ID_GEN")
    @Column(name = "ORDER_ID", insertable = true, updatable = false, nullable = false)
    private long orderId;
    @Column(name = "PRODUCT_CODE", insertable = true, updatable = true, nullable = true, length = 10)
    private String productCode;
    @Column(name = "QUANTITY", insertable = true, updatable = true, nullable = false, precision = 3)
    private double quantity;
    @Column(name = "PRICE", insertable = true, updatable = true, nullable = false, precision = 3)
    private double price;
    @Column(name = "AMOUNT", insertable = true, updatable = true, nullable = false, precision = 3)
    private double amount;
    @Column(name = "NET_AMOUNT", insertable = true, updatable = true, nullable = false, precision = 3)
    private double netAmount;
    @Column(name = "STATUS", insertable = true, updatable = true, nullable = false)
    private short status;
    @Column(name = "CREATED_DATE", insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Transient
    @JsonIgnore
    private double oldQuantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(double oldQuantity) {
        this.oldQuantity = oldQuantity;
    }
}
