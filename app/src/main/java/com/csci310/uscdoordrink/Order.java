package com.csci310.uscdoordrink;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order implements Serializable {
    private ArrayList<Item> orderItems = new ArrayList<Item>();
    private DeliveryRoute deliveryRoute;
    private Integer totalCaffeine = 0;
    private Float totalPrice = 0f;
    DecimalFormat df = new DecimalFormat();

    public Order(ArrayList<Item> orderItems, DeliveryRoute deliveryRoute) {
        df.setMaximumFractionDigits(2);
        this.orderItems = orderItems;
        this.deliveryRoute = deliveryRoute;
        for (Item i:orderItems){
            totalCaffeine += i.getItemCaffeine() * i.getItemQtyInOrder();
            totalPrice += i.getItemPrice() * i.getItemQtyInOrder();
            totalPrice = Float.parseFloat(df.format(totalPrice));
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
        totalPrice += item.getItemPrice() * item.getItemQtyInOrder();
        totalCaffeine += getTotalCaffeine() * item.getItemQtyInOrder();
        totalPrice = Float.parseFloat(df.format(totalPrice));
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
        return totalPrice = Float.parseFloat(df.format(totalPrice));
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = Float.parseFloat(df.format(totalPrice));
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
