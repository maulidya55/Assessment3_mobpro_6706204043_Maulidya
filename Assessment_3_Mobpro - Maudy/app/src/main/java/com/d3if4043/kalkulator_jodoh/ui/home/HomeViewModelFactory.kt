package com.d3if4043.kalkulator_jodoh.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if4043.kalkulator_jodoh.db.InputDao

class HomeViewModelFactory(
    private val db: InputDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}