package com.csci310.uscdoordrink;

import java.util.ArrayList;

class Customer extends User {

    private String cusLoc;
    private String merchLoc;
    private Integer caffeineIntake;
    private ArrayList<Order> custOrderHistory;
    private ArrayList<Item> currCart;

    public Customer(String name, String pw){
        super(name, pw);
        caffeineIntake = 0;
        custOrderHistory = new ArrayList<Order>();
        currCart = new ArrayList<Item>();
        // TODO: FILL IN THE LOCATION OF THE CUSTOMER
        // TODO: FILL IN THE LOCATION OF THE MERCHANT
        cusLoc = "";
        merchLoc = "";
    }

    public String getCusLoc() {
        return cusLoc;
    }

    public void setCusLoc(String cusLoc) {
        this.cusLoc = cusLoc;
    }

    public Integer getCaffeineIntake() {
        return caffeineIntake;
    }

    public void setCaffeineIntake(Integer caffeineIntake) {
        this.caffeineIntake = caffeineIntake;
    }

    public ArrayList<Order> getCustOrderHistory() {
        return custOrderHistory;
    }

    public void addCustOrderHistory(Order order) {
        custOrderHistory.add(order);
    }

    public void addToCart(Item item){
        currCart.add(item);
    }

    public ArrayList<Item> getCart() {
        return currCart;
    }

    public void emptyCart(){
        currCart.clear();
    }

    public Order checkout() {
        // TODO: GET MERCHANT LOCATION
        // TODO: GET ORDER CREATED TIME
        // TODO: GET ORDER DELIVERY TIME
        String createdTime = "";
        String deliveredTime = "";
        DeliveryRoute route = new DeliveryRoute(merchLoc,cusLoc,createdTime,deliveredTime);
        // TODO: GET CUSID AND MERCHID
        Integer cusID = 0;
        Integer merchID = 0;
        Order order = new Order(currCart,route,cusID, merchID);
        // TODO: Check/Update CaffeineIntake
        emptyCart();
        return order;
    }

    public void updateCaffeineIntake (Integer amount){
        caffeineIntake += amount;
    }
}
