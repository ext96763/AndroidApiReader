package opendata.profinit.eu.opendataapireader.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import opendata.profinit.eu.opendataapireader.api.OpenDataInterface;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by shorcicka on 02.09.2017.
 */


public class RetrofitConfiguration {

    public OkHttpClient okHttpClient() {
        final Logger logger = Logger.getLogger("LoggingInterceptor");

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                Long t1 = System.nanoTime();
                                logger.info(String.format("Sending req for %s", original.url(), chain.connection(), original.headers()));

                                Request.Builder requestBuilder = original.newBuilder()
                                        .headers(original.headers())
                                        .method(original.method(), original.body());


                                Request request = requestBuilder.build();

                                okhttp3.Response response = chain.proceed(request);

                                long t2 = System.nanoTime();
                                logger.info(String.format("Received response for %s in %.1fms%n%s",
                                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                                return response;
                            }
                        })
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        return okClient;
    }


    public Retrofit retrofit(OkHttpClient client) {
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .baseUrl("http://10.0.2.2:8090/")
                .client(client)
                .build();
    }

    public OpenDataInterface apiService(Retrofit retrofit) {
        return retrofit.create(OpenDataInterface.class);
    }
}

