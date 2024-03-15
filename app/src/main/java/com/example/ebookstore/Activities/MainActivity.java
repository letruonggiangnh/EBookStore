package com.example.ebookstore.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ebookstore.Adapter.ImageViewPagerAdapter;
import com.example.ebookstore.Adapter.TrendingBookAdapter;
import com.example.ebookstore.Constants;
import com.example.ebookstore.Model.ImageSlider;
import com.example.ebookstore.Model.ViewModel.BookViewModel;
import com.example.ebookstore.R;
import com.example.ebookstore.Retrofit.ProductBookService;
import com.example.ebookstore.Retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView trendingBookRecyclerView;
    private ProductBookService productBookService;
    private List<BookViewModel> productBookList;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        getListImgSlider();
        getTrendingBooks();
    }

    private void getListImgSlider()
    {
        List<ImageSlider> list = new ArrayList<>();
        list.add(new ImageSlider(R.drawable.banner_0803));
        list.add(new ImageSlider(R.drawable.banner_book));
        list.add(new ImageSlider(R.drawable.banner_kim_dong));
        list.add(new ImageSlider(R.drawable.banner_fahasa));

        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(list);
        mViewPager.setAdapter(adapter);

        mCircleIndicator.setViewPager(mViewPager);
        mRunnable = new Runnable() {
        @Override
        public void run() {
                if(mViewPager.getCurrentItem() == list.size() -1)
                {
                    mViewPager.setCurrentItem(0);
                }else
                {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                }
        }
        };
        mHandler.postDelayed(mRunnable, 2000);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void getTrendingBooks() {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            trendingBookRecyclerView.setLayoutManager(linearLayoutManager);
            String baseUrl = Constants.Base_url;
            productBookService = RetrofitService.getClient(baseUrl).create(ProductBookService.class);
            Call<List<BookViewModel>> call = productBookService.getTrendingBooks();
            call.enqueue(new Callback<List<BookViewModel>>() {
                @Override
                public void onResponse(@NonNull Call<List<BookViewModel>> call, @NonNull Response<List<BookViewModel>> response) {
                    productBookList = response.body();
                    TrendingBookAdapter adapter = new TrendingBookAdapter(productBookList);
                    adapter.setOnItemClickListener(new TrendingBookAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BookViewModel book) {
                            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                            intent.putExtra("product_id", book.getBookId());
                            intent.putExtra("product_name", book.getBookName());
                            intent.putExtra("product_price",book.getBookPrice());
                            intent.putExtra("product_discount", book.getDiscount());
                            intent.putExtra("product_img",book.getImageUrl());
                            intent.putExtra("product_price_discounted",book.getBookDiscountedPrice());
                            startActivity(intent);
                        }
                    });

                    trendingBookRecyclerView.setAdapter(adapter);
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
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }

    private void anhxa() {
        trendingBookRecyclerView = findViewById(R.id.thinhHanhRecyclerView);
        mViewPager = findViewById(R.id.main_slider_image);
        mCircleIndicator = findViewById(R.id.circle_indicator);
    }
}