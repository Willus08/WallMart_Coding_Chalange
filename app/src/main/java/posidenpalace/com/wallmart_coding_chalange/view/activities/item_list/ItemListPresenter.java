package posidenpalace.com.wallmart_coding_chalange.view.activities.item_list;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import posidenpalace.com.wallmart_coding_chalange.model.Item;
import posidenpalace.com.wallmart_coding_chalange.model.RetrofitHelper;
import posidenpalace.com.wallmart_coding_chalange.model.WalMartItemsList;
import retrofit2.Call;
import retrofit2.Response;

public class ItemListPresenter implements ItemListContract.Presenter{
    public ItemListContract.View view;

    public List<Item> itemList = new ArrayList<>();
    @Override
    public void addView(ItemListContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getItems(String item, final int start) {
        // clears the list when a new item is searched for
        if(start == 1){
        itemList.clear();
        }
        // makes the rest call for lazy the initial list and lazy loading
        retrofit2.Call<WalMartItemsList> walMartItemsListCall = RetrofitHelper.walMartItemsListCall(item,start);
        walMartItemsListCall.enqueue(new retrofit2.Callback<WalMartItemsList>() {

            @Override
            public void onResponse(@NonNull Call<WalMartItemsList> call, @NonNull Response<WalMartItemsList> response) {
                // adds items to the list to be passed back to the view
                for (int i = 0; i < response.body().getItems().size() ; i++) {
                    itemList.add(response.body().getItems().get(i));
                }
                view.setupList(itemList,start);
            }

            @Override
            public void onFailure(@NonNull Call<WalMartItemsList> call, @NonNull Throwable t) {

            }
        });
    }

}
