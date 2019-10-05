package ru.pereudin.photoapp.present;

import android.util.Log;

import java.util.List;

import ru.pereudin.photoapp.model.Data;
import ru.pereudin.photoapp.view.IViewHolder;

public class MainPresenter {

    public static final String TAG = "MainPresent";
    Data data = new Data();
    
    RecyclerMainPresenter recyclerMainPresenter = new RecyclerMainPresenter();

    private class RecyclerMainPresenter implements IRecyclerMainPresenter {


        private List<Integer> list = data.getList();

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(list.get(holder.getPos()));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }

    public RecyclerMainPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    public void counter() {
        int sum = data.getNum() + 1;
        data.setNum(sum);

        Log.d(TAG, "bindView: " + sum);
    }

}
