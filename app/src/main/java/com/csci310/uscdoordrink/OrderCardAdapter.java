package com.csci310.uscdoordrink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardAdapter.OrderHolder> {

    private Context context;
    private ArrayList<Order> orders;

    public OrderCardAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_card_item,parent,false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Order order = orders.get(position);
        holder.setDetails(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        private TextView from, to, created, delivered, items;


        OrderHolder(View itemView){
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            items = itemView.findViewById(R.id.items);
            created = itemView.findViewById(R.id.created);
            delivered = itemView.findViewById(R.id.delivered);
        }

        void setDetails(Order order){
            from.setText("From: " + order.getDeliveryRoute().getMerchantUsrName());
            to.setText("To: " + order.getDeliveryRoute().getCustomerUsrName());
            created.setText("Placed time: " + order.getDeliveryRoute().getOrderPlacedDate() + " " + order.getDeliveryRoute().getOrderPlacedTime());
            delivered.setText("Delivered time: " + order.getDeliveryRoute().getDeliveryDate() + " " + order.getDeliveryRoute().getDeliveryTime());
            items.setText(order.orderToString());
        }
    }
}
