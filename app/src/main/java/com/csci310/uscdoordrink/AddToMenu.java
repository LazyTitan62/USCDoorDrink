package com.csci310.uscdoordrink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddToMenu extends AppCompatActivity {
    EditText item,price,caf;
    Button buttonAdd, buttonSubmit;
    LinearLayout container;
    ArrayList<Item> menuItems = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_add_menu);
        item = findViewById(R.id.itemName);
        price = findViewById(R.id.itemPrice);
        caf = findViewById(R.id.itemCaf);
        buttonAdd = findViewById(R.id.add);
        buttonSubmit = findViewById(R.id.submit_menu);
        container = findViewById(R.id.container);

        Intent intent = getIntent();
        //receive user name from intent
        String UserName = intent.getExtras().getString("userID");

        buttonAdd.setOnClickListener(view -> {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View addView = layoutInflater.inflate(R.layout.row, null);

            TextView showItemName = addView.findViewById(R.id.show_itemName);
            TextView showItemPrice = addView.findViewById(R.id.show_itemPrice);
            TextView showItemCaf = addView.findViewById(R.id.show_itemCaf);

            showItemName.setText(item.getText().toString());
            showItemPrice.setText(price.getText().toString());
            showItemCaf.setText(caf.getText().toString());

            //create an item object
            Item menuItem = new Item(item.getText().toString(),Float.parseFloat(price.getText().toString()),Integer.parseInt(caf.getText().toString()));
            //insert into menuItems arraylist
            menuItems.add(menuItem);

            Button buttonRemove = addView.findViewById(R.id.remove);
            buttonRemove.setOnClickListener(v -> ((LinearLayout)addView.getParent()).removeView(addView));
            container.addView(addView);
        });

        buttonSubmit.setOnClickListener(v -> {
            //insert menu into
            //finish this later--------------------------------------
            new Thread(() -> {
                for(Item i: menuItems){
                    insertMenu.InsertMenu(i,UserName);
                }
            }).start();

            //lead to add location page
            Intent userID = new Intent(AddToMenu.this, EnterLocation.class);
            userID.putExtra("userID", UserName);
            startActivity(userID);

        });

    }
}