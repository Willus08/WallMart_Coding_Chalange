package posidenpalace.com.wallmart_coding_chalange.view.activities.item_details;


import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import posidenpalace.com.wallmart_coding_chalange.R;
import posidenpalace.com.wallmart_coding_chalange.model.Item;


class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder>{
    private List<Item> itemsList = new ArrayList<>();

    ItemDetailsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // sets up the layout for when the device is in landscape mode
        if(parent.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_items_list_alt,parent,false);
            return new ViewHolder(view);

        }else{
            // sets up the layout for when the device is in portrait mode
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_items_list,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //sets up all the views in the layout
        Item detailedItem = itemsList.get(position);
        String itemName = "Item: " + detailedItem.getName();
        String itemPrice = "Price: $"+ detailedItem.getSalePrice();
        String itemStock = "Stock: " + detailedItem.getStock();
        String itmeAvailable ="Available Online: "+ detailedItem.getAvailableOnline();
        holder.name.setText(itemName);
        holder.price.setText(itemPrice);
        holder.stock.setText(itemStock);
        holder.online.setText(itmeAvailable);
        if (detailedItem.getCustomerRating() != null){
            holder.itemRating.setRating(Float.parseFloat(detailedItem.getCustomerRating()));
        }else {
            holder.itemRating.setVisibility(View.GONE);
        }
        // prevents the user from acidently changing the raiting on their device
        holder.itemRating.setEnabled(false);

        Glide.with(holder.itemView.getContext()).load(detailedItem.getLargeImage()).into(holder.itemImage);


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //instantiates all the views for the layouts
        TextView name;
        TextView price;
        TextView stock;
        TextView online;
        ImageView itemImage;
        RatingBar itemRating;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvDetailedName);
            price = (TextView) itemView.findViewById(R.id.tvDetailedPrice);
            stock = (TextView) itemView.findViewById(R.id.tvStock);
            online = (TextView) itemView.findViewById(R.id.tvAvailableOnline);
            itemImage = (ImageView) itemView.findViewById(R.id.ivDetailedItemImage);
            itemRating = (RatingBar) itemView.findViewById(R.id.rbItemRating);
        }
    }
}
