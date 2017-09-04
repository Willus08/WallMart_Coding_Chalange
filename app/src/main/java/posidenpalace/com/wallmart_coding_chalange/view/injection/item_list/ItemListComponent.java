package posidenpalace.com.wallmart_coding_chalange.view.injection.item_list;

import dagger.Component;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_list.ItemList;

@Component(modules = ItemListModule.class)
public interface ItemListComponent {
    void inject(ItemList itemList);
}
