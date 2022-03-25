package com.csci310.uscdoordrink;

class Item {
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
    private Integer itemQty;
    private Double caffeineAmount;

    public Item(String itemName, Double itemPrice, String itemDescription, Integer itemQty, Double caffeineAmount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.caffeineAmount = caffeineAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public Double getCaffeineAmount() {
        return caffeineAmount;
    }

    public void setCaffeineAmount(Double caffeineAmount) {
        this.caffeineAmount = caffeineAmount;
    }
}
