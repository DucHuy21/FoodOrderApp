package com.example.foodapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.models.MealDetailModel;
import com.example.foodapp.repository.MealDetailRepository;

public class ShowDetailViewModel extends ViewModel {
    private MealDetailRepository mealDetailRepository;

    public ShowDetailViewModel() {
        mealDetailRepository = new MealDetailRepository();
    }
    public MutableLiveData<MealDetailModel> mealDetailModelMutableLiveData(int id){
        return mealDetailRepository.getMealDetail(id);
    }
}
