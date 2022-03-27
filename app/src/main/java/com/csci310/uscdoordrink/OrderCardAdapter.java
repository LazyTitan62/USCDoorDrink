package com.csci310.uscdoordrink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

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
        private TextView from, to, items;


        OrderHolder(View itemView){
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            items = itemView.findViewById(R.id.items);
        }

        void setDetails(Order order){
            from.setText(String.format(Locale.US,"From: %f", order.getDeliveryRoute().getMerchantLocLatitude()));
            to.setText(String.format(Locale.US,"To: %f", order.getDeliveryRoute().getCustomerLatitude()));
            items.setText(order.orderToString());

        }
    }
}
