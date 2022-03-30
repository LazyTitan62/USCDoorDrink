package com.csci310.uscdoordrink;

import com.csci310.uscdoordrink.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<Item> itemsInCart = new ArrayList<>();

    public Cart(ArrayList<Item> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public ArrayList<Item> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(ArrayList<Item> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public void addItem(Item  item){
        itemsInCart.add(item);
    }
}
