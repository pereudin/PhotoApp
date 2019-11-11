package ru.pereudin.photoapp.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhotosData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotosDataDao photosDataDao();
}
