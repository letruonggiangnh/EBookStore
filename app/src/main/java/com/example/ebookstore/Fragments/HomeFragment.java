package com.example.ebookstore.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ebookstore.Activities.BookDetailActivity;
import com.example.ebookstore.Activities.DetailCategoryBook;
import com.example.ebookstore.Activities.MainActivity;
import com.example.ebookstore.Adapter.ImageViewPagerAdapter;
import com.example.ebookstore.Adapter.TrendingBookAdapter;
import com.example.ebookstore.Constants;
import com.example.ebookstore.Model.ImageSlider;
import com.example.ebookstore.Model.ViewModel.BookViewModel;
import com.example.ebookstore.R;
import com.example.ebookstore.Retrofit.ProductBookService;
import com.example.ebookstore.Retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView trendingBookRecyclerView;
    private ProductBookService productBookService;
    private List<BookViewModel> productBookList;
    private ImageView vanhocImgView, kinhTeImgView,tamLyImgView,giaoDucImgView,thieuNhiImgView,hoiKyImgView,giaoKhoaImgView,ngoaiNguImgView;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        getListImgSlider();
        getTrendingBooks();
        allCategory();
    }
    public void anhxa(View view) {
        trendingBookRecyclerView = view.findViewById(R.id.thinhHanhRecyclerView);
        mViewPager = view.findViewById(R.id.main_slider_image);
        mCircleIndicator = view.findViewById(R.id.circle_indicator);
        vanhocImgView = view.findViewById(R.id.van_hoc_icon);
        kinhTeImgView = view.findViewById(R.id.kinh_te_icon);
        tamLyImgView = view.findViewById(R.id.tam_ly_icon);
        giaoDucImgView = view.findViewById(R.id.giaoDucImageView);
        thieuNhiImgView = view.findViewById(R.id.thieu_nhi);
        hoiKyImgView = view.findViewById(R.id.tieu_su_hoi_ky);
        giaoKhoaImgView = view.findViewById(R.id.giaoKhoaIcon);
        ngoaiNguImgView = view.findViewById(R.id.ngoai_ngu);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
                            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
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
    private void setCategoryClickListener(final ImageView imageView, final int categoryId) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailCategoryBook.class);
                intent.putExtra("categoryId", categoryId);
                startActivity(intent);
            }
        });
    }
    private void allCategory() {
        setCategoryClickListener(vanhocImgView, 1);
        setCategoryClickListener(kinhTeImgView, 2);
        setCategoryClickListener(tamLyImgView, 3);
        setCategoryClickListener(giaoDucImgView, 4);
        setCategoryClickListener(thieuNhiImgView, 5);
        setCategoryClickListener(hoiKyImgView, 6);
        setCategoryClickListener(giaoKhoaImgView, 7);
        setCategoryClickListener(ngoaiNguImgView, 8);
    }
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }
}
