package com.repsly.careline.retrofit.interfaces;

import com.repsly.careline.model.MedicineConfirmation;
import com.repsly.careline.model.Schedule;
import com.repsly.careline.model.User;
import com.repsly.careline.model.network.ServerStatus;
import com.repsly.careline.model.network.UserData;
import com.repsly.careline.model.network.UserPassModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tosulc on 31.05.2016..
 */
public interface ApiCarelineInterface  {

    @GET("/api/Account/MobileGetUserData")
    Call<UserData> sendUserPass();

    @GET
    Call<User> getUserData(@Path("userId") String userId);

    @GET("/api/Schedule/GetScheduleForMobileUser")
    Call<Schedule> getScheduleForMobileUser(); //getting schedule and schedule items

    @POST("/api/Schedule/MedicineConfirmation")
    Call<Void> sendMedicineConformation(@Body MedicineConfirmation mc);

    @POST()
    Call<Void> sendCurrentLocation(); //TODO implement
}
