package divyanshu.bookinventory.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Divyanshu on 5/11/17.
 */

public class BooksProvider extends ContentProvider {

    private BooksDbHelper booksDbHelper;

    private static final int BOOKS = 100;
    private static final int BOOKS_ID = 101;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(BooksContract.CONTENT_AUTHORITY, BooksContract.PATH_BOOKS, BOOKS);
        uriMatcher.addURI(BooksContract.CONTENT_AUTHORITY, BooksContract.PATH_BOOKS_ID, BOOKS_ID);
    }

    @Override
    public boolean onCreate() {

        booksDbHelper = new BooksDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = booksDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriMatcher.match(uri)){

            case BOOKS:
                cursor = database.query(
                        BooksContract.BooksEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case BOOKS_ID:
                selection = BooksContract.BooksEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(
                        BooksContract.BooksEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)){

            case BOOKS:
                return BooksContract.BooksEntry.CONTENT_LIST_TYPE;

            case BOOKS_ID:
                return BooksContract.BooksEntry.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase database = booksDbHelper.getWritableDatabase();
        long id;

        switch (uriMatcher.match(uri)){

            case BOOKS:
                id = database.insert(BooksContract.BooksEntry.TABLE_NAME, null, values);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database = booksDbHelper.getWritableDatabase();
        int rowsDeleted;

        switch (uriMatcher.match(uri)){

            case BOOKS:
                rowsDeleted = database.delete(BooksContract.BooksEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case BOOKS_ID:
                selection = BooksContract.BooksEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                rowsDeleted = database.delete(BooksContract.BooksEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database = booksDbHelper.getWritableDatabase();
        int rowsUpdated;

        switch (uriMatcher.match(uri)){

            case BOOKS:
                rowsUpdated = database.update(
                        BooksContract.BooksEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;

            case BOOKS_ID:
                selection = BooksContract.BooksEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                rowsUpdated = database.update(
                        BooksContract.BooksEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }
}
