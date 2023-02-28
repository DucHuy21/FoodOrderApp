package com.example.foodapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.models.MealDetailModel;
import com.example.foodapp.models.MealModel;
import com.example.foodapp.retrofits.FoodAppApi;
import com.example.foodapp.retrofits.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailRepository {
    private FoodAppApi appApi;

    public MealDetailRepository() {
        appApi = RetrofitInstance.retrofit().create(FoodAppApi.class);
    }

    public MutableLiveData<MealDetailModel> getMealDetail(int id) {
        MutableLiveData<MealDetailModel> data = new MutableLiveData<>();
        appApi.getMealsDetails(id).enqueue(new Callback<MealDetailModel>() {
            @Override
            public void onResponse(Call<MealDetailModel> call, Response<MealDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealDetailModel> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
