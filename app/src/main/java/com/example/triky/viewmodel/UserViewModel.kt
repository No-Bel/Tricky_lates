package com.example.triky.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.triky.data.User
import com.example.triky.data.UserDatabase
import com.example.triky.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application): AndroidViewModel(app) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(app).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    //add User
    fun addUserVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserRp(user)
        }
    }

    //Update User
    fun updateItemWhenBoxTrueVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemWhenBoxTrueRp(user)
        }
    }

    fun updateItemWhenBoxFalseVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemWhenBoxFalseRp(user)
        }
    }

    fun updateCurrentItemVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCurrentItemRp(user)
        }
    }

    //Delete User
    fun deleteUserVm(list: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserRp(list)
        }
    }

    //Completed Data
    fun getCompletedDataVm(): LiveData<List<User>> {
        return repository.getCompletedDataRp()
    }
}