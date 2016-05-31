package com.repsly.careline.retrofit;

import android.util.Base64;

import com.repsly.careline.model.User;
import com.repsly.careline.retrofit.interfaces.ApiCarelineInterface;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ApiCarelineImpl {

    String url;
    OkHttpClient.Builder client;

    public ApiCarelineImpl() {

    }

    public ApiCarelineImpl setUrl(String url) {
        this.url = url;
        return this;
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
     * @param username
     * @param password
     * @return
     */
    public ApiCarelineImpl addAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                                                         .header("Authorization", basic)
                .header("Accept", "application/json")
                .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return this;
    }

    /**
     * TODO call it from async!
     * @return user data
     */
    public User getUserData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiCarelineInterface apiCarelineService = retrofit.create(ApiCarelineInterface.class);
        Call<User> userCall = apiCarelineService.getUserData("iddd"); //TODO id of user!
        User user = null;
        try {
            user = userCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}