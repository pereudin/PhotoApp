package ru.pereudin.photoapp.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

import ru.pereudin.photoapp.R;
import ru.pereudin.photoapp.present.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_activity_main);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter(presenter.getRecyclerMainPresenter());
        recyclerView.setAdapter(myAdapter);
    }

    public void imageClick(View view) {
        presenter.counter();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_KEY, Integer.toString((Integer)view.getTag()));
        startActivity(intent);
    }

    @Override
    public void totalClicks(int amount) {
        Log.d(TAG, "totalClicks: " + amount);
    }
}
