package ru.pereudin.photoapp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.pereudin.photoapp.R;
import ru.pereudin.photoapp.present.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    private static final String TAG = "DetailActivity";

    public static final String EXTRA_KEY = "extraKey";
    ImageView imageView;

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter
    public DetailPresenter providePresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.image_view_activity_detail);

        presenter.requestDetails();
        presenter.transferPosition(getIntent().getStringExtra(EXTRA_KEY));

        Log.d(TAG, "onCreate: " + getIntent().getStringExtra(EXTRA_KEY));
    }

    @Override
    public void setImage(int img) {
        imageView.setImageResource(img);
    }
}
