package com.d3if4043.kalkulator_jodoh.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InputDao {
    @Insert
    fun insert(input: InputEntity)

    @Query("SELECT * FROM input ORDER BY id DESC")
    fun getLastQuotes(): LiveData<List<InputEntity?>>

    @Query("DELETE FROM input")
    fun clearData()
}