package com.csci310.uscdoordrink;

class Item {
    private String itemName;
    private Integer itemPrice;
    private String itemDescription;
    private Integer itemQty;
    private Integer caffeineAmount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
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

    public Integer getCaffeineAmount() {
        return caffeineAmount;
    }

    public void setCaffeineAmount(Integer caffeineAmount) {
        this.caffeineAmount = caffeineAmount;
    }
}
