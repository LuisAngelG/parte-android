package com.sinche.victor.myapplication.network;

import com.sinche.victor.myapplication.model.ApiResponse;
import com.sinche.victor.myapplication.model.ApiResponseProduct;
import com.sinche.victor.myapplication.model.Usuarios;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apis {

    @GET("usuarios")
    Call<ApiResponse> getAllUser();

    @POST("usuarios")
    Call<Usuarios> postNewUser(@Body Usuarios usuario);

    @GET("productos")
    Call<ApiResponseProduct> getAllProduct();

}
