package com.csci310.uscdoordrink;

import java.io.Serializable;

public class DeliveryRoute implements Serializable {
    private String merchantUsrName;
    private String customerUsrName;
    private String orderPlacedDate;
    private String orderPlacedTime;
    private String deliveryDate;
    private String deliveryTime;

    public DeliveryRoute(String merchantUsrName, String customerUsrName, String orderPlacedDate, String orderPlacedTime, String deliveryDate, String deliveryTime) {
        this.merchantUsrName = merchantUsrName;
        this.customerUsrName = customerUsrName;
        this.orderPlacedDate = orderPlacedDate;
        this.orderPlacedTime = orderPlacedTime;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
    }

    public String getMerchantUsrName() {
        return merchantUsrName;
    }

    public void setMerchantUsrName(String merchantUsrName) {
        this.merchantUsrName = merchantUsrName;
    }

    public String getCustomerUsrName() {
        return customerUsrName;
    }

    public void setCustomerUsrName(String customerUsrName) {
        this.customerUsrName = customerUsrName;
    }

    public String getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate(String orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    public String getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(String orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
