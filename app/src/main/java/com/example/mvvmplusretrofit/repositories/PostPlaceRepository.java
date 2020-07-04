package com.example.mvvmplusretrofit.repositories;


import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvmplusretrofit.PostApi;
import com.example.mvvmplusretrofit.models.PostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Singleton pattern
 */
public class PostPlaceRepository {

    private static PostPlaceRepository instance;

    private ArrayList<PostModel> dataSet = new ArrayList<>();


    private final MutableLiveData<PostModel> listOfMovies = new MutableLiveData<>();

    public static PostPlaceRepository getInstance(){
        if(instance == null){
            instance = new PostPlaceRepository();
        }
        return instance;
    }




    public MutableLiveData<List<PostModel>> getPostPlaces(){
        setPostPlaces();

        MutableLiveData<List<PostModel>> data = new MutableLiveData<>();
        data.setValue(dataSet);

        return data;
    }



    private void setPostPlaces() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApi.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi postApi= retrofit.create(PostApi.class);
        Call<List<PostModel>> call = postApi.getListPost();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {


                if(response.isSuccessful()){

                    if (response.body() != null) {
                        List<PostModel> postList = response.body();


                        for(PostModel h:postList){
                            dataSet.add(h);
                        }

                    }

                }else {
                    Log.d("fail", "fail");
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("fail", t.getMessage() == null ? "" : t.getMessage());
            }

        });

    }





}











