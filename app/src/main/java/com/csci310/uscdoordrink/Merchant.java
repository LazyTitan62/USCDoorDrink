package com.csci310.uscdoordrink;

import java.io.Serializable;
import java.util.ArrayList;

public class Merchant extends User implements Serializable {

    private Float storeLocLatitude;
    private Float storeLocLongitude;
    private ArrayList<Item> menu;
    private ArrayList<Order> merchantOrderHistories;

    public Merchant(String name, String pw, Float latitude, Float longitude){
        super(name, pw);
        menu = new ArrayList<Item>();
        merchantOrderHistories = new ArrayList<Order>();
        storeLocLatitude = latitude;
        storeLocLongitude = longitude;
    }

    public Float getStoreLocLatitude() {
        return storeLocLatitude;
    }

    public void setStoreLocLatitude(Float storeLocLatitude) {
        this.storeLocLatitude = storeLocLatitude;
    }

    public Float getStoreLocLongitude() {
        return storeLocLongitude;
    }

    public void setStoreLocLongitude(Float storeLocLongitude) {
        this.storeLocLongitude = storeLocLongitude;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Item> menu) {
        this.menu = menu;
    }

    public void addMenuItem(Item item) {
        menu.add(item);
    }

    public ArrayList<Order> getMerchantOrderHistories() {
        return merchantOrderHistories;
    }

    public void addMerchantOrder(Order order) {
        merchantOrderHistories.add(order);
    }

}
