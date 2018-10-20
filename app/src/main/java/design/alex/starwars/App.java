package design.alex.starwars;

import android.app.Application;
import android.util.Log;

import design.alex.starwars.rest.RestApiPeoples;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    // Сервис для получения персонажей
    private RestApiPeoples mPeopleRestService;

    @Override
    public void onCreate() {
        super.onCreate();
        buildRest();
        Log.d("TAG", "onCreateApplication");

    }

    private void buildRest() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://swapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mPeopleRestService = retrofit.create(RestApiPeoples.class);
    }

    public RestApiPeoples getPeopleRestService() {
        return mPeopleRestService;
    }
}
