package com.example.foodapp.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.models.CategoryModel;
import com.example.foodapp.models.MealModel;
import com.example.foodapp.repository.CategoryRepository;
import com.example.foodapp.repository.MealRepository;

import java.io.Closeable;

public class MainViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    private MealRepository mealRepository;

    public MainViewModel() {
        categoryRepository = new CategoryRepository();
        mealRepository = new MealRepository();
    }

    public MutableLiveData<CategoryModel> categoryModelMutableLiveData() {
        return categoryRepository.getCategory();
    }
    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate){
        return  mealRepository.getMeal(idcate);
    }
}
