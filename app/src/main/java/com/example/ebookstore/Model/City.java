package com.example.ebookstore.Model;
import com.google.gson.annotations.SerializedName;
public class City {
    @SerializedName("ProvinceID")
    private int ProvinceId;
    @SerializedName("ProvinceName")
    private String ProvinceName;
    @SerializedName("CountryID")
    private int CountryID;
    @SerializedName("Code")
    private int Code;
    @SerializedName("NameExtension")
    private String[] NameExtension;
    @SerializedName("IsEnable")
    private int IsEnable;
    @SerializedName("RegionID")
    private int RegionID;
    @SerializedName("UpdatedBy")
    private int UpdatedBy;
    @SerializedName("CreatedAt")
    private String CreatedAt;
    @SerializedName("UpdatedAt")
    private String UpdatedAt;
    @SerializedName("CanUpdateCOD")
    private String CanUpdateCOD;
    @SerializedName("Status")
    private int Status;

    public int getProvinceId() {
        return ProvinceId;
    }
    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String[] getNameExtension() {
        return NameExtension;
    }

    public void setNameExtension(String[] nameExtension) {
        NameExtension = nameExtension;
    }

    public int getIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(int isEnable) {
        IsEnable = isEnable;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int regionID) {
        RegionID = regionID;
    }

    public int getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getCanUpdateCOD() {
        return CanUpdateCOD;
    }

    public void setCanUpdateCOD(String canUpdateCOD) {
        CanUpdateCOD = canUpdateCOD;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
