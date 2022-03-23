package com.csci310.uscdoordrink;

import java.util.ArrayList;

class Order {
    private ArrayList<Item> orderContent = new ArrayList<Item>();
    private DeliveryRoute orderRoute;
    private Integer customerID;
    private Integer merchantID;
    private Integer totalCaffeine;

    public Order(ArrayList<Item> itemsInOrder, DeliveryRoute deliveryRoute, Integer cusID, Integer merchID){
        orderContent.addAll(itemsInOrder);
        orderRoute = new DeliveryRoute(deliveryRoute.getStartLoc(), deliveryRoute.getDestination(), deliveryRoute.getOrderPlacedTime(), deliveryRoute.getDeliveryTime());
        customerID = cusID;
        merchantID = merchID;
        totalCaffeine = 0;
        for (Item item : orderContent){
            totalCaffeine += item.getCaffeineAmount();
        }
    }

    public void addContent(Item item){
        orderContent.add(item);
    }

    public ArrayList<Item> getContent() {
        return orderContent;
    }

    public void addRoute(DeliveryRoute route) {
        orderRoute = new DeliveryRoute(route.getStartLoc(), route.getDestination(), route.getOrderPlacedTime(), route.getDeliveryTime());
    }

    public DeliveryRoute getRoute() {
        return orderRoute;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Integer merchantID) {
        this.merchantID = merchantID;
    }

    public Integer getTotalCaffeine() {
        return totalCaffeine;
    }

    public void setTotalCaffeine(Integer totalCaffeine) {
        this.totalCaffeine = totalCaffeine;
    }
}
