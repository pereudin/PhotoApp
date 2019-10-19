package ru.pereudin.photoapp.present;

import android.util.Log;

import moxy.InjectViewState;
import moxy.MvpPresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.pereudin.photoapp.model.DetailModel;
import ru.pereudin.photoapp.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public static final String TAG = "DetailPresenter";

    private DetailModel model;

    public DetailPresenter() {
        model = new DetailModel();
    }

    public void requestDetails() {
        Single<Integer> single = model.showDetails();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(noDetails -> {
        getViewState().setImage(noDetails);
        });
    }

    public void transferPosition(String positionNumber) {
        model.setPositionNumber(positionNumber);

        Log.d(TAG, "position = " + positionNumber);
    }
}
