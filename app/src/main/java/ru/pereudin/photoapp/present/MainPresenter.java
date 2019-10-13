package ru.pereudin.photoapp.present;

import android.util.Log;

import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;

import ru.pereudin.photoapp.model.Data;
import ru.pereudin.photoapp.view.IViewHolder;
import ru.pereudin.photoapp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public static final String TAG = "MainPresenter";
    Data data = new Data();
    
    RecyclerMainPresenter recyclerMainPresenter = new RecyclerMainPresenter();

    private class RecyclerMainPresenter implements IRecyclerMainPresenter {


        private List<Integer> list = data.getList();

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(list.get(holder.getPos()));
            holder.setTag(holder.getPos()+1);
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

        getViewState().totalClicks(sum);

        Log.d(TAG, "bindView: " + sum);
    }
}
