package ru.pereudin.photoapp.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

import ru.pereudin.photoapp.R;
import ru.pereudin.photoapp.present.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";

    private MainAdapter mainAdapter;

    @BindView(R.id.recycler_view_activity_main)
    RecyclerView recyclerView;

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

        ButterKnife.bind(this);

        initRecyclerView();
    }

    private void initRecyclerView() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(this, presenter.getRecyclerMain());
        recyclerView.setAdapter(mainAdapter);
    }


    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        mainAdapter.notifyDataSetChanged();
    }

    public void imageClick(View view) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_KEY, Integer.toString((Integer)view.getTag()));
        intent.putExtra(DetailActivity.EXTRA_KEY, (Integer)view.getTag());
        startActivity(intent);
    }

    @Override
    public void totalClicks(int amount) {
        Log.d(TAG, "totalClicks: " + amount);
    }
}
