package com.csci310.uscdoordrink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderCardAdapter adapter;
    private ArrayList<Order> ordersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Your Order Histories");
        actionBar.setDisplayHomeAsUpEnabled(true);
        InitializeOrderCardView();
    }

    private void InitializeOrderCardView() {
        recyclerView = findViewById(R.id.order_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersArrayList = new ArrayList<Order>();

        adapter = new OrderCardAdapter(this,ordersArrayList);
        recyclerView.setAdapter(adapter);

        CreateDataForCards();
    }

    private void CreateDataForCards() {
        Item mocha = new Item("Mocha", 2.49f, 1,20);
        Item mocha2 = new Item("Mocha", 2.49f, 2,20);
        Item cupp = new Item("Cuppucino", 5.49f,1,15);
        Item tea = new Item("Tea", 1.99f,2,30);

        ArrayList<Item> items = new ArrayList<>();
        items.add(mocha);
        items.add(mocha2);
        items.add(cupp);
        items.add(tea);

        DeliveryRoute deliveryRoute = new DeliveryRoute("Haha","Lolo","2022-3-24","20:46:22", "2022-3-24", "20:58:46");

        Order order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        order = new Order(items,deliveryRoute);
        ordersArrayList.add(order);

        adapter.notifyDataSetChanged();
    }
}