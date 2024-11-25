package com.alpharays.composeclean.data

import com.alpharays.composeclean.data.local.dao.StudentDao
import com.alpharays.composeclean.data.mapper.toStudent
import com.alpharays.composeclean.data.mapper.toStudentEntity
import com.alpharays.composeclean.data.mapper.toStudentResponse
import com.alpharays.composeclean.data.remote.ApiServices
import com.alpharays.composeclean.domain.UserRepository
import com.alpharays.composeclean.domain.model.Student
import com.alpharays.composeclean.domain.model.StudentListResponse
import com.alpharays.composeclean.utils.APIResponse
import com.alpharays.composeclean.utils.ResponseHandler
import com.alpharays.composeclean.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val studentDao: StudentDao
) :
    UserRepository {

    @Inject
    lateinit var responseHandler: ResponseHandler
    override suspend fun getUserList(): Flow<APIResponse<StudentListResponse?>> {
        val remoteResponse = responseHandler.callAPI {
            apiServices.getUserList()
        }

        val localResponse = studentDao.getAllStudents().map {
            it.toStudent()
        }


        val studentListResponse = remoteResponse.map {
            it.map { itt ->
                itt.toStudentResponse()
            }
        }

        return studentListResponse
    }


    override suspend fun addUserToList(student: Student): Flow<APIResponse<StudentListResponse?>> {
        studentDao.addStudent(student.toStudentEntity())
        return getLocalStudentList()
    }

    private suspend fun getLocalStudentList(): Flow<APIResponse<StudentListResponse?>> {
        return flow {
            emit(
                APIResponse.Success(
                    StudentListResponse(
                        studentDao.getAllStudents().map {
                            it.toStudent()
                        }
                    )
                )
            )
        }
    }
}