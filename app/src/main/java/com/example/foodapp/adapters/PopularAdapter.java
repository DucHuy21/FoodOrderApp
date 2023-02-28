package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.activity.MainActivity;
import com.example.foodapp.databinding.ItemRcvCategoryBinding;
import com.example.foodapp.databinding.ItemRcvPopularBinding;
import com.example.foodapp.listener.CategoryListener;
import com.example.foodapp.listener.PopularListener;
import com.example.foodapp.models.Category;
import com.example.foodapp.models.Meals;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {

    List<Meals> list;
    private  PopularListener listener;

    public PopularAdapter(List<Meals> list, PopularListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvPopularBinding itemRcvPopularBinding = ItemRcvPopularBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemRcvPopularBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(list.get(position));
        Glide.with(holder.itemView).load(list.get(position).getStrMealThumb()).into(holder.binding.imgPopular);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private  ItemRcvPopularBinding binding;

        public MyViewHolder(ItemRcvPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setBinding(Meals meals){
            binding.setPopular(meals);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPopularClick(meals);
                }
            });
        }
    }
}
