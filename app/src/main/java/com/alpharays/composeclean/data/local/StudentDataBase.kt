package com.alpharays.composeclean.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alpharays.composeclean.data.local.dao.StudentDao
import com.alpharays.composeclean.data.local.entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class StudentDataBase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}