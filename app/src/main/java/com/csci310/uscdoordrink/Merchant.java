package com.csci310.uscdoordrink;

import java.util.ArrayList;

class Merchant extends User {

    private Double storeLocLatitude;
    private Double storeLocLongtitude;
    private ArrayList<Item> menu;
    private ArrayList<Order> merchantOrderHistory;
    private Integer operationStartTime;
    private Integer operationEndTime;

    public Merchant(String name, String pw, Double latitude, Double longtitude, Integer startTime, Integer endTime){
        super(name, pw);
        menu = new ArrayList<Item>();
        merchantOrderHistory = new ArrayList<Order>();
        storeLocLatitude = latitude;
        storeLocLongtitude = longtitude;
        operationStartTime = startTime;
        operationEndTime = endTime;
    }

    public String getStoreName(){
        return super.getUsrName();
    }

    public void setStoreName(String name){
        super.setUsrName(name);
    }

    public Double getStoreLocLatitude() {
        return storeLocLatitude;
    }

    public void setStoreLocLatitude(Double storeLocLatitude) {
        this.storeLocLatitude = storeLocLatitude;
    }

    public Double getStoreLocLongtitude() {
        return storeLocLongtitude;
    }

    public void setStoreLocLongtitude(Double storeLocLongtitude) {
        this.storeLocLongtitude = storeLocLongtitude;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Item> menu) {
        (this.menu).addAll(menu);
    }

    public ArrayList<Order> getMerchantOrderHistory() {
        return merchantOrderHistory;
    }

    public void addMerchantOrderHistory(Order order) {
        merchantOrderHistory.add(order);
    }

    public Integer getOperationStartTime() {
        return operationStartTime;
    }

    public void setOperationStartTime(Integer operationStartTime) {
        this.operationStartTime = operationStartTime;
    }

    public Integer getOperationEndTime() {
        return operationEndTime;
    }

    public void setOperationEndTime(Integer operationEndTime) {
        this.operationEndTime = operationEndTime;
    }

    public Boolean isOperating(Integer time){
        if (time >= operationStartTime && time <= operationEndTime){
            return true;
        }
        else{
            return false;
        }
    }
}
