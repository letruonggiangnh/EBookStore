package com.example.ebookstore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ebookstore.Model.ImageSlider;
import com.example.ebookstore.R;

import java.util.List;

public class ImageViewPagerAdapter extends PagerAdapter {

    private final List<ImageSlider> mListImageSlider;

    public ImageViewPagerAdapter(List<ImageSlider> mListImageSlider) {
        this.mListImageSlider = mListImageSlider;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image_slider, container, false);
        ImageView imgPhoto = view.findViewById(R.id.imgSlider);

        ImageSlider imageSlider = mListImageSlider.get(position);
        imgPhoto.setImageResource(imageSlider.getResourceId());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mListImageSlider != null)
        {
            return mListImageSlider.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
