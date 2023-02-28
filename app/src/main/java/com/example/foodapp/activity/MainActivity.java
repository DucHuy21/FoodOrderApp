package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.R;
import com.example.foodapp.adapters.CategoryAdapter;
import com.example.foodapp.adapters.PopularAdapter;
import com.example.foodapp.databinding.ActivityMainBinding;
import com.example.foodapp.listener.CategoryListener;
import com.example.foodapp.listener.PopularListener;
import com.example.foodapp.models.Category;
import com.example.foodapp.models.Meals;
import com.example.foodapp.viewModels.MainViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import meow.bottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity implements CategoryListener, PopularListener {
    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
        initData();

      /*  navigation = findViewById(R.id.meowNav);

        //add menu
        navigation.add(new MeowBottomNavigation.Model(1, R.drawable.home_24));
        navigation.add(new MeowBottomNavigation.Model(2, R.drawable.info_24));
        navigation.add(new MeowBottomNavigation.Model(3, R.drawable.shopping_cart_24));
        navigation.add(new MeowBottomNavigation.Model(4, R.drawable.support_24));
        navigation.add(new MeowBottomNavigation.Model(5, R.drawable.settings_24));*/


    }

    private void initView() {
        binding.rcvCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rcvCategory.setLayoutManager(layoutManagerCategory);

        binding.rcvPopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerPopular = new GridLayoutManager(this, 3);
        binding.rcvPopular.setLayoutManager(layoutManagerPopular);

        binding.floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });
    }

    private void initData() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.categoryModelMutableLiveData().observe(this, categoryModel -> {
            if (categoryModel.isSuccess()){
                CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModel.getResult(), this);
                binding.rcvCategory.setAdapter(categoryAdapter);
            }
        });
        mainViewModel.mealModelMutableLiveData(1).observe(this, mealModel -> {
            if (mealModel.isSuccess()){
                PopularAdapter popularAdapter = new PopularAdapter(mealModel.getResult(),  this);
                binding.rcvPopular.setAdapter(popularAdapter);
            }
        });
    }

    @Override
    public void onCategoryClick(Category category) {
        Intent intent = new Intent(getApplicationContext(), CategoryItemActivity.class);
        intent.putExtra("idcate", category.getId());
        intent.putExtra("namecate", category.getCategory());
        startActivity(intent);
    }

    @Override
    public void onPopularClick(Meals meals) {
        Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
        intent.putExtra("id", meals.getIdMeal());
        startActivity(intent);
    }
}