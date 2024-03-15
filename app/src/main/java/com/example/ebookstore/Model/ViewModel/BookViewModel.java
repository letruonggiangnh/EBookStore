package com.example.ebookstore.Model.ViewModel;

import com.google.gson.annotations.SerializedName;

public class BookViewModel {
    @SerializedName("bookId")
    public int bookId ;
    @SerializedName("bookName")
    public String bookName;
    @SerializedName("discount")
    private int discount ;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("bookPrice")
    private int bookPrice;
    @SerializedName("bookDiscountedPrice")
    public int BookDiscountedPrice;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }
    public int getBookDiscountedPrice() {
        return BookDiscountedPrice;
    }
    public void setBookDiscountedPrice(int bookDiscountedPrice) {
        BookDiscountedPrice = bookDiscountedPrice;
    }
}
