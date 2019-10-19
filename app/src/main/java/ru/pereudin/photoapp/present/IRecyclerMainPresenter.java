package ru.pereudin.photoapp.present;

import ru.pereudin.photoapp.view.IViewHolder;

public interface IRecyclerMainPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();

}
