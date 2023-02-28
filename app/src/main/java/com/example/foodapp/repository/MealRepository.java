package com.example.foodapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.models.MealModel;
import com.example.foodapp.retrofits.FoodAppApi;
import com.example.foodapp.retrofits.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    private final FoodAppApi api;

    public MealRepository() {
        api = RetrofitInstance.retrofit().create(FoodAppApi.class);
    }

    public MutableLiveData<MealModel> getMeal(int idcate) {
        MutableLiveData<MealModel> data = new MutableLiveData<>();
        api.getMeals(idcate).enqueue(new Callback<MealModel>() {
            @Override
            public void onResponse(@NonNull Call<MealModel> call, @NonNull Response<MealModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealModel> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
