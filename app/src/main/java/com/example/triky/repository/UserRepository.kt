package com.example.triky.repository

import androidx.lifecycle.LiveData
import com.example.triky.data.User
import com.example.triky.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    //add user
    suspend fun addUserRp(user: User) =  userDao.addUserDao(user)

    //Update
    suspend fun updateItemWhenBoxTrueRp(user: User) = userDao.updateItemWhenBoxTrueDao(user)

    suspend fun updateItemWhenBoxFalseRp(user: User) = userDao.updateItemWhenBoxFalseDao(user)

    suspend fun updateCurrentItemRp(user: User) = userDao.updateCurrentItemDao(user)

    //Delete
    suspend fun deleteUserRp(list: List<User>) = userDao.deleteUser(list)

    //Completed Data
    fun getCompletedDataRp() : LiveData<List<User>> {
        return userDao.getCompletedDataDAO()
    }
}