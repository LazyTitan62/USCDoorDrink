package com.csci310.uscdoordrink;

class DeliveryRoute {
    private Double startLocLatitude;
    private Double startLocLongtitude;
    private Double destinationLatitude;
    private Double destinationLongtitude;
    private String orderPlacedDate;
    private String orderPlacedTime;
    private String deliveryDate;
    private String deliveryTime;

    public DeliveryRoute(Double startLocLatitude, Double startLocLongtitude, Double destinationLatitude, Double destinationLongtitude, String orderPlacedDate, String orderPlacedTime, String deliveryDate, String deliveryTime) {
        this.startLocLatitude = startLocLatitude;
        this.startLocLongtitude = startLocLongtitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongtitude = destinationLongtitude;
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

    public Double getStartLocLongtitude() {
        return startLocLongtitude;
    }

    public void setStartLocLongtitude(Double startLocLongtitude) {
        this.startLocLongtitude = startLocLongtitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongtitude() {
        return destinationLongtitude;
    }

    public void setDestinationLongtitude(Double destinationLongtitude) {
        this.destinationLongtitude = destinationLongtitude;
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
