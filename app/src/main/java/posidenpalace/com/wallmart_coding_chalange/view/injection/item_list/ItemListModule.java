package posidenpalace.com.wallmart_coding_chalange.view.injection.item_list;

import dagger.Module;
import dagger.Provides;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_list.ItemListPresenter;

@Module
public class ItemListModule {
    @Provides
    public ItemListPresenter itemListPresenterProvider(){
        return new ItemListPresenter();
    }
}
