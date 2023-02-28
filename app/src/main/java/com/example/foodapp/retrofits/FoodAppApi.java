package com.example.foodapp.retrofits;

import com.example.foodapp.models.Category;
import com.example.foodapp.models.CategoryModel;
import com.example.foodapp.models.MealDetailModel;
import com.example.foodapp.models.MealModel;
import com.example.foodapp.models.MessModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();

    @POST("meal.php")
    @FormUrlEncoded
    Call<MealModel> getMeals(
            @Field("idcate") int idcate
    );

    @POST("mealdetail.php")
    @FormUrlEncoded
    Call<MealDetailModel> getMealsDetails(
            @Field("id") int id
    );

    @POST("cart.php")
    @FormUrlEncoded
    Call<MessModel> postCart(
            @Field("iduser") int id,
            @Field("amount") int amount,
            @Field("total") double total,
            @Field("detail") String detail
    );
}
