package com.example.chennai360;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class foodAdaptor extends RecyclerView.Adapter<foodAdaptor.MyViewHolder> {
    Context context;
    ArrayList<FoodModal> foodModals;

    private final FoodRecyclerViewInterface recyclerViewInterface;

    public foodAdaptor(Context context, ArrayList<FoodModal> foodModals, FoodRecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.foodModals = foodModals;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public foodAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);

        return new foodAdaptor.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull foodAdaptor.MyViewHolder holder, int position) {
        holder.textView.setText(foodModals.get(position).getName());
        holder.textView1.setText(foodModals.get(position).getCalories());
        holder.imageView.setImageResource(foodModals.get(position).getFoodImage());
        holder.imageView1.setImageResource(foodModals.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return foodModals.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView1;
        TextView textView;
        TextView textView1;


        public MyViewHolder(@NonNull View itemView, FoodRecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.foodImage);
            imageView1 = itemView.findViewById(R.id.imageView4);
            textView = itemView.findViewById(R.id.foodName);
            textView1 = itemView.findViewById(R.id.foodCalories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemCLick(pos);
                        }
                    }
                }
            });

        }
    }
}
