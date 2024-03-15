package com.example.ebookstore.Model;

import com.google.gson.annotations.SerializedName;

public class ProductBook {
    @SerializedName("bookId")
    public int bookId ;
    @SerializedName("bookName")
    public String bookName;
    @SerializedName("author")

    private String author;
    @SerializedName("discount")

    private int discount ;
    @SerializedName("packagingSize")

    private String packagingSize;
    @SerializedName("weight")

    private String weight;
    @SerializedName("publishingCompany")

    private String publishingCompany;
    @SerializedName("supplier")

    private String supplier;
    @SerializedName("numberOfPages")

    private int numberOfPages;
    @SerializedName("language")

    private String language;
    @SerializedName("imageUrl")

    private String imageUrl;
    @SerializedName("bookPrice")

    private int bookPrice;
    @SerializedName("tagId")

    private int tagId;
    @SerializedName("soldQuantity")

    private int soldQuantity;
    @SerializedName("categoryChildId")

    private int categoryChildId;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPackagingSize() {
        return packagingSize;
    }

    public void setPackagingSize(String packagingSize) {
        this.packagingSize = packagingSize;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }
    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
    public int getCategoryChildId() {
        return categoryChildId;
    }
    public void setCategoryChildId(int categoryChildId) {
        this.categoryChildId = categoryChildId;
    }
}
