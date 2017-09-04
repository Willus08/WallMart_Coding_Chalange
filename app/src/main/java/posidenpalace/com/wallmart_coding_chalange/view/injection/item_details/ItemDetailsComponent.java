package posidenpalace.com.wallmart_coding_chalange.view.injection.item_details;

import dagger.Component;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_details.ItemDetails;

@Component(modules = ItemDetailsModule.class)
public interface ItemDetailsComponent {
    void inject(ItemDetails itemDetails);
}
