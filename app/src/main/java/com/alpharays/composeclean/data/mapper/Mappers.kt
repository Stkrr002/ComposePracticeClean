package com.alpharays.composeclean.data.mapper

import com.alpharays.composeclean.data.remote.dto.StudentDto
import com.alpharays.composeclean.data.remote.dto.StudentListResponseDto
import com.alpharays.composeclean.domain.model.Student
import com.alpharays.composeclean.domain.model.StudentListResponse

fun StudentListResponseDto.toStudentResponse() = StudentListResponse(
    students = students?.map {
        it?.toStudent()
    }
)

fun StudentDto.toStudent() = Student(
    name = name,
    roll_number = roll_number
)
