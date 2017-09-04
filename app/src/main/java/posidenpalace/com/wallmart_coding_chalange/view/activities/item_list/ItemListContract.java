package posidenpalace.com.wallmart_coding_chalange.view.activities.item_list;


import java.util.List;

import posidenpalace.com.wallmart_coding_chalange.model.Item;
import posidenpalace.com.wallmart_coding_chalange.view.activities.BasePresenter;
import posidenpalace.com.wallmart_coding_chalange.view.activities.BaseView;
// sets up methods for the view and its presenter
interface ItemListContract {
    interface View extends BaseView{
        void setupList(List<Item> itemList, int posiiton);
        void goToDetailedItems(int position, List<Item> itemList);
    }
    interface Presenter extends BasePresenter<View>{

        void getItems(String item,int start);
    }
}
