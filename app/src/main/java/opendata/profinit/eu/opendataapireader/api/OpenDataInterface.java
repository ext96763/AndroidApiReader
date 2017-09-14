package opendata.profinit.eu.opendataapireader.api;

import java.util.List;

import opendata.profinit.eu.opendataapireader.model.Record;
import opendata.profinit.eu.opendataapireader.model.Retrieval;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shorcicka on 02.09.2017.
 */

public interface OpenDataInterface {

    @GET("/search")
    Call<List<Record>> searchByName(@Query("name") String name, @Query("size") Long size, @Query("page") Long pages);

    @GET("/tenders/search")
    Call<List<Record>> searchTenders(@Query("name") String name, @Query("size") Long size, @Query("page") Long pages);

    @GET("/suppliers/search")
    Call<List<Record>> searchSuppliers(@Query("name") String name, @Query("size") Long size, @Query("page") Long pages);

    @GET("/buyers/search")
    Call<List<Record>> searchBuyers(@Query("name") String name, @Query("size") Long size, @Query("page") Long pages);

    @GET("/lastUpdate")
    Call<List<Retrieval>> searchDbLastUpdate();

}
