package ru.pereudin.photoapp.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_photos")
public class PhotosData {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String urlWeb;

    public String urlLarge;

}
