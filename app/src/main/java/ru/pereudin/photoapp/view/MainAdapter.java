package ru.pereudin.photoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.pereudin.photoapp.R;
import ru.pereudin.photoapp.model.GlideLoader;
import ru.pereudin.photoapp.present.IRecyclerMainPresenter;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private static final String TAG = "MainAdapter";

    private IRecyclerMainPresenter iRecyclerMainPresenter;
    private GlideLoader glideLoader;

    public MainAdapter(Context context, IRecyclerMainPresenter iRecyclerMainPresenter) {
        this.iRecyclerMainPresenter = iRecyclerMainPresenter;
        glideLoader = new GlideLoader(context);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.position = position;
        iRecyclerMainPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerMainPresenter.getItemCount();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private int position = 0;

        @BindView(R.id.image_view_item_main)
        ImageView imageView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            glideLoader.loadImage(url, imageView);
        }

        @Override
        public void setTag(Integer number) {
            this.imageView.setTag(number);
        }

        @Override
        public int getPos() {
            return position;
        }

    }

}
