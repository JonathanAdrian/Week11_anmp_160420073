package com.example.advweek4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advweek4.model.Menu
import com.example.advweek4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    fun setSelectedStudent(student: Student) {
        studentLD.value = student
    }
}