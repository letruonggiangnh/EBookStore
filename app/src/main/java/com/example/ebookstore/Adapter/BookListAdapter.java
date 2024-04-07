package com.example.ebookstore.Adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookstore.Model.ViewModel.BookViewModel;
import com.example.ebookstore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder>{
    private final List<BookViewModel> mListProductBook;
    private TrendingBookAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(BookViewModel book);
    }
    public BookListAdapter(List<BookViewModel> mListProductBook)
    {
        this.mListProductBook = mListProductBook;
    }
    public void setOnItemClickListener(TrendingBookAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public BookListAdapter.BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_category_list_view_holder, parent, false);
        return new BookListAdapter.BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookListViewHolder holder, int position) {
        BookViewModel productBook = mListProductBook.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        int discountRate = productBook.getDiscount();

        if (productBook == null) {
            return;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(productBook);
                }
            }
        });

        if (discountRate == 0) {
            int bookPrice = productBook.getBookPrice();
            String bookPriceFormatted = decimalFormat.format(bookPrice);
            holder.prodPrice.setText(bookPriceFormatted);

            // Ẩn các view liên quan đến giá gốc
            holder.discountRate.setVisibility(View.INVISIBLE);
            holder.prodPriceOriginal.setVisibility(View.INVISIBLE);
        } else if (discountRate > 0) {
            int discountedPrice = productBook.getBookDiscountedPrice();
            int originalPrice = productBook.getBookPrice();
            String formattedOriginalPrice = decimalFormat.format(originalPrice);
            String formattedPrice = decimalFormat.format(discountedPrice);

            holder.prodPrice.setText(formattedPrice);
            holder.discountRate.setText(String.valueOf(productBook.getDiscount()) + "%");
            // Hiển thị giá gốc với hiệu ứng strikethrough
            holder.prodPriceOriginal.setText(formattedOriginalPrice);
            holder.prodPriceOriginal.setPaintFlags(holder.prodPriceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // Hiển thị lại các view khi cần thiết
            holder.discountRate.setVisibility(View.VISIBLE);
            holder.prodPriceOriginal.setVisibility(View.VISIBLE);
        }

        holder.prodName.setText(productBook.getBookName());
        Picasso.get().load(productBook.getImageUrl()).into(holder.prodImage);
    }

    @Override
    public int getItemCount() {
        if(mListProductBook != null)
        {
            return mListProductBook.size();
        }
        return 0;
    }

    public class BookListViewHolder extends RecyclerView.ViewHolder {
        private final ImageView prodImage;
        private final TextView prodName;
        private final TextView prodPrice;
        private final TextView prodPriceOriginal;
        private final TextView discountRate;
        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.productImg);
            prodName = itemView.findViewById(R.id.productName);
            prodPrice = itemView.findViewById(R.id.productPrice);
            prodPriceOriginal = itemView.findViewById(R.id.bookPriceOriginal);
            discountRate = itemView.findViewById(R.id.discount_rate);
        }
    }
}
