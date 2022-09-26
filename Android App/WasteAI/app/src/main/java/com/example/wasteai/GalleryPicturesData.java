package com.example.wasteai;

public class GalleryPicturesData {
    private Double Latitude;
    private Double Longitude;
    private String Country;
    private String Locality;
    private String Address;
    private String QueryID;
    private String UserNo;


    public GalleryPicturesData() {
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getQueryID() {
        return QueryID;
    }

    public void setQueryID(String queryID) {
        QueryID = queryID;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String userNo) {
        UserNo = userNo;
    }
}
