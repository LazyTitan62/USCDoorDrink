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

public class MenuActivity extends AppCompatActivity implements MenuCardAdapter.MenuClickListener{
    private ArrayList<Item> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuCardAdapter adapter;
    private ArrayList<Item> itemsInCart;
    private int totalItemInCart = 0;
    private TextView buttonCheckout;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    initializeMenuCardView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Merchant merchant = (Merchant) getIntent().getSerializableExtra("Merchant");
        // Customer customer = (Customer) getIntent().getSerializableExtra("Customer");
        // Boolean isUserMerchant = getIntent().getExtras().getBoolean("IsUserMerchant");

        Merchant merchant = new Merchant("OhYeah","123456",10.10f,20.20f);
        new Thread(() -> {
            Query in = new Query();
            menuList.addAll(in.getMenuFromDatabase(merchant));
            merchant.setMenu(menuList);
            handler.sendEmptyMessage(0);

        }).start();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(merchant.getUsrName());
        actionBar.setSubtitle(String.valueOf(merchant.getStoreLocLatitude()) + " " + String.valueOf(merchant.getStoreLocLongitude()));
        actionBar.setDisplayHomeAsUpEnabled(true);


        Customer customer = new Customer("Redekopp", "123456", 300);
        Boolean isUserMerchant = true;

        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((itemsInCart != null && itemsInCart.size() <= 0) || itemsInCart == null) {
                    Toast.makeText(MenuActivity.this, "Please add some items in cart.", Toast.LENGTH_SHORT).show();
                    return;
                }
                customer.setCurrCart(itemsInCart);
                Intent i = new Intent(MenuActivity.this, CheckoutActivity.class);
                i.putExtra("Merchant", merchant);
                i.putExtra("Customer", customer);
                i.putExtra("IsUserMerchant",isUserMerchant);
                //startActivityForResult(i, 1000);
                startActivity(i);
            }
        });

    }

    private void initializeMenuCardView() {
        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuCardAdapter(menuList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAddToCartClick(Item item) {
        if(itemsInCart == null) {
            itemsInCart = new ArrayList<>();
        }
        itemsInCart.add(item);
        totalItemInCart = 0;

        for(Item i : itemsInCart) {
            totalItemInCart = totalItemInCart + i.getItemQtyInOrder();
        }
        buttonCheckout.setText("Checkout (" +totalItemInCart +") items");
    }

    @Override
    public void onUpdateCartClick(Item item) {
        if(itemsInCart.contains(item)) {
            int index = itemsInCart.indexOf(item);
            itemsInCart.remove(index);
            itemsInCart.add(index, item);

            totalItemInCart = 0;

            for(Item i : itemsInCart) {
                totalItemInCart = totalItemInCart + i.getItemQtyInOrder();
            }
            buttonCheckout.setText("Checkout (" +totalItemInCart +") items");
        }
    }

    @Override
    public void onRemoveFromCartClick(Item item) {
        if(itemsInCart.contains(item)) {
            itemsInCart.remove(item);
            totalItemInCart = 0;

            for(Item i : itemsInCart) {
                totalItemInCart = totalItemInCart + i.getItemQtyInOrder();
            }
            buttonCheckout.setText("Checkout (" +totalItemInCart +") items");
        }
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



//    private void CreateDataForCards() {
//
//
//       // Merchant merchant = new Merchant("OhYeah","123456",10.10,20.20,10, 20);
//       // merchant.setMenu(items);
//
//        adapter.notifyDataSetChanged();
//    }
}