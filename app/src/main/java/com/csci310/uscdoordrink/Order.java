package com.csci310.uscdoordrink;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private ArrayList<Item> orderItems = new ArrayList<Item>();
    private DeliveryRoute deliveryRoute;
    private Integer totalCaffeine = 0;
    private Float totalPrice = 0f;

    public Order(ArrayList<Item> orderItems, DeliveryRoute deliveryRoute) {
        this.orderItems = orderItems;
        this.deliveryRoute = deliveryRoute;
        for (Item i:orderItems){
            totalCaffeine += i.getItemCaffeine() * i.getItemQtyInOrder();
            totalPrice += i.getItemPrice() * i.getItemQtyInOrder();
        }
    }

    public ArrayList<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(Item item){
        orderItems.add(item);
    }

    public DeliveryRoute getDeliveryRoute() {
        return deliveryRoute;
    }

    public void setDeliveryRoute(DeliveryRoute deliveryRoute) {
        this.deliveryRoute = deliveryRoute;
    }

    public Integer getTotalCaffeine() {
        return totalCaffeine;
    }

    public void setTotalCaffeine(Integer totalCaffeine) {
        this.totalCaffeine = totalCaffeine;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String orderToString(){
        String itemsInfo = "\n";
        for (Item i: orderItems){
            itemsInfo += Integer.toString(i.getItemQtyInOrder()) + " x " + i.getItemName() + "\n";
        }
        itemsInfo += "\nTotal: " + totalPrice;
        return itemsInfo;
    }
}
