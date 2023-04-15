package com.example.composemovies.utils

import com.example.composemovies.domain.repository.Either
import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

fun <T> handleResponse(call: suspend () -> T): Flow<Either<T>> {
    return flow {
        try {
            val response = call.invoke()
            emit(Either.Success(response))
        } catch (e: ClientRequestException) {
            val errorObj = JSONObject(e.response.readText())
            if(errorObj.has("message")){
                val errorMessagesObj = errorObj.getString("message")
                emit(Either.Error(errorMessagesObj.toString()))
            }else{
                emit(Either.Error(e.response.status.description))
            }
        } catch (e: ServerResponseException) {
            val errorObj = JSONObject(e.response.readText())
            if(errorObj.has("message")){
                val errorMessagesObj = errorObj.getString("message")
                emit(Either.Error(errorMessagesObj.toString()))
            }else{
                emit(Either.Error(e.response.status.description))
            }
        } catch (e: RedirectResponseException) {
            val errorObj = JSONObject(e.response.readText())
            if(errorObj.has("message")){
                val errorMessagesObj = errorObj.getString("message")
                emit(Either.Error(errorMessagesObj.toString()))
            }else{
                emit(Either.Error(e.response.status.description))
            }
        } catch (e: ConnectTimeoutException) {
            emit(Either.Error("Connection Timeout"))
        } catch (e: SocketTimeoutException) {
            emit(Either.Error("Socket Timeout"))
        } catch (e: IOException) {
            emit(Either.Error(e.message ?: "Unknown IO Error"))
        } catch (e: Exception) {
            emit(Either.Error(e.message ?: "Unknown Error"))
        }
    }
}