package opendata.profinit.eu.opendataapireader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import opendata.profinit.eu.opendataapireader.api.OpenDataInterface;
import opendata.profinit.eu.opendataapireader.model.Retrieval;
import opendata.profinit.eu.opendataapireader.retrofit.RetrofitConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;

    private OpenDataInterface openDataInterface;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        Button tenderButton = (Button) findViewById(R.id.tendersButton);
        tenderButton.setOnClickListener(this);

        Button buyerButton = (Button) findViewById(R.id.buyersButton);
        buyerButton.setOnClickListener(this);

        Button customerButton = (Button) findViewById(R.id.suppliersButton);
        customerButton.setOnClickListener(this);

        RetrofitConfiguration retrofitConfiguration = new RetrofitConfiguration();
        OkHttpClient newClient = retrofitConfiguration.okHttpClient();
        OpenDataInterface service = retrofitConfiguration.apiService(retrofitConfiguration.retrofit(newClient));
        service.searchDbLastUpdate().enqueue(new Callback<List<Retrieval>>() {
            @Override
            public void onResponse(Call<List<Retrieval>> call, Response<List<Retrieval>> response) {

                TextView footerText = (TextView) findViewById(R.id.footerText);
                footerText.setText(Utils.formatLongToDate( response.body().get(0).getDate()));


            }

            @Override
            public void onFailure(Call<List<Retrieval>> call, Throwable t) {
            t.getMessage();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tendersButton:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.buyersButton:

                break;

            case R.id.suppliersButton:

                break;

            default:
                break;
        }
    }

}
