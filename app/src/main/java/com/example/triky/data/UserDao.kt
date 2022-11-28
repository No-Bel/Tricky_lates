package com.example.triky.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserDao(user: User)

    @Query("Select * From user_table WHERE selected = 0")
    fun readAllData(): LiveData<List<User>>

    //For Update
    @Update
    suspend fun updateItemWhenBoxTrueDao(user: User)

    @Update
    suspend fun updateItemWhenBoxFalseDao(user: User)

    @Update
    suspend fun updateCurrentItemDao(user: User)

    //For Delete
    @Delete
    suspend fun deleteUser(list: List<User>)


    //Completed data
    @Query("SELECT * FROM user_table WHERE selected = 1")
    fun getCompletedDataDAO(): LiveData<List<User>>
}