package com.course.implement.newsapp.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.course.implement.newsapp.utils.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HTTP {

    private static Retrofit retrofit;

    public static void initialize(){
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public static <T> T create(Class<T> object) {
        //initialize(App.getConfig());
        if (retrofit == null) {
            initialize();
        }
        return retrofit.create(object);
    }

    public static <T> T create(Class<T> object, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(object);
    }

    private static OkHttpClient getHeader() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String s) {
                Log.e("Interceptor", s);
            }
        });
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient okHttpClient = new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .connectTimeout(15, TimeUnit.SECONDS)
                 .readTimeout(15, TimeUnit.SECONDS)
                 .addNetworkInterceptor(new Interceptor() {
                     @NonNull
                     @Override
                     public Response intercept(@NonNull Chain chain) throws IOException {
                         Request request = chain.request();
                         Request.Builder requestBuild = request.newBuilder();
                         requestBuild.addHeader("Content_Type", Config.MULTIPART);
                         requestBuild.addHeader("ACCEPT", Config.APPLICATION_JSON);
                         request = requestBuild.build();

                         return chain.proceed(request);
                     }
                 }).build();

         return okHttpClient;
    }
}
