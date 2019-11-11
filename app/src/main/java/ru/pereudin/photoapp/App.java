package ru.pereudin.photoapp;

import android.app.Application;
import androidx.room.Room;
import ru.pereudin.photoapp.model.room.AppDatabase;


public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "photos_database").build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
