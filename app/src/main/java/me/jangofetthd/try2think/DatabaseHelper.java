package me.jangofetthd.try2think;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Mikhail Mustakimov on 11/25/2015.
 *
 * Main goal of this class -- working with SQLite DataBase.
 * Implements loading and saving to DB
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_TABLE = "game";

    private static final String LOG_TAG = "TRY2THINK_DB";

    public static final String LEVEL_COLUMN = "level";
    public static final String CHAPTER_COLUMN = "chapter";
    public static final String TRY_COUNT_COLUMN = "try_count";
    public static final String STATUS_COLUMN = "status";
    public static final String HELP_COUNT_COLUMN = "help_count";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
                        + DATABASE_TABLE + " (" + BaseColumns._ID
                        + " integer primary key autoincrement, " + CHAPTER_COLUMN
                        + " integer, " + LEVEL_COLUMN + " integer, "
                        + STATUS_COLUMN + " integer, "
                        + HELP_COUNT_COLUMN + " integer, "
                        + TRY_COUNT_COLUMN + " integer);";

    private static Context mContext;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
        Log.i(LOG_TAG, "Table has been created. DatabaseCreateScript='"+DATABASE_CREATE_SCRIPT+"';");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:
                switch(newVersion){
                    case 2:
                        db.execSQL("ALTER TABLE "+DATABASE_TABLE+" ADD COLUMN "+HELP_COUNT_COLUMN+" INTEGER DEFAULT 0");
                        break;
                }
                break;
        }
    }

    public static void saveStateToDatabase() {

        SQLiteDatabase sdb;

        DatabaseHelper mDatabaseHelper;

        lvl section[] = new lvl[0];

        mDatabaseHelper = new DatabaseHelper(MainActivity.getContext());
        for (int i=1; i<=7; i++) {
            switch (i) {
                case 1:
                    section = DataBase.section1;
                    break;
                case 2:
                    section = DataBase.section2;
                    break;
                case 3:
                    section = DataBase.section3;
                    break;
                case 4:
                    section = DataBase.section4;
                    break;
                case 5:
                    section = DataBase.section5;
                    break;
                case 6:
                    section = DataBase.section6;
                    break;
                case 7:
                    section = DataBase.section7;
                    break;
            }
            for (int j=0; j<section.length; j++) {
                ContentValues newValues = new ContentValues();
                newValues.put(DatabaseHelper.CHAPTER_COLUMN, i);
                newValues.put(DatabaseHelper.LEVEL_COLUMN, j);
                newValues.put(DatabaseHelper.STATUS_COLUMN, section[j].status);
                newValues.put(DatabaseHelper.TRY_COUNT_COLUMN, section[j].tryCount);
                newValues.put(DatabaseHelper.HELP_COUNT_COLUMN, section[j].helpCount);

                sdb = mDatabaseHelper.getWritableDatabase();
                sdb.insert(DatabaseHelper.DATABASE_TABLE, null, newValues);
            }
        }


        String dbpath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        Log.i(LOG_TAG, "databasePath='"+dbpath+"'");
    }

    public static void loadStateFromDatabase() {

        SQLiteDatabase sdb;

        DatabaseHelper mDatabaseHelper;

        mDatabaseHelper = new DatabaseHelper(MainActivity.getContext());

        sdb = mDatabaseHelper.getReadableDatabase();

        Cursor cursor = sdb.query(DATABASE_TABLE, new String[] {LEVEL_COLUMN, CHAPTER_COLUMN,
                TRY_COUNT_COLUMN,STATUS_COLUMN, HELP_COUNT_COLUMN}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int section = cursor.getInt(cursor.getColumnIndex(CHAPTER_COLUMN));
            int levelNum = cursor.getInt(cursor.getColumnIndex(LEVEL_COLUMN));

            /*
            * For new level adding you should add case statement
            * case N: level = DataBase.sectionN; break;
            * */

            lvl level[] = DataBase.section1;
            switch (section) {
                case 2:
                    level = DataBase.section2;
                    break;
                case 3:
                    level = DataBase.section3;
                    break;
                case 4:
                    level = DataBase.section4;
                    break;
                case 5:
                    level = DataBase.section5;
                    break;
                case 6:
                    level = DataBase.section6;
                    break;
                case 7:
                    level = DataBase.section7;
                    break;
            }

            level[levelNum].status = cursor.getInt(cursor.getColumnIndex(STATUS_COLUMN));
            level[levelNum].tryCount = cursor.getInt(cursor.getColumnIndex(TRY_COUNT_COLUMN));
            level[levelNum].helpCount = cursor.getInt(cursor.getColumnIndex(HELP_COUNT_COLUMN));
        }
        cursor.close();
        Log.i(LOG_TAG, "State from DB loaded.");
    }

}
