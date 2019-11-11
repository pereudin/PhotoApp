package ru.pereudin.photoapp.model.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PhotosDataDao {

    @Query("SELECT * FROM table_photos")
    Single<List<PhotosData>> getAll();

    @Query("SELECT * FROM table_photos WHERE id = :id")
    Single<List<PhotosData>> getPhotosById(int id);

    @Insert
    Single<Long> insert(PhotosData photosData);

    @Insert
    Single<List> insertList(List<PhotosData> photosData);

    @Delete
    Single<Integer> delete(PhotosData photosData);

    @Update
    Single<Integer> update(PhotosData photosData);

}
