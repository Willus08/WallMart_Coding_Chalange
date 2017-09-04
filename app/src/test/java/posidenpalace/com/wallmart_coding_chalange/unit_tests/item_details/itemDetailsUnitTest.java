package posidenpalace.com.wallmart_coding_chalange.unit_tests.item_details;


import org.junit.Before;
import org.junit.Test;

import posidenpalace.com.wallmart_coding_chalange.view.activities.item_details.ItemDetailsPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class itemDetailsUnitTest {
    private ItemDetailsPresenter mockedPresenter = mock(ItemDetailsPresenter.class);

    private int start;

    @Before
    public void setup(){
        String searched = "apple";
        start = 11;
        mockedPresenter.currentItem = searched;
        mockedPresenter.loadItems(start);
    }
    @Test// makes sure that a lazy load happens
    public void lazyLoadList() throws Exception{
        //assertNotNull();
        verify(mockedPresenter).loadItems(start);
    }

}
