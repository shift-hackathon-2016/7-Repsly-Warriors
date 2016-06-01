package com.repsly.careline.retrofit.interfaces;

import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.model.Medicine;
import com.repsly.careline.model.MedicineConfirmation;
import com.repsly.careline.model.Schedule;
import com.repsly.careline.model.User;
import com.repsly.careline.model.network.ServerStatus;
import com.repsly.careline.model.network.UserData;
import com.repsly.careline.model.network.UserPassModel;

import java.util.List;

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

    @GET("/api/CareReceiver/GetList")
    Call<List<CareReceiver>> getReceiverList();

    @GET("/api/Schedule/GetScheduleForMobileUser")
    Call<List<Schedule>> getScheduleForMobileUser(); //getting schedule and schedule items

    @GET("/api/Medicine/GetList")
    Call<List<Medicine>> getMedicine();


    @GET
    Call<User> getUserData(@Path("userId") String userId);

    @POST("/api/Schedule/MedicineConfirmation")
    Call<Void> sendMedicineConformation(@Body MedicineConfirmation mc);

    @POST()
    Call<Void> sendCurrentLocation(); //TODO implement
}
