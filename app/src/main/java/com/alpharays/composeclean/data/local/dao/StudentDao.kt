package com.alpharays.composeclean.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.alpharays.composeclean.data.local.entity.StudentEntity
import com.alpharays.composeclean.domain.model.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student_details")
    suspend fun getAllStudents(): List<StudentEntity>
}