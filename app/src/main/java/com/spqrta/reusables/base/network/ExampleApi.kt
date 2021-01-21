package com.spqrta.reusables.base.network

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.*
import java.io.File


interface ExampleApi {

    @Headers(
        "Accept: application/vnd.github.v3.full+json",
        "User-Agent: Retrofit-Sample-App"
    )
    @POST("users/{param}/repos")
    fun request(
        @Header("Authorization") authorization: String,
        @HeaderMap headers: Map<String, String>,
        @Path("param") param: String,
        @Query("param") param1: String,
        @QueryMap options: Map<String, String>,
        @Body param2: String,
    ): Call<List<String>>


    @FormUrlEncoded
    @POST("users/{param}/repos")
    fun request1(
        @Field("first_name") first: String,
        @Field("last_name") last: String
    ): Call<List<String>>

    @Multipart
    @PUT("user/photo")
    fun request2(
        @Part("photo") photo: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("description") part: MultipartBody.Part,
    ): Call<String>

    companion object {
        fun createRequestBody(file: File, mime: String): RequestBody {
            return RequestBody.create(
                MediaType.parse(mime),
                file
            )
        }

        fun createMultipartPart(file: File, mime: String): MultipartBody.Part {
            return MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                RequestBody.create(MediaType.parse("image/*"), file)
            )
        }
    }
}