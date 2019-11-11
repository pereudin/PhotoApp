package ru.pereudin.photoapp.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.pereudin.photoapp.model.entity.Photo;

public interface IApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
