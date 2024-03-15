package com.example.ebookstore.Retrofit;

import com.example.ebookstore.Model.BookDescription;
import com.example.ebookstore.Model.ProductBook;
import com.example.ebookstore.Model.ViewModel.BookViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductBookService {
        @GET("api/ProductBook/trending")
        Call<List<BookViewModel>> getTrendingBooks();
        @GET("api/ProductBook/book?")
        Call<ProductBook> getBookById(@Query("id") int id);
        @GET("api/ProductBook/bookdescription?")
        Call<List<BookDescription>> getBookDescById(@Query("id") int id);
}
