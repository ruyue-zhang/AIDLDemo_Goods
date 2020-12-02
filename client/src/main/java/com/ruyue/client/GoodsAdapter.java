package com.ruyue.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ruyue.goods.Fruit;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.FruitViewHolder> {

    private List<Fruit> fruitList;
    private Context context;

    public GoodsAdapter(List<Fruit> fruitList, Context context) {
        this.fruitList = fruitList;
        this.context = context;
    }

    static class FruitViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView description;
        private TextView price;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.name = itemView.findViewById(R.id.name);
            this.description = itemView.findViewById(R.id.description);
            this.price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new GoodsAdapter.FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        Glide.with(context).load(fruit.getImgLink()).into(holder.image);
        holder.name.setText(fruit.getName());
        holder.description.setText(fruit.getDescription());
        holder.price.setText(String.format("%s", fruit.getPrice()));
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
