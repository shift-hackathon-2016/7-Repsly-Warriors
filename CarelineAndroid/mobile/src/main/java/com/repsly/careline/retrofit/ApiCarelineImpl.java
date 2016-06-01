package com.repsly.careline.retrofit;

import android.util.Base64;
import android.util.Log;

import com.repsly.careline.interfaces.ILogin;
import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.model.Medicine;
import com.repsly.careline.model.MedicineConfirmation;
import com.repsly.careline.model.Schedule;
import com.repsly.careline.model.TrackingEvent;
import com.repsly.careline.model.User;
import com.repsly.careline.model.network.ServerStatus;
import com.repsly.careline.model.network.UserData;
import com.repsly.careline.model.network.UserPassModel;
import com.repsly.careline.retrofit.interfaces.ApiCarelineInterface;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ApiCarelineImpl {

    String url = "http://api.carelineapp.me/";
    OkHttpClient.Builder client;

    public ApiCarelineImpl() {
    }

    public ApiCarelineImpl buildInterceptor() {
        HttpLoggingInterceptor hli = new HttpLoggingInterceptor();
        hli.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder();
        client.addInterceptor(hli);
        return this;
    }

    /**
     * Method for setting auth header.
     *
     * @return
     */
    public ApiCarelineImpl addAuthHeader(final String basic) {
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                                                         .header("Authorization", basic)
                                                         .header("Accept", "application/json")
                                                         .method(original.method(),
                                                                 original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return this;
    }

    public void sendLoginData(final ILogin login) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<UserData> sendData = apiCarelineService
                .sendUserPass();
        sendData.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call,
                                   retrofit2.Response<UserData> response) {
                if (response.body() != null) {
                    login.LoginResult(response.body());
                } else {
                    login.LoginResult(null);
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                login.LoginResult(null);
            }
        });
    }

    public List<CareReceiver> getRecevierList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<List<CareReceiver>> receivers = apiCarelineService.getReceiverList();
        try {
            return receivers.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Schedule> getSchedules() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<List<Schedule>> receivers = apiCarelineService.getScheduleForMobileUser();
        try {
            return receivers.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Medicine> getMedicine() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<List<Medicine>> receivers = apiCarelineService.getMedicine();
        try {
            return receivers.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMedicineConfirmation(MedicineConfirmation mc) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<Void> service = apiCarelineService.sendMedicineConformation(mc);
        service.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                Log.d("Repsly debug message", "SENT!");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Repsly debug message", "FAILED!");
            }
        });
    }

    public void sendUserTracking(TrackingEvent te) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<Void> service = apiCarelineService.sendTrackingEvent(te);
        service.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                Log.d("Repsly debug message", "SENT!");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Repsly debug message", "FAILED!");
            }
        });
    }
}