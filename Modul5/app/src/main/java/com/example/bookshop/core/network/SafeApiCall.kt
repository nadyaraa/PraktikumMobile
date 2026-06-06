package com.example.bookshop.core.network

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        val response = apiCall()
        ApiResult.Success(response)
    } catch (e: IOException) {
        ApiResult.Error(
            message = "Tidak ada koneksi internet. Periksa kembali koneksi internet anda.",
            throwable = e
        )
    } catch (e: HttpException) {
        ApiResult.Error(
            message = "Terjadi kesalahan: ${e.code()}",
            throwable = e
        )
    } catch (e: Exception) {
        ApiResult.Error(
            message = e.message ?: "Terjadi kesahalan tidak diketahui.",
            throwable = e
        )
    }
}