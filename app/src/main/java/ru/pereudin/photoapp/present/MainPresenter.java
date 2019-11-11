package ru.pereudin.photoapp.present;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

import ru.pereudin.photoapp.App;
import ru.pereudin.photoapp.model.entity.Hit;
import ru.pereudin.photoapp.model.entity.Photo;
import ru.pereudin.photoapp.model.retrofit.ApiHelper;
import ru.pereudin.photoapp.model.room.PhotosData;
import ru.pereudin.photoapp.model.room.PhotosDataDao;
import ru.pereudin.photoapp.view.IViewHolder;
import ru.pereudin.photoapp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public static final String TAG = "MainPresenter";

    private RecyclerMain recyclerMain;
    private ApiHelper apiHelper;
    private PhotosDataDao photosDataDao;
    private List<Hit> hitList;
    private List<PhotosData> photosList;
    private PhotosData photosData;
    

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        recyclerMain = new RecyclerMain();
        apiHelper = new ApiHelper();
        photosDataDao = App.getAppDatabase().photosDataDao();
        photosList = new ArrayList<>();
        photosData = new PhotosData();
    }


    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {


            for (Hit hit : photos.hits) {

                photosData.urlWeb = hit.webformatURL;
                photosData.urlLarge = hit.largeImageURL;
                photosList.add(photosData);

                Log.d(TAG, "putData: " + photosData.urlLarge);

            }

            hitList = photos.hits;

            putData();

            getViewState().updateRecyclerView();

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });

        putData();

    }

    private class RecyclerMain implements IRecyclerMainPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(hitList.get(holder.getPos()).webformatURL);
            holder.setTag(holder.getPos()+1);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

    }

    public RecyclerMain getRecyclerMain() {
        return recyclerMain;
    }

    public void putData() {

        Disposable disposable = photosDataDao.insertList(photosList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "putData: " + id);
                }, throwable -> {
                    Log.d(TAG, "putData: " + throwable);
                });
    }

}
