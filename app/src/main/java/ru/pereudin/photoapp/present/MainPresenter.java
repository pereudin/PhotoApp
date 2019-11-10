package ru.pereudin.photoapp.present;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

import ru.pereudin.photoapp.model.Data;
import ru.pereudin.photoapp.model.entity.Hit;
import ru.pereudin.photoapp.model.entity.Photo;
import ru.pereudin.photoapp.model.retrofit.ApiHelper;
import ru.pereudin.photoapp.view.IViewHolder;
import ru.pereudin.photoapp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public static final String TAG = "MainPresenter";

    private RecyclerMain recyclerMain;
    private ApiHelper apiHelper;
    private List<Hit> hitList;
    

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        recyclerMain = new RecyclerMain();
        apiHelper = new ApiHelper();
    }


    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            //Log.d(TAG, "onNext: " + photos.totalHits);

//            for (Photo.Hit hit : photos.hits) {
//                Log.d(TAG, "getAllPhoto: " + hit.webformatURL);
//            }
            hitList = photos.hits;

            getViewState().updateRecyclerView();

        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
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

}
