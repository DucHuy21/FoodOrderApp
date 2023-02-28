package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.databinding.ItemRcvCategoryBinding;
import com.example.foodapp.listener.CategoryListener;
import com.example.foodapp.listener.PopularListener;
import com.example.foodapp.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    List<Category> list;
    private CategoryListener listener;

    public CategoryAdapter(List<Category> list, CategoryListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvCategoryBinding itemRcvCategoryBinding = ItemRcvCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemRcvCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(list.get(position));
        Glide.with(holder.itemView).load(list.get(position).getCategoryThumb()).into(holder.binding.imgItemCategory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private  ItemRcvCategoryBinding binding;

        public MyViewHolder(ItemRcvCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setBinding(Category category){
            binding.setCategory(category);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCategoryClick(category);
                }
            });
        }
    }
}
