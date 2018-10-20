package design.alex.starwars.rest;

import design.alex.starwars.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiPeoples {

    @GET("/api/people/?format=json")
    Call<Result> getAllPeoples(
            @Query("page") Integer page
    );

}
