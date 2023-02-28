package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.adapters.MealAdapter;
import com.example.foodapp.databinding.ActivityCategoryItemBinding;
import com.example.foodapp.viewModels.CategoryViewModel;

public class CategoryItemActivity extends AppCompatActivity {
    ActivityCategoryItemBinding binding;
    CategoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_item);
        
        initView();
        initData();
    }

    private void initView() {
        binding.rcvCategoryItem.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rcvCategoryItem.setLayoutManager(layoutManager);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        int idcate = getIntent().getIntExtra("idcate", 1);
        String namecate = getIntent().getStringExtra("namecate");
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.mealModelMutableLiveData(idcate).observe(this, mealModel -> {
            if (mealModel.isSuccess()){
                MealAdapter adapter = new MealAdapter(mealModel.getResult());
                binding.rcvCategoryItem.setAdapter(adapter);
                binding.tvName.setText(namecate + ":" + mealModel.getResult().size());
            }
        });
    }
}