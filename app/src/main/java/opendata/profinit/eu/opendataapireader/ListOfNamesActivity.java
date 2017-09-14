package opendata.profinit.eu.opendataapireader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import opendata.profinit.eu.opendataapireader.api.OpenDataInterface;
import opendata.profinit.eu.opendataapireader.model.Record;
import opendata.profinit.eu.opendataapireader.retrofit.RetrofitConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shorcicka on 05.09.2017.
 */

public class ListOfNamesActivity extends AppCompatActivity {

    RetrofitConfiguration retrofitConfiguration;

    ProgressBar progressBar;

    Intent intent = new Intent();

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_of_short_names_activity);


        String searchText = getIntent().getStringExtra("search_text");

        mRecyclerView = (RecyclerView) findViewById(R.id.list_of_names);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar) findViewById(R.id.progressSearchBar);
        progressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        // specify an adapter (see also next example)
        final MyAdapter mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        retrofitConfiguration = new RetrofitConfiguration();
        OkHttpClient newClient = retrofitConfiguration.okHttpClient();
        OpenDataInterface service = retrofitConfiguration.apiService(retrofitConfiguration.retrofit(newClient));

        service.searchByName(searchText, 2L, 1L).enqueue(new Callback<List<Record>>() {
            @Override
            public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {

                if (response.isSuccessful()) {
                    mAdapter.setDataset(response.body());
                }
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<List<Record>> call, Throwable t) {
                t.getMessage();
                progressBar.setVisibility(View.GONE);
                showMessage(t.getMessage());
                showMessage("Returning to main page, try searching later");
                startActivity(moveTo(MainActivity.class));


            }
        });
    }

    private void showMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Intent moveTo(Class clas){
        Intent intent = new Intent(this, clas);
        return intent;
    }
}
