package divyanshu.bookinventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import divyanshu.bookinventory.database.BooksContract;
import divyanshu.bookinventory.database.BooksDbHelper;

public class MainActivity extends AppCompatActivity {
    
    TextView tv_total_items, tv_display_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_total_items = (TextView) findViewById(R.id.tv_total_items);
        tv_display_data = (TextView) findViewById(R.id.tv_display_data);

        displayData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData();
    }

    private void displayData() {
        String[] projection = new String[]{
                BooksContract.BooksEntry._ID,
                BooksContract.BooksEntry.COLUMN_BOOK_NAME,
                BooksContract.BooksEntry.COLUMN_RATING,
                BooksContract.BooksEntry.COLUMN_TYPE
        };

        Cursor cursor = getContentResolver().query(
                BooksContract.BooksEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        int indexID = cursor.getColumnIndex(BooksContract.BooksEntry._ID);
        int indexBookName = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_NAME);
        int indexRating = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_RATING);
        int indexType = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_TYPE);

        tv_total_items.setText(String.valueOf(cursor.getCount()));
        tv_display_data.setText("");

        while (cursor.moveToNext()){
            String display = cursor.getInt(indexID) + " - "
                    + cursor.getString(indexBookName) + " - "
                    + cursor.getFloat(indexRating) + " - "
                    + cursor.getInt(indexType) + "\n";

            tv_display_data.append(display);
        }

        cursor.close();
    }


    private void addSampleData() {
        ContentValues values = new ContentValues();

        values.put(BooksContract.BooksEntry.COLUMN_BOOK_NAME, "Sample Name");
        values.put(BooksContract.BooksEntry.COLUMN_RATING, 2.5f);
        values.put(BooksContract.BooksEntry.COLUMN_TYPE, BooksContract.BooksEntry.TYPE_FICTION);

        Uri insertUri = getContentResolver().insert(BooksContract.BooksEntry.CONTENT_URI, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.m_add_sample_data:
                addSampleData();
                displayData();
                return true;
            case R.id.m_add_book:
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
