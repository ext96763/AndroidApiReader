package opendata.profinit.eu.opendataapireader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    EditText searchBoxCriteria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_criteria);

        Button searchButtonCriteria = (Button) findViewById(R.id.searchButtonCriteria);
        searchBoxCriteria = (EditText) findViewById(R.id.searchBocCriteria);
        searchButtonCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity();
            }
        });
    }

    private void startActivity(){
        Intent intent = new Intent(this, ListOfNamesActivity.class);
        intent.putExtra("search_text", searchBoxCriteria.getText());
        startActivity(intent);
    }
}
