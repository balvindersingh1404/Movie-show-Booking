package com.example.balvinder.bookYourShow.rest;

import com.example.balvinder.bookYourShow.model.MovieDetailResponseModel;
import com.example.balvinder.bookYourShow.model.theatreData.ApiResponseTheatre;
import com.example.balvinder.bookYourShow.model.ContactDetails;
import com.example.balvinder.bookYourShow.model.GetToken;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.LoginDetails;
import com.example.balvinder.bookYourShow.model.MFYResponse;
import com.example.balvinder.bookYourShow.model.MovieResponseModel;
import com.example.balvinder.bookYourShow.model.OfferResponseModel;
import com.example.balvinder.bookYourShow.model.SearchResponseModel;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("generateToken")
    Call<ApiResponse> getToken(@Body GetToken getToken);

    @GET("other/getSplashText")
    Call<ApiResponse> getSplashText();

    @POST("login")
    Call<ApiResponse> getToken(@Body LoginDetails loginDetails);

    @POST("register/email")
    Call<ApiResponse> registerUser(@Body LoginDetails loginDetails);


    @POST("social/login")
    Call<ApiResponse> socialLogin(@Body LoginDetails loginDetails);

    @GET("location/listcity")
    Call<ApiResponse> getLocation();


    @POST("user/forgetPassword")
    Call<ApiResponse>getOtp(
            @Body LoginDetails loginDetails);
    @POST("user/resetPassword")
    Call<ApiResponse>getnewpassword(
            @Body LoginDetails loginDetails);

    @GET("movies/moviesForYou/{id}")
    Call<MFYResponse> getMovies(@Path("id") String id);


    @POST("contact-us")
    Call<ApiResponse> setContactUs(
            @Body ContactDetails contactDetails);

    @GET("/location/listcity/{latitude}/{longitude}")
    Call<ApiResponse> getLocation(@Path("latitude") String latitude, @Path("longitude") String longitude);


    @GET("/movies/comingSoon")
    Call<MovieResponseModel> getmovies();

    @GET("/search")
    Call<SearchResponseModel> getMoviedetails(@Query("cityId") String cityId);

    @GET("/search?cityId=1")
    Call<SearchResponseModel> getMoviedetails1();


    @GET("/movies/comingSoon")
    Call<MovieResponseModel> getComingSoonMovies(@Query("language") String language);


    @GET("/theater/allTheater?cityId=1&lat=28.6252151&lang=77.3734901")
    Call<ApiResponseTheatre> gettheatres();

    @GET("/offer/list")
    Call<OfferResponseModel> getoffers();

    @GET("/movies/details?movieId=NHO8955372&language=Hindi&cityId=1")
    Call<MovieDetailResponseModel> getMoviesDetail();

    @GET("/movies/details")
    Call<MovieDetailResponseModel> getMoviesDetail(@Query("movieId") String movieID, @Query("language") String languageID, @Query("cityId") String cityID);



}