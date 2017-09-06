package posidenpalace.com.wallmart_coding_chalange.data.remote;

import posidenpalace.com.wallmart_coding_chalange.model.WalMartItemsList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Sets up the retrofit calls
public class RetrofitHelper  {
    private static final String BASE_URL = "http://api.walmartlabs.com/";
    private static final String API_KEY = "zma8hpvguj7t7f36bbzx82zn";
    private static Retrofit create(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Makes the call to the api to aquire the list of items
    public static Call<WalMartItemsList> walMartItemsListCall(String item , int start){
        Retrofit retrofit = create();
        CallServices callServices = retrofit.create(CallServices.class);
        return callServices.getItems(item,"json", start, API_KEY);
    }

  public interface CallServices{
        //sends out the query to the api
        @GET("v1/search?")
        Call<WalMartItemsList> getItems(@Query("query") String query, @Query("format") String format,@Query("start") int start, @Query("apiKey") String key);

    }
}