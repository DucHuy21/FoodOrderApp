package com.example.foodapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.models.MealModel;
import com.example.foodapp.repository.MealRepository;

public class CategoryViewModel extends ViewModel {
    private MealRepository mealRepository;

    public CategoryViewModel() {
        mealRepository = new MealRepository();
    }
    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate) {
        return mealRepository.getMeal(idcate);
    }
}
