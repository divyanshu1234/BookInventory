package divyanshu.bookinventory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import divyanshu.bookinventory.database.BooksContract;
import divyanshu.bookinventory.database.BooksDbHelper;

public class AddBookActivity extends AppCompatActivity {

    EditText et_book_name;
    RatingBar r_rating;
    int type = BooksContract.BooksEntry.TYPE_FICTION;

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
            saveData();
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void saveData() {
        BooksDbHelper booksDbHelper = new BooksDbHelper(this);
        SQLiteDatabase database = booksDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BooksContract.BooksEntry.COLUMN_BOOK_NAME, et_book_name.getText().toString());
        values.put(BooksContract.BooksEntry.COLUMN_RATING, r_rating.getRating());
        values.put(BooksContract.BooksEntry.COLUMN_TYPE, type);

        database.insert(BooksContract.BooksEntry.TABLE_NAME, null, values);
    }

    public void onRadioClick(View view) {
        switch (view.getId()){
            case R.id.rb_fiction:
                type = BooksContract.BooksEntry.TYPE_FICTION;
                break;
            case R.id.rb_drama:
                type = BooksContract.BooksEntry.TYPE_DRAMA;
                break;
        }
    }
}
