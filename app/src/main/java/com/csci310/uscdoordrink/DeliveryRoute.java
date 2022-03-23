package com.csci310.uscdoordrink;

class DeliveryRoute {
    private String startLoc;
    private String destination;
    private String orderPlacedTime;
    private String deliveryTime;

    public DeliveryRoute(String startLoc, String destination, String orderPlacedTime, String deliveryTime) {
        this.startLoc = startLoc;
        this.destination = destination;
        this.orderPlacedTime = orderPlacedTime;
        this.deliveryTime = deliveryTime;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(String orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
