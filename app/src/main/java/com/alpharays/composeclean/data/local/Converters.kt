package com.alpharays.composeclean.data.local

import androidx.room.TypeConverter
import com.alpharays.composeclean.data.local.entity.StudentEntity
import com.alpharays.composeclean.domain.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStudentList(value: List<StudentEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStudentList(value: String): List<Student> {
        val listType = object : TypeToken<List<StudentEntity>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}