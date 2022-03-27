package com.csci310.uscdoordrink;

import java.io.Serializable;

public class DeliveryRoute implements Serializable {
    private String merchantUsrName;
    private Float merchantLocLatitude;
    private Float merchantLocLongitude;
    private String customerUsrName;
    private Float customerLatitude;
    private Float customerLongitude;
    private String orderPlacedDate;
    private String orderPlacedTime;
    private String deliveryDate;
    private String deliveryTime;

    public DeliveryRoute(String merchantUsrName, Float merchantLocLatitude, Float merchantLocLongitude, String customerUsrName, Float customerLatitude, Float customerLongitude, String orderPlacedDate, String orderPlacedTime, String deliveryDate, String deliveryTime) {
        this.merchantUsrName = merchantUsrName;
        this.merchantLocLatitude = merchantLocLatitude;
        this.merchantLocLongitude = merchantLocLongitude;
        this.customerUsrName = customerUsrName;
        this.customerLatitude = customerLatitude;
        this.customerLongitude = customerLongitude;
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

    public Float getMerchantLocLatitude() {
        return merchantLocLatitude;
    }

    public void setMerchantLocLatitude(Float merchantLocLatitude) {
        this.merchantLocLatitude = merchantLocLatitude;
    }

    public Float getMerchantLocLongitude() {
        return merchantLocLongitude;
    }

    public void setMerchantLocLongitude(Float merchantLocLongitude) {
        this.merchantLocLongitude = merchantLocLongitude;
    }

    public String getCustomerUsrName() {
        return customerUsrName;
    }

    public void setCustomerUsrName(String customerUsrName) {
        this.customerUsrName = customerUsrName;
    }

    public Float getCustomerLatitude() {
        return customerLatitude;
    }

    public void setCustomerLatitude(Float customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public Float getCustomerLongitude() {
        return customerLongitude;
    }

    public void setCustomerLongitude(Float customerLongitude) {
        this.customerLongitude = customerLongitude;
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
