package posidenpalace.com.wallmart_coding_chalange.view.activities;



public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
