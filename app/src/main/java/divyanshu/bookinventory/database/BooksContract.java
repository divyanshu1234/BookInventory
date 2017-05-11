package divyanshu.bookinventory.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Divyanshu on 5/11/17.
 */

public final class BooksContract {

    public static final String CONTENT_AUTHORITY = "divyanshu.bookinventory";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOOKS = "books";
    public static final String PATH_BOOKS_ID = PATH_BOOKS + "/#";


    public static final class BooksEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;


        public static final String TABLE_NAME = "books";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_BOOK_NAME = "book_name";

        public static final String COLUMN_RATING = "rating";

        public static final String COLUMN_TYPE = "type";

        public static final int TYPE_FICTION = 0;
        public static final int TYPE_DRAMA = 1;
    }
}
