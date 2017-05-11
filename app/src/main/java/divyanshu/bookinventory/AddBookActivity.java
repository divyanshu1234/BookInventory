package divyanshu.bookinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddBookActivity extends AppCompatActivity {

    EditText et_book_name;
    RatingBar r_rating;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        et_book_name = (EditText) findViewById(R.id.et_book_name);
        r_rating = (RatingBar) findViewById(R.id.r_rating);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.menu_add_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //End the activity
        if (item.getItemId() == R.id.m_save_data)
            finish();

        return super.onOptionsItemSelected(item);
    }

    public void onRadioClick(View view) {
    }
}
