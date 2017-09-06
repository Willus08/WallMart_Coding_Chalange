package posidenpalace.com.wallmart_coding_chalange.view.activities.item_details;


import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import posidenpalace.com.wallmart_coding_chalange.model.Item;
import posidenpalace.com.wallmart_coding_chalange.data.remote.RetrofitHelper;
import posidenpalace.com.wallmart_coding_chalange.model.WalMartItemsList;
import retrofit2.Call;
import retrofit2.Response;

public class ItemDetailsPresenter implements ItemDetailsContract.Presenter{
    private ItemDetailsContract.View view;
    private List<Item> itemList = new ArrayList<>();
    public String currentItem;

    @Override
    public void addView(ItemDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void extractIntent(Intent intent) {
        // grabs the intent from the past activity and pass it to the view
        itemList.clear();
        itemList = intent.getParcelableArrayListExtra("list");
        currentItem = intent.getStringExtra("item");
        int position = intent.getIntExtra("position",0);
        view.setupDetailedList(itemList,position);
    }

    @Override
    public void loadItems(final int start) {
        // calls the retrofit to get the next part of the list and passes it back to the view
        retrofit2.Call<WalMartItemsList> walMartItemsListCall = RetrofitHelper.walMartItemsListCall(currentItem,start);
        walMartItemsListCall.enqueue(new retrofit2.Callback<WalMartItemsList>() {

            @Override
            public void onResponse(@NonNull Call<WalMartItemsList> call, @NonNull Response<WalMartItemsList> response) {
               //fills out the list then passes it back
                for (int i = 0; i < response.body().getItems().size() ; i++) {
                    itemList.add(response.body().getItems().get(i));
                }
                view.setupDetailedList(itemList, start-1);
            }

            @Override
            public void onFailure(@NonNull Call<WalMartItemsList> call, @NonNull Throwable t) {

            }
        });
    }
}
