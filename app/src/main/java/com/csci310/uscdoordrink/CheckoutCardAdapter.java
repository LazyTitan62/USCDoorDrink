package com.csci310.uscdoordrink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckoutCardAdapter extends RecyclerView.Adapter<CheckoutCardAdapter.CheckoutHolder> {
    private ArrayList<Item> orderedItems;

    public CheckoutCardAdapter(ArrayList<Item> orderedItems){
        this.orderedItems = orderedItems;
    }

    public void updateData(ArrayList<Item> orderedItems) {
        this.orderedItems = orderedItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CheckoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_card_item, parent, false);
        return  new CheckoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutHolder holder, int position) {
        holder.checkoutItemName.setText(orderedItems.get(position).getItemName());
        holder.checkoutItemPrice.setText("Price per item: $"+String.format("%.2f", orderedItems.get(position).getItemPrice()));
        holder.checkoutItemQty.setText("Quantity: " + orderedItems.get(position).getItemQtyInOrder());
        holder.checkoutItemCaffeine.setText("Caffeine per item: " + orderedItems.get(position).getItemCaffeine() + " mg");
    }

    @Override
    public int getItemCount() {
        return orderedItems.size();
    }

    static class CheckoutHolder extends RecyclerView.ViewHolder {
        TextView checkoutItemName;
        TextView checkoutItemPrice;
        TextView checkoutItemQty;
        TextView checkoutItemCaffeine;

        public CheckoutHolder(View view) {
            super(view);
            checkoutItemName = view.findViewById(R.id.checkoutItemName);
            checkoutItemPrice = view.findViewById(R.id.checkoutItemPrice);
            checkoutItemQty = view.findViewById(R.id.checkoutItemQty);
            checkoutItemCaffeine = view.findViewById(R.id.checkoutItemCaffeine);
        }
    }
}
