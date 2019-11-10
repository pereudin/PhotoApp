package ru.pereudin.photoapp.present;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.pereudin.photoapp.model.DetailModel;
import ru.pereudin.photoapp.model.entity.Hit;
import ru.pereudin.photoapp.model.entity.Photo;
import ru.pereudin.photoapp.model.retrofit.ApiHelper;
import ru.pereudin.photoapp.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public static final String TAG = "DetailPresenter";

    private DetailModel model;
    private ApiHelper apiHelper;
    private List<Hit> hitList;

    public DetailPresenter() {
        model = new DetailModel();
        apiHelper = new ApiHelper();
    }

//    public void requestDetails() {
//        Single<Integer> single = model.showDetails();
//        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(noDetails -> {
//        getViewState().setImage(noDetails);
//        });
//    }

    public void transferPosition(String positionNumber) {
        model.setPositionNumber(positionNumber);

        Log.d(TAG, "position = " + positionNumber);
    }

    public void getOnePhoto(int number) {
        Observable<Photo> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {

            hitList = photos.hits;

            getViewState().setImageURL(hitList.get(number - 1).largeImageURL);

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }
}
