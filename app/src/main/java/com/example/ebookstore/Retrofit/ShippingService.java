package com.example.ebookstore.Retrofit;

import com.example.ebookstore.Model.City;
import com.example.ebookstore.Model.ProvinceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ShippingService
    {
        @GET("shiip/public-api/master-data/province")
        Call<ProvinceResponse> getProvinceList(@Header("token") String token, @Header("Host") String host);
    }
