package com.csci310.uscdoordrink;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuCardAdapter extends RecyclerView.Adapter<MenuCardAdapter.MenuHolder>{
    private ArrayList<Item> menuItems;
    private MenuClickListener clickListener;

    public MenuCardAdapter(ArrayList<Item> menuItems, MenuClickListener clickListener) {
        this.menuItems = menuItems;
        this.clickListener = clickListener;
    }

//    public void updateData(ArrayList<Item> menuItems) {
//        this.menuItems = menuItems;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card_item, parent, false);
        return  new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, @SuppressLint("RecyclerView") int position) {
        Item item = menuItems.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText("Price per item: $" + item.getItemPrice());
        holder.itemCaffeineAmount.setText("Caffeine per item: " + item.getItemCaffeine() + " mg");
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item  = menuItems.get(position);
                item.setItemQtyInOrder(1);
                clickListener.onAddToCartClick(item);
                holder.addMoreLayout.setVisibility(View.VISIBLE);
                holder.addToCartButton.setVisibility(View.GONE);
                holder.qtyCount.setText(item.getItemQtyInOrder()+"");
            }
        });
        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item  = menuItems.get(position);
                int total = item.getItemQtyInOrder();
                total--;
                if(total > 0 ) {
                    item.setItemQtyInOrder(total);
                    clickListener.onUpdateCartClick(item);
                    holder.qtyCount.setText(total +"");
                } else {
                    holder.addMoreLayout.setVisibility(View.GONE);
                    holder.addToCartButton.setVisibility(View.VISIBLE);
                    item.setItemQtyInOrder(total);
                    clickListener.onRemoveFromCartClick(item);
                }
            }
        });

        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item  = menuItems.get(position);
                int total = item.getItemQtyInOrder();
                total++;
                item.setItemQtyInOrder(total);
                clickListener.onUpdateCartClick(item);
                holder.qtyCount.setText(total +"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView  itemPrice;
        TextView  itemCaffeineAmount;
        TextView  addToCartButton;
        ImageView imageMinus;
        ImageView imageAddOne;
        TextView  qtyCount;
        LinearLayout addMoreLayout;

        public MenuHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemPrice = view.findViewById(R.id.itemPrice);
            itemCaffeineAmount = view.findViewById(R.id.itemCaffeineAmount);
            addToCartButton = view.findViewById(R.id.addToCartButton);
            imageMinus = view.findViewById(R.id.imageMinus);
            imageAddOne = view.findViewById(R.id.imageAdd);
            qtyCount = view.findViewById(R.id.qtyCount);
            addMoreLayout  = view.findViewById(R.id.addMoreLayout);
        }
    }

    public interface MenuClickListener {
        public void onAddToCartClick(Item item);
        public void onUpdateCartClick(Item item);
        public void onRemoveFromCartClick(Item item);
    }
}


