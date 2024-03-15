package com.example.ebookstore.Activities;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebookstore.Constants;
import com.example.ebookstore.Model.BookDescription;
import com.example.ebookstore.Model.ProductBook;
import com.example.ebookstore.R;
import com.example.ebookstore.Retrofit.ProductBookService;
import com.example.ebookstore.Retrofit.RetrofitService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class BookDetailActivity extends AppCompatActivity {
    private TextView bookNameTextView;
    private TextView bookPriceTextView;
    private TextView bookOriginPriceTextView;
    private TextView discountRate;
    private TextView supplierTextview;
    private TextView authorTextView;
    private TextView publishingCompanyTextView;
    private TextView bookDescription1, bookDescription2;
    private ImageView productImg;
    String baseUrl = Constants.Base_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        anhxa();
        displayProductDetail();
        getBookDetailById();
        getBookDescription();
    }
    private void displayProductDetail() {
        try{
            Intent intent = getIntent();
            DecimalFormat decimalFormat = new DecimalFormat("###,### đ");

            int productId = intent.getIntExtra("product_id",0);

            String productName = intent.getStringExtra("product_name");

            int originPrice = intent.getIntExtra("product_price",0);
            String originPriceFormatted = decimalFormat.format(originPrice);

            int productDiscount = intent.getIntExtra("product_discount",0);
            String productDiscountFormatted = String.valueOf(productDiscount);

            int productPriceDiscounted = intent.getIntExtra("product_price_discounted", 0);
            String productPriceFormatted = decimalFormat.format(productPriceDiscounted);

            String prodImage = intent.getStringExtra("product_img");

            if(productDiscount > 0)
            {
                bookNameTextView.setText(productName);
                bookPriceTextView.setText(productPriceFormatted);
                bookOriginPriceTextView.setText(originPriceFormatted);
                bookOriginPriceTextView.setPaintFlags(bookOriginPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                discountRate.setText(String.format("%s %%", productDiscountFormatted));
                Picasso.get().load(prodImage).into(productImg);
            }
            else if (productDiscount == 0)
            {
                bookNameTextView.setText(productName);
                bookPriceTextView.setText(originPriceFormatted);
                Picasso.get().load(prodImage).into(productImg);
                discountRate.setVisibility(View.INVISIBLE);
                bookOriginPriceTextView.setVisibility(View.INVISIBLE);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    private void getBookDescription() {
        try{
            Intent intent = getIntent();
            int productId = intent.getIntExtra("product_id",0);
            ProductBookService productBookService = RetrofitService.getClient(baseUrl).create(ProductBookService.class);
            Call<List<BookDescription>> call = productBookService.getBookDescById(productId);
            call.enqueue(new Callback<List<BookDescription>>() {
                @Override
                public void onResponse(@NonNull Call<List<BookDescription>> call, @NonNull Response<List<BookDescription>> response) {
                    if(response.body() != null)
                    {
                        List<BookDescription> bookDescriptions = response.body();
                        if(bookDescriptions.size() > 0)
                        {
                            bookDescription1.setText(bookDescriptions.get(0).getDescription());
                            bookDescription2.setText(bookDescriptions.get(1).getDescription());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<BookDescription>> call, @NonNull Throwable t) {

                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getBookDetailById() {
        Intent intent = getIntent();
        int productId = intent.getIntExtra("product_id",0);
        try {
            ProductBookService productBookService = RetrofitService.getClient(baseUrl).create(ProductBookService.class);
            Call<ProductBook> call = productBookService.getBookById(productId);
            call.enqueue(new Callback<ProductBook>() {
                @Override
                public void onResponse( Call<ProductBook> call, Response<ProductBook> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body()!=null)
                        {
                            ProductBook book = response.body();

                            String authorText = book.getAuthor();
                            authorTextView.setText(authorText);

                            String supplierText = book.getSupplier();
                            supplierTextview.setText(supplierText);

                            String publishingCompanyText = book.getPublishingCompany();
                            publishingCompanyTextView.setText(publishingCompanyText);
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ProductBook> call, @NonNull Throwable t) {
                }
            });
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    private void anhxa() {
        bookNameTextView = findViewById(R.id.book_name);
        bookPriceTextView = findViewById(R.id.book_price);
        bookOriginPriceTextView = findViewById(R.id.origin_price);
        discountRate = findViewById(R.id.discount_rate);
        productImg = findViewById(R.id.prodImg);
        supplierTextview = findViewById(R.id.supplier);
        authorTextView = findViewById(R.id.author);
        publishingCompanyTextView = findViewById(R.id.publishingCompany);
        bookDescription1 = findViewById(R.id.bookDescription1);
        bookDescription2 = findViewById(R.id.bookDescription2);
    }
}
