package com.csci310.uscdoordrink;

import java.io.Serializable;

public class Item implements Serializable {
    private String itemName;
    private Float itemPrice;
    private Integer itemQtyInOrder;
    private Integer itemCaffeine;

    public Item(String itemName, Float itemPrice, Integer itemCaffeine) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCaffeine = itemCaffeine;
        itemQtyInOrder = 0;
    }

    public Item(String itemName, Float itemPrice, Integer itemQty, Integer itemCaffeine) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQtyInOrder = itemQty;
        this.itemCaffeine = itemCaffeine;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQtyInOrder() {
        return itemQtyInOrder;
    }

    public void setItemQtyInOrder(Integer itemQtyInOrder) {
        this.itemQtyInOrder = itemQtyInOrder;
    }

    public Integer getItemCaffeine() {
        return itemCaffeine;
    }

    public void setItemCaffeine(Integer itemCaffeine) {
        this.itemCaffeine = itemCaffeine;
    }
}
