package com.example.foodapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.models.MessModel;
import com.example.foodapp.retrofits.FoodAppApi;
import com.example.foodapp.retrofits.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private FoodAppApi api;
    MutableLiveData<MessModel> data;

    public CartRepository() {
        api = RetrofitInstance.retrofit().create(FoodAppApi.class);
        data = new MutableLiveData<>();
    }

    public void checkOut(int iduser, int amount, double total, String detail) {
        api.postCart(iduser, amount, total, detail).enqueue(new Callback<MessModel>() {
            @Override
            public void onResponse(@NonNull Call<MessModel> call, @NonNull Response<MessModel> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MessModel> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
    }

    public MutableLiveData<MessModel> messModelMutableLiveData(){
        return data;
    }
}
