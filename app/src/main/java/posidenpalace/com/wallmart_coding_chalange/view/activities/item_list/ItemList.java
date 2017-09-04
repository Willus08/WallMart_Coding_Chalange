package posidenpalace.com.wallmart_coding_chalange.view.activities.item_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.wallmart_coding_chalange.R;
import posidenpalace.com.wallmart_coding_chalange.model.Item;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_details.ItemDetails;
import posidenpalace.com.wallmart_coding_chalange.view.injection.item_list.DaggerItemListComponent;

public class ItemList extends AppCompatActivity implements ItemListContract.View {
    @Inject ItemListPresenter presenter;

    @BindView(R.id.etSearchBox)
    EditText searchBar;

    @BindView(R.id.rvListOfItems)
    RecyclerView listOfItems;

    ItemListAdapter adapter;
    DefaultItemAnimator animator;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.OnScrollListener scrollListener;

    ItemReceiver receiver = new ItemReceiver();
    IntentFilter filter = new IntentFilter();
    public String currentItem = "";
    public int currentSize = 0;
    boolean loading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setupDagger();
        ButterKnife.bind(this);
        presenter.addView(this);
        // loads the currently searched item when the screen rotates
        if(savedInstanceState != null){

            currentItem =savedInstanceState.getString("currentItem");

        }else {
            // gives a default value for when the app first starts
            currentItem = "apple";
        }
        presenter.getItems(currentItem,1);



    }
    @Override
    public void setupDagger() {
        DaggerItemListComponent.create().inject(this);
    }


    @Override
    public void showError(Error error) {

    }

    @Override
    public void setupList(final List<Item> itemList, int position) {

        loading=false;
        currentSize = itemList.size();
        // sets the list to the recycler view
        adapter = new ItemListAdapter(itemList);
        layoutManager = new LinearLayoutManager(this);
        // saves the user's spot when lazy loading happens
        layoutManager.scrollToPosition(position);
        animator = new DefaultItemAnimator();
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // begins lazy loading when the user can not scroll down anymore
                if(!recyclerView.canScrollVertically(1) && !loading){
                    // prevents the list from having more than 100 children to save on memory
                   if(currentSize+1 < 100){
                        presenter.getItems(currentItem,currentSize+1);

                   }else {
                       // lets the user know when they can not load anymore
                       Toast.makeText(ItemList.this, R.string.lazy_load_limit_message, Toast.LENGTH_SHORT).show();
                   }
                    loading=true;
                }
            }
        };
        listOfItems.addOnScrollListener(scrollListener);
        listOfItems.setAdapter(adapter);
        listOfItems.setLayoutManager(layoutManager);
        listOfItems.setItemAnimator(animator);

    }

    @Override
    public void goToDetailedItems(int position, List<Item> itemList) {
        // passes the list and the selected position to the next activity
        Intent intent = new Intent(this, ItemDetails.class);
        intent.putExtra("position",position);
        intent.putExtra("item",currentItem);
        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) itemList);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // sets up the receiver to get information on what item was clicked from the adapter
        filter.addAction("items");
        registerReceiver(receiver,filter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        // unregisters the reciver to save on memory
        unregisterReceiver(receiver);
    }

    public void Search(View view) {
        // checks to make sure the search bar has anything in it before attempting to do a new search
        if(!searchBar.getText().toString().equals("")){
        currentItem = searchBar.getText().toString();
        presenter.getItems(currentItem,1);
        }else {
            //warns the user that they have nothing in the search bar
            Toast.makeText(this, R.string.search_warning_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // saves the current item when the screen is rotated
        savedInstanceState.putString("currentItem",currentItem);

    }


    class ItemReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // gets information from the adaptor and passes it on to be sent to the next activity
              List<Item> items = intent.getParcelableArrayListExtra("list");
              int position = intent.getIntExtra("position" , 0);
              goToDetailedItems(position,items);
        }
    }
}
