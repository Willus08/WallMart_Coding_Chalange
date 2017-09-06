package posidenpalace.com.wallmart_coding_chalange.unit_tests.item_list;


import org.junit.Before;
import org.junit.Test;

import posidenpalace.com.wallmart_coding_chalange.view.activities.item_list.ItemListPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class itemListUnitTests {

    private ItemListPresenter mockedPresenter;

    private int start;
    private String searched;

    @Before
    public void setup(){
        mockedPresenter = mock(ItemListPresenter.class);
        searched = "apple";
        start = 1;
        mockedPresenter.getItems(searched,start);
    }
    @Test// makes sure that a list is made
    public void Should_Aquire_List() throws Exception{
        //assertNotNull();
        verify(mockedPresenter).getItems(searched,start);
    }


}
