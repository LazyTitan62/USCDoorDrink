package com.csci310.uscdoordrink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
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
//                    customer.resetCaffeineIntake();
//                    break;
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Merchant  merchant= (Merchant) getIntent().getSerializableExtra("Merchant");
        Customer customer = (Customer) getIntent().getSerializableExtra("Customer");
        ArrayList<Item> menu = customer.getCurrCart();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(merchant.getUsrName());
        actionBar.setSubtitle(String.valueOf(merchant.getStoreLocLatitude()) + " " + String.valueOf(merchant.getStoreLocLongitude()));
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        orderTotal = findViewById(R.id.orderTotal);
        orderTotalAmount = findViewById(R.id.orderTotalAmount);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        caffineIntakeSummary = findViewById(R.id.tvCaffineIntake);

        Integer caffeine = calculateTotalCaffeineAmount(customer);
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String date = "";
//                new Thread(() -> {
//                    Query q = new Query();
//                    if (q.checkCaffeineUpdate(date,customer)){
//                        handler.sendEmptyMessage(0);
//                    }
//                }).start();
                onPlaceOrderButtonClick(merchant, customer, caffeine);
            }
        });

        initRecyclerView(customer);
        calculateTotalAmount(customer);


    }

    private void initRecyclerView(Customer customer) {
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutCardAdapter = new CheckoutCardAdapter(customer.getCurrCart());
        checkoutRecyclerView.setAdapter(checkoutCardAdapter);
    }

    private void calculateTotalAmount(Customer customer) {
        Float totalAmount = 0f;
        for(Item i : customer.getCurrCart()) {
            totalAmount += i.getItemPrice() * i.getItemQtyInOrder();
        }
        orderTotalAmount.setText("$ " + String.format("%.2f", totalAmount));
    }

    private Integer calculateTotalCaffeineAmount(Customer customer) {
        Integer totalCaffeineAmount = 0;
        for(Item i : customer.getCurrCart()) {
            totalCaffeineAmount += i.getItemCaffeine() * i.getItemQtyInOrder();
        }
        caffineIntakeSummary.setText(
                "You have consumed " + customer.getCaffeineIntake() + " mg\n" +
                        "You will consume " + totalCaffeineAmount + " mg\n" + "----------------------------------------------------\n" +
                        "Total consumption: " + Integer.toString(customer.getCaffeineIntake() + totalCaffeineAmount) + " mg\n" +
                        "(USDA recommended consumption: 400 mg)");
        return totalCaffeineAmount;
    }

    private void onPlaceOrderButtonClick(Merchant merchant, Customer customer, Integer totalCaffineInCart) {
        if (customer.getCaffeineIntake() + totalCaffineInCart > USDA_CAFFEINE){
            if (!waringIgnored) {
                Toast.makeText(CheckoutActivity.this, "TOO MUCH CAFFEINE TODAY!\nPlease stay at this page and retry if you still want to proceed.", Toast.LENGTH_SHORT).show();
                waringIgnored = true;
                return;
            }
        }
        Toast.makeText(CheckoutActivity.this, "Order Successfully Placed!", Toast.LENGTH_LONG).show();
        Order order = customer.checkout(merchant,customer);
        //TODO：INSERT INTO DATABASE
        Intent i = new Intent(CheckoutActivity.this, MapsActivity.class);
        startActivity(i);
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