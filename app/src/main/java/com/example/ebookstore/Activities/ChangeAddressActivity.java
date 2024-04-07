package com.example.ebookstore.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebookstore.Adapter.TrendingBookAdapter;
import com.example.ebookstore.Constants;
import com.example.ebookstore.Model.City;
import com.example.ebookstore.Model.ProvinceResponse;
import com.example.ebookstore.Model.ViewModel.BookViewModel;
import com.example.ebookstore.R;
import com.example.ebookstore.Retrofit.RetrofitService;
import com.example.ebookstore.Retrofit.ShippingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeAddressActivity extends AppCompatActivity {
    private Spinner cityDropdown, districtDropdown, wardDropdown;
    private String PROVINCE_API = Constants.API_GHN;
    private String TOKEN = Constants.TOKEN_GHN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_address_activity);
        anhxa();
        getSupportActionBar().hide();
        getCityList();
    }
    private void getDistrictList() {
        String[] listCity = new String[] {"Nam Định", "Hà Nội"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listCity);
        districtDropdown.setAdapter(adapter);
        getWardList();
    }
    private void getWardList() {
        String[] listWard = new String[] {"Nam Định", "Hà Nội"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listWard);

        wardDropdown.setAdapter(adapter);
    }
    private void getCityList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PROVINCE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ShippingService shippingService = retrofit.create(ShippingService.class);
        Call<ProvinceResponse> call = shippingService.getProvinceList(TOKEN, "online-gateway.ghn.vn");
        Log.d("API", "URL: " + call.request().url());
        call.enqueue(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                if (response.isSuccessful()) {
                    ProvinceResponse provinceResponse = response.body();
                    if (provinceResponse != null) {
                        City[] provinceData = provinceResponse.getData();
                        String[] listCityName = new String[provinceData.length];
                        int index = 0;
                        for (City city : provinceData) {
                            String cityName = city.getProvinceName();
                            listCityName[index] = cityName;
                            index++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChangeAddressActivity.this, android.R.layout.simple_spinner_dropdown_item, listCityName);
                        cityDropdown.setAdapter(adapter);
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                Log.d("API", "URL: " + t.toString());
            }
        });


    }
    private void anhxa() {
        cityDropdown = findViewById(R.id.citySpinner);
        districtDropdown = findViewById(R.id.districtSpinner);
        wardDropdown = findViewById(R.id.wardSpinner);
    }
}
