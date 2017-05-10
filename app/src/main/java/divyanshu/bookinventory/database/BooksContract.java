package divyanshu.bookinventory.database;

import android.provider.BaseColumns;

/**
 * Created by Divyanshu on 5/11/17.
 */

public final class BooksContract {


    public static final class BooksEntry implements BaseColumns{

        public static final String TABLE_NAME = "books";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_BOOK_NAME = "book_name";

        public static final String COLUMN_RATING = "rating";

        public static final String COLUMN_TYPE = "type";

        public static final int TYPE_FICTION = 0;
        public static final int TYPE_DRAMA = 1;
    }
}
