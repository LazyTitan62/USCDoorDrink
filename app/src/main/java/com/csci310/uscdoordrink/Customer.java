package com.csci310.uscdoordrink;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User implements Serializable {
    private Integer caffeineIntake;
    private ArrayList<Order> customerOrderHistories;
    private ArrayList<Item> currCart;


    public Customer(String name, String pw){
        super(name, pw);
        caffeineIntake = 0;
        customerOrderHistories = new ArrayList<Order>();
        currCart = new ArrayList<Item>();
    }


    public Customer(String name, String pw, Integer caffeineIntake) {
        super(name, pw);
        this.caffeineIntake = caffeineIntake;
        customerOrderHistories = new ArrayList<Order>();
        currCart = new ArrayList<Item>();
    }

    public Integer getCaffeineIntake() {
        return caffeineIntake;
    }

    public void setCaffeineIntake(Integer caffeineIntake) {
        this.caffeineIntake = caffeineIntake;
    }

    public ArrayList<Order> getCustomerOrderHistories() {
        return customerOrderHistories;
    }

    public void addCustomerOrder(Order order) {
        customerOrderHistories.add(order);
    }

    public ArrayList<Item> getCurrCart() {
        return currCart;
    }

    public void setCurrCart(ArrayList<Item> currCart) {
        this.currCart = currCart;
    }

    public void addToCart(Item item){
        currCart.add(item);
    }

    public void emptyCart(){
        currCart.clear();
    }

    public Order checkout(Merchant merchant, Customer customer) {

        // TODO: GET CUSTOMER LOCATION
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String createdDate = dateObj.format(formatter);

        LocalTime timeObject = LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String createdTime = timeObject.format(formatterTime);

        // TODO: GET ORDER CREATED DATE AND TIME
        String deliveredDate = "";

        String deliveredTime = "";
        Float merchLocLatitude = merchant.getStoreLocLatitude();
        Float merchLocLongitude = merchant.getStoreLocLongitude();
        Float cusLocLatitude = 0f;
        Float cusLocLongitude = 0f;
        DeliveryRoute route = new DeliveryRoute(merchant.getUsrName(), customer.getUsrName(), createdDate, createdTime,deliveredDate,deliveredTime);
        Order order = new Order(currCart,route);
        merchant.addMerchantOrder(order);
        customer.addCustomerOrder(order);
        customer.setCaffeineIntake(caffeineIntake+order.getTotalCaffeine());
        emptyCart();
        return order;
    }

    public void resetCaffeineIntake(){
        caffeineIntake = 0;
    }


}
