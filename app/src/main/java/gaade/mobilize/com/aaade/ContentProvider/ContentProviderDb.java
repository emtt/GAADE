package gaade.mobilize.com.aaade.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import gaade.mobilize.com.aaade.Database.MySQLiteHelper;

/**
 * Created by user01 on 09/05/17.
 *
 * context.getContentResolver().insert(.....);
 context.getContentResolver().query(.....);
 context.getContentResolver().delete(.....);
 context.getContentResolver().update(.....);

 from activity

 getContentResolver().insert(.....);
 getContentResolver().query(.....);
 getContentResolver().delete(.....);
 getContentResolver().update(.....);

 example:
 ContentValues initialValues = new ContentValues();
 initialValues.put("Column", "value");
 Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "TableName");
 Uri resultUri = context.getContentResolver().insert(contentUri, initialValues);

 Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "TableName");
 */

public class ContentProviderDb  extends ContentProvider {

    private static final int ALL_BOOKS = 1;
    private static final int SINGLE_BOOK = 2;

    MySQLiteHelper db;
    // authority is the symbolic name of your provider
    // To avoid conflicts with other providers, you should use
    // Internet domain ownership (in reverse) as the basis of your provider authority.
    private static final String AUTHORITY = "gaade.mobilize.com.aaade.ContentProvider.ContentProviderDb";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/books");


    @Override
    public boolean onCreate() {

        db = MySQLiteHelper.getInstance(getContext());
        return false;

    }
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "books", ALL_BOOKS);
        uriMatcher.addURI(AUTHORITY, "books/#", SINGLE_BOOK);
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {

        switch (uriMatcher.match(uri)) {
            case ALL_BOOKS:
                //do nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        SQLiteDatabase database = db.getWritableDatabase();

        long id =  database.insert("books",
                null,
                initialValues);
        db.close();
        Log.d("MYSQLITEHELPER", "REGISTRO INSERTADO EN DB");
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String table =getTableName(uri);
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor =database.query(table,  projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    public static String getTableName(Uri uri){
        String value = uri.getPath();
        value = value.replace("/", "");//we need to remove '/'
        return value;
    }

    @Override
    public int delete(Uri uri, String where, String[] args) {

        String table = getTableName(uri);
        SQLiteDatabase dataBase=db.getWritableDatabase();
        return dataBase.delete(table, where, args);

    }

    @Override
    public int update(Uri uri, ContentValues values, String whereClause,
                      String[] whereArgs) {
        String table = getTableName(uri);
        SQLiteDatabase database = db.getWritableDatabase();
        return database.update(table, values, whereClause, whereArgs);
    }

    @Override
    public String getType(Uri arg0) {
        switch (uriMatcher.match(arg0)) {
            case ALL_BOOKS:
                return "vnd.android.cursor.dir/vnd.gaade.mobilize.com.aaade.ContentProvider.ContentProviderDb.books";
            case SINGLE_BOOK:
                return "vnd.android.cursor.item/vnd.gaade.mobilize.com.aaade.ContentProvider.ContentProviderDb.books";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + arg0);
        }
    }

    private void notifyChanges(Uri uri){
        if (getContext() != null && getContext().getContentResolver() != null){
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

}
