package com.repsly.careline.retrofit.interfaces;

import com.repsly.careline.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tosulc on 31.05.2016..
 */
public interface ApiCarelineInterface  {

    @GET
    Call<User> getUserData(@Path("userId") String userId);

    /*@GET("users/{user}/repos")
    Call<List<OneModel>> listRepos(@Path("user") String user);*/

}
