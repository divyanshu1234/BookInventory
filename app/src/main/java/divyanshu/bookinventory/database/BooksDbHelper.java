package divyanshu.bookinventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Divyanshu on 5/11/17.
 */

public class BooksDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "inventory.db";

    public BooksDbHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BOOKS_TABLE = "CREATE TABLE " + BooksContract.BooksEntry.TABLE_NAME + " ("
                + BooksContract.BooksEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BooksContract.BooksEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL DEFAULT 'No name', "
                + BooksContract.BooksEntry.COLUMN_RATING + " REAL, "
                + BooksContract.BooksEntry.COLUMN_TYPE + " INTEGER);";

        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
