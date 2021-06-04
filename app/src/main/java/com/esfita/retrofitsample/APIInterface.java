package com.esfita.retrofitsample;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/repositories")
   Call<List<ProductModel>> postData(@Body JSONObject json);
    @GET("/repositories")
    Call<List<ProductModel>> getData();

}
