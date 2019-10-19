package ru.pereudin.photoapp.model;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import ru.pereudin.photoapp.R;

public class DetailModel {

    private String positionNumber;

    public Single<Integer> showDetails() {

        Single<Integer> single = Single.create((SingleOnSubscribe<Integer>) emitter -> {
            int noDetails = R.drawable.no_details;
            emitter.onSuccess(noDetails);
        }).subscribeOn(Schedulers.io());

        return single;

    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }
}
