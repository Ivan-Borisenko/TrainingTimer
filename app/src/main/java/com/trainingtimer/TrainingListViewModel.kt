package com.trainingtimer

import androidx.lifecycle.ViewModel

class TrainingListViewModel : ViewModel() {

    private val trainingRepository = TrainingRepository.get()
    val trainingListLiveData = trainingRepository.getTrainings()
}