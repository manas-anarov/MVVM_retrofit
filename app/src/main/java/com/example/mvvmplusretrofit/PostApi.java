package com.example.mvvmplusretrofit;




import com.example.mvvmplusretrofit.models.PostModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;



public interface PostApi {


    String root = "http://192.168.0.104:8000/";

    String API_URL = root + "api/v1/";


    @GET("post/list/")
    Call<List<PostModel>> getListPost();


}