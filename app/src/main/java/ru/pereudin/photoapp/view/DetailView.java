package ru.pereudin.photoapp.view;

import moxy.MvpView;

public interface DetailView extends MvpView {
    void setImage(int img);
    void setImageURL(String url);
}
