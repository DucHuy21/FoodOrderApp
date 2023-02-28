package com.example.foodapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.models.CategoryModel;
import com.example.foodapp.retrofits.FoodAppApi;
import com.example.foodapp.retrofits.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private final FoodAppApi foodAppApi;

    public CategoryRepository() {
        foodAppApi = RetrofitInstance.retrofit().create(FoodAppApi.class);
    }
    public MutableLiveData<CategoryModel> getCategory() {
        MutableLiveData<CategoryModel> data = new MutableLiveData<>();
        foodAppApi.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {
                data.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
