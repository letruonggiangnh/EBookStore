package com.example.ebookstore.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookstore.Adapter.BookListAdapter;
import com.example.ebookstore.Adapter.TrendingBookAdapter;
import com.example.ebookstore.Constants;
import com.example.ebookstore.Model.ViewModel.BookViewModel;
import com.example.ebookstore.R;
import com.example.ebookstore.Retrofit.ProductBookService;
import com.example.ebookstore.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCategoryBook extends AppCompatActivity {
    private RecyclerView bookListRecyclerView;
    private List<BookViewModel> productBookList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_category_book_activity);
        anhxa();
        getBookList();
    }

    private void anhxa() {
        bookListRecyclerView = findViewById(R.id.listBookRecyclerView);
    }

    private void getBookList() {
        try {
            Intent intent = getIntent();
            int parentId = intent.getIntExtra("categoryId",0);
            GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false);
            bookListRecyclerView.setLayoutManager(linearLayoutManager);
            String baseUrl = Constants.Base_url;
            ProductBookService productBookService = RetrofitService.getClient(baseUrl).create(ProductBookService.class);
            Call<List<BookViewModel>> call = productBookService.getBookByParentId(parentId);
            call.enqueue(new Callback<List<BookViewModel>>() {
                @Override
                public void onResponse(@NonNull Call<List<BookViewModel>> call, @NonNull Response<List<BookViewModel>> response) {
                    productBookList = response.body();
                    BookListAdapter adapter = new BookListAdapter(productBookList);
                    adapter.setOnItemClickListener(new TrendingBookAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BookViewModel book) {
                            Intent intent = new Intent(DetailCategoryBook.this, BookDetailActivity.class);
                            intent.putExtra("product_id", book.getBookId());
                            intent.putExtra("product_name", book.getBookName());
                            intent.putExtra("product_price",book.getBookPrice());
                            intent.putExtra("product_discount", book.getDiscount());
                            intent.putExtra("product_img",book.getImageUrl());
                            intent.putExtra("product_price_discounted",book.getBookDiscountedPrice());
                            startActivity(intent);
                        }
                    });
                    bookListRecyclerView.setAdapter(adapter);
                }
                @Override
                public void onFailure(@NonNull Call<List<BookViewModel>> call, @NonNull Throwable t) {

                }
            });
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}
