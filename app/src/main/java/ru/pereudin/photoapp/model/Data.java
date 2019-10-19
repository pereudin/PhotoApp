package ru.pereudin.photoapp.model;

import java.util.ArrayList;
import java.util.List;

import ru.pereudin.photoapp.R;

public class Data {

    List<Integer> list;
    int num = 0;

    public Data() {
        list = new ArrayList<>();
        list.add(R.drawable.bmw);
        list.add(R.drawable.vw);
        list.add(R.drawable.skoda);
        list.add(R.drawable.volvo);
        list.add(R.drawable.mercedes);
        list.add(R.drawable.toyota);
        list.add(R.drawable.mazda);
        list.add(R.drawable.audi);
        list.add(R.drawable.opel);
        list.add(R.drawable.nissan);
    }

    public List<Integer> getList() {
        return list;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
