package com.example.ebookstore.Model;

import com.google.gson.annotations.SerializedName;

public class BookDescription {
    @SerializedName("bookDescriptionId")
    private int BookDescriptionId;
    @SerializedName("bookId")
    private int BookId;
    @SerializedName("description")
    private String Description;
    public int getBookDescriptionId() {
        return BookDescriptionId;
    }
    public void setBookDescriptionId(int bookDescriptionId) {
        BookDescriptionId = bookDescriptionId;
    }
    public int getBookId() {
        return BookId;
    }
    public void setBookId(int bookId) {
        BookId = bookId;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
