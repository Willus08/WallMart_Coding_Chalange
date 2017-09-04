package posidenpalace.com.wallmart_coding_chalange.view.activities.item_details;


import android.content.Intent;

import java.util.List;

import posidenpalace.com.wallmart_coding_chalange.model.Item;
import posidenpalace.com.wallmart_coding_chalange.view.activities.BasePresenter;
import posidenpalace.com.wallmart_coding_chalange.view.activities.BaseView;
// sets up methods for the view and its presenter
interface ItemDetailsContract {
    interface View extends BaseView{
        void setupDetailedList(List<Item> itemList, int position);
    }
    interface Presenter extends BasePresenter<View>{
        void extractIntent(Intent intent);
        void loadItems(int Start);
    }
}
