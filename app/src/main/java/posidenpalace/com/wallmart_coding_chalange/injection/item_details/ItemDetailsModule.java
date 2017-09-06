package posidenpalace.com.wallmart_coding_chalange.injection.item_details;


import dagger.Module;
import dagger.Provides;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_details.ItemDetailsPresenter;

@Module
public class ItemDetailsModule {
    @Provides
    public ItemDetailsPresenter itemDetailsPresenterProvider(){
        return new ItemDetailsPresenter();
    }
}
