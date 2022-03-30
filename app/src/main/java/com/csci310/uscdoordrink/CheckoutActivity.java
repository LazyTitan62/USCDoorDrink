package com.csci310.uscdoordrink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckoutActivity extends AppCompatActivity {
    private String customerName;
    private String merchantName;
    private Integer neededTime;
    private Cart cart;
    private Integer totalCaffeine = 0;
    private Boolean updateCaffeine = false;
    private Integer consumedCaffeine = 0;
    private String date;
    private RecyclerView checkoutRecyclerView;
    private TextView orderTotal, orderTotalAmount, buttonPlaceOrder, caffineIntakeSummary;
    private CheckoutCardAdapter checkoutCardAdapter;
    private final Integer USDA_CAFFEINE = 400;
    private Boolean waringIgnored = false;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    updateCaffeineIntake();
//                    break;
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Merchant  merchant= (Merchant) getIntent().getSerializableExtra("Merchant");
        merchantName = getIntent().getStringExtra("MerchantName");
        customerName = getIntent().getStringExtra("CustomerName");
        cart = (Cart) getIntent().getSerializableExtra("Cart");
        neededTime = getIntent().getExtras().getInt("NeededTime");

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler;
        handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
                Query q = new Query();
                q.refreshCaffeine(date,customerName);
                consumedCaffeine = q.getCaffeine(customerName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Background work here

            handler.post(new Runnable() {
                @Override
                public void run() {
                    for (Item i : cart.getItemsInCart()) {
                        totalCaffeine += i.getItemCaffeine() * i.getItemQtyInOrder();
                    }
                    caffineIntakeSummary.setText(
                            "You have consumed " + consumedCaffeine + " mg\n" +
                                    "You will consume " + totalCaffeine + " mg\n" + "----------------------------------------------------\n" +
                                    "Total consumption: " + Integer.toString(consumedCaffeine + totalCaffeine) + " mg\n" +
                                    "(USDA recommended consumption: 400 mg)");
                }
            });
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(merchantName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        orderTotal = findViewById(R.id.orderTotal);
        orderTotalAmount = findViewById(R.id.orderTotalAmount);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        caffineIntakeSummary = findViewById(R.id.tvCaffineIntake);


        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlaceOrderButtonClick();
            }
        });

        initRecyclerView();
        calculateTotalAmount();


    }

    private void initRecyclerView() {
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutCardAdapter = new CheckoutCardAdapter(cart.getItemsInCart());
        checkoutRecyclerView.setAdapter(checkoutCardAdapter);
    }


    private void calculateTotalAmount() {
        Float totalAmount = 0f;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        for(Item i : cart.getItemsInCart()) {
            totalAmount += i.getItemPrice() * i.getItemQtyInOrder();
            totalAmount = Float.parseFloat(df.format(totalAmount));
        }
        orderTotalAmount.setText("$ " + String.format("%.2f", totalAmount));
    }

//    private void calculateTotalCaffeineAmount() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Handler handler;
//        handler = new Handler(Looper.getMainLooper());
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Query q = new Query();
//                    consumedCaffeine = q.getCaffeine(customerName);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Integer totalCaffeineAmount = 0;
//                        for(Item i : cart.getItemsInCart()) {
//                            totalCaffeineAmount += i.getItemCaffeine() * i.getItemQtyInOrder();
//                        }
//                        caffineIntakeSummary.setText(
//                                "You have consumed " + consumedCaffeine + " mg\n" +
//                                        "You will consume " + totalCaffeineAmount + " mg\n" + "----------------------------------------------------\n" +
//                                        "Total consumption: " + Integer.toString(consumedCaffeine + totalCaffeineAmount) + " mg\n" +
//                                        "(USDA recommended consumption: 400 mg)");
//                    }
//                });
//            }
//        });
//
//
//    }

    private void onPlaceOrderButtonClick() {
        if (consumedCaffeine + totalCaffeine > USDA_CAFFEINE){
            if (!waringIgnored) {
                Toast.makeText(CheckoutActivity.this, "TOO MUCH CAFFEINE TODAY!\nPlease stay at this page and retry if you still want to proceed.", Toast.LENGTH_SHORT).show();
                waringIgnored = true;
                return;
            }
        }

        LocalDateTime obj = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String createdDate = obj.format(formatterDate);
        String createdTime = obj.format(formatterTime);

        LocalDateTime objNew = obj.plusMinutes((long)neededTime);
        String deliveredDate = objNew.format(formatterDate);
        String deliveredTime = objNew.format(formatterTime);

        DeliveryRoute route = new DeliveryRoute(merchantName, customerName, createdDate, createdTime,deliveredDate,deliveredTime);

        Order order = new Order(cart.getItemsInCart(),route);
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        Handler handler1;
        handler1 = new Handler(Looper.getMainLooper());
        executor1.execute(() -> {
            try {
                Query q1 = new Query();
                q1.insertOrderIntoDatabase(order);
                q1.updateCaffeine(customerName,order.getTotalCaffeine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler1.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CheckoutActivity.this, "Order Successfully Placed!", Toast.LENGTH_LONG).show();
                    cart.setItemsInCart(new ArrayList<Item>());
                    Intent i = new Intent(CheckoutActivity.this, MapsActivity.class);
                    i.putExtra("userID",customerName);
                    i.putExtra("isMerchant",0);
                    startActivity(i);
                }
            });
        });
        //        new Thread(() -> {
//            Query q = new Query();
//            q.insertOrderIntoDatabase(order);
//            q.updateCaffeine(customerName,order.getTotalCaffeine());
//        }).start();
//        Toast.makeText(CheckoutActivity.this, "Order Successfully Placed!", Toast.LENGTH_LONG).show();
//        cart.setItemsInCart(new ArrayList<Item>());
//        Intent i = new Intent(CheckoutActivity.this, MapsActivity.class);
//        i.putExtra("userID",customerName);
//        i.putExtra("isMerchant",0);
//        startActivity(i);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }


}