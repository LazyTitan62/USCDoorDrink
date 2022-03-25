package com.csci310.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Order> ordersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.order_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersArrayList = new ArrayList<Order>();
        
        adapter = new CardAdapter(this,ordersArrayList);
        recyclerView.setAdapter(adapter);
        
        CreateDataForCards();
    }

    private void CreateDataForCards() {
        Item mocha = new Item("Mocha", 2.49, "Drink",1,2.0);
        Item mocha2 = new Item("Mocha", 2.49, "Drink",2,2.0);
        Item cupp = new Item("Cuppucino", 5.49, "Drink",1,1.5);
        Item tea = new Item("Tea", 1.99, "Drink",2,3.0);

        ArrayList<Item> items = new ArrayList<>();
        items.add(mocha);
        items.add(mocha2);
        items.add(cupp);
        items.add(tea);

        DeliveryRoute deliveryRoute = new DeliveryRoute("Home", "Res","2022/3/24/20:46", "2022/3/24/20:58");

        Order order = new Order(items,deliveryRoute,001,007);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,002,003);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,012,044);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,062,027);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,042,046);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,102,103);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute,222,333);
        ordersArrayList.add(order);

        adapter.notifyDataSetChanged();
    }
}