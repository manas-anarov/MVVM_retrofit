package com.example.mvvmplusretrofit.viewmodels;



import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmplusretrofit.models.PostModel;
import com.example.mvvmplusretrofit.repositories.PostPlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private MutableLiveData<List<PostModel>> mPostPlaces;
    private PostPlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mPostPlaces != null){
            return;
        }
        mRepo = PostPlaceRepository.getInstance();
        mPostPlaces = mRepo.getPostPlaces();
    }

    public void addNewValue(final PostModel nicePlace){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<PostModel> currentPlaces = mPostPlaces.getValue();
                currentPlaces.add(nicePlace);
                mPostPlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }





    public LiveData<List<PostModel>> getPostPlaces(){
        return mPostPlaces;
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
