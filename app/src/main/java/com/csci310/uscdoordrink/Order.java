package com.csci310.uscdoordrink;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> orderContent = new ArrayList<Item>();
    private DeliveryRoute orderRoute;
    private Integer customerID;
    private Integer merchantID;
    private Integer totalCaffeine;
    private Double totalPrice;

    public Order(ArrayList<Item> itemsInOrder, DeliveryRoute deliveryRoute, Integer cusID, Integer merchID){
        orderContent.addAll(itemsInOrder);
        orderRoute = new DeliveryRoute(deliveryRoute.getStartLocLatitude(), deliveryRoute.getStartLocLongitude(), deliveryRoute.getDestinationLatitude(), deliveryRoute.getDestinationLongitude(), deliveryRoute.getOrderPlacedDate(), deliveryRoute.getOrderPlacedTime(), deliveryRoute.getDeliveryDate(), deliveryRoute.getDeliveryTime());
        customerID = cusID;
        merchantID = merchID;
        totalCaffeine = 0;
        totalPrice = 0.0;
        for (Item item : orderContent){
            totalCaffeine += item.getCaffeineAmount();
            totalPrice += item.getItemPrice() * item.getItemQty();
        }
    }

    public void addContent(Item item){
        orderContent.add(item);
    }

    public ArrayList<Item> getContent() {
        return orderContent;
    }

    public void addRoute(DeliveryRoute route) {
        orderRoute = new DeliveryRoute(route.getStartLocLatitude(), route.getStartLocLongitude(), route.getDestinationLatitude(), route.getDestinationLongitude(), route.getOrderPlacedDate(), route.getOrderPlacedTime(), route.getDeliveryDate(), route.getDeliveryTime());
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String contentsToString(){
        String itemsInfo = "";
        for (Item i: orderContent){
            itemsInfo += Integer.toString(i.getItemQty()) + " x " + i.getItemName() + "\n";
        }
        itemsInfo += "Total: " + totalPrice;
        return itemsInfo;
    }
}
