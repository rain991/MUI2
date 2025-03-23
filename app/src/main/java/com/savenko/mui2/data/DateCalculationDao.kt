package com.example.mui1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DateDifferenceCalculationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(DateDifferenceCalculation: DateDifferenceCalculation)

    @Update
    suspend fun update(DateDifferenceCalculation: DateDifferenceCalculation)

    @Delete
    suspend fun deleteItem(DateDifferenceCalculation: DateDifferenceCalculation)

    @Query("SELECT * FROM dateDifferenceCalculation")
    fun getAll(): Flow<List<DateDifferenceCalculation>>
}