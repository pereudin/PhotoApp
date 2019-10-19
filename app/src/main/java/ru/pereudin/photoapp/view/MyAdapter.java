package ru.pereudin.photoapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pereudin.photoapp.R;
import ru.pereudin.photoapp.present.IRecyclerMainPresenter;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private IRecyclerMainPresenter iRecyclerMainPresenter;

    public MyAdapter(IRecyclerMainPresenter iRecyclerMainPresenter) {
        this.iRecyclerMainPresenter = iRecyclerMainPresenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.position = position;
        iRecyclerMainPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerMainPresenter.getItemCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private ImageView image;
        private int position = 0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view_item_main);
        }

        @Override
        public void setImage(int image) {
            this.image.setImageResource(image);
        }

        @Override
        public int getPos() {
            return position;
        }

    }

}
