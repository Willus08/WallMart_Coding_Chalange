package posidenpalace.com.wallmart_coding_chalange;



public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
