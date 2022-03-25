package com.csci310.uscdoordrink;

public class DeliveryRoute {
    private Double startLocLatitude;
    private Double startLocLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private String orderPlacedDate;
    private String orderPlacedTime;
    private String deliveryDate;
    private String deliveryTime;

    public DeliveryRoute(Double startLocLatitude, Double startLocLongitude, Double destinationLatitude, Double destinationLongitude, String orderPlacedDate, String orderPlacedTime, String deliveryDate, String deliveryTime) {
        this.startLocLatitude = startLocLatitude;
        this.startLocLongitude = startLocLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.orderPlacedDate = orderPlacedDate;
        this.orderPlacedTime = orderPlacedTime;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
    }

    public Double getStartLocLatitude() {
        return startLocLatitude;
    }

    public void setStartLocLatitude(Double startLocLatitude) {
        this.startLocLatitude = startLocLatitude;
    }

    public Double getStartLocLongitude() {
        return startLocLongitude;
    }

    public void setStartLocLongitude(Double startLocLongitude) {
        this.startLocLongitude = startLocLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
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
