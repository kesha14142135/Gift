package g.girl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import g.girl.constant.Constant;
import g.girl.DBHelper;
import g.girl.model.Person;

/**
 * Created by sergej on 07.02.17.
 */

public class PersonsDataBaseSource implements PersonsDataSource {

    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private Context mContext;

    public PersonsDataBaseSource(Context context) {
        mContext = context;
    }

    private SQLiteDatabase conectionDataBase() {
        if (checkDataBase()) {
            mSQLiteDataBase = SQLiteDatabase.openDatabase(Constant.DB_FULL_PATH, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } else {
            mDbHelper = new DBHelper(mContext, Constant.DB_NAME);
            mSQLiteDataBase = mDbHelper.getWritableDatabase();
        }
        return mSQLiteDataBase;
    }

    private boolean checkDataBase() {
        try {
            mSQLiteDataBase = SQLiteDatabase.openDatabase(Constant.DB_FULL_PATH, null,
                    SQLiteDatabase.OPEN_READWRITE);
            mSQLiteDataBase.close();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public void readAll(PersonsCallback callback) {
        SQLiteDatabase dataBase = conectionDataBase();
        List<Person> persons = new ArrayList<>();
        Cursor cursor = dataBase.query(Constant.DB_TABLE_ACCOUNT, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    persons.add(
                            new Person(
                                    cursor.getString(cursor.getColumnIndex(Constant.NAME)),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.TYPE))),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.PASSED_THE_TEST))),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.DAY))),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.MONTH))),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.YEAR))),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.ID)))
                            ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (persons != null) {
            callback.onSuccess(persons);
        } else {
            callback.onFailure();
        }
        mSQLiteDataBase.close();
    }

    @Override
    public void addPerson(Person Person) {
        SQLiteDatabase dataBase = conectionDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.NAME, Person.getName());
        contentValues.put(Constant.TYPE, Person.getType());
        contentValues.put(Constant.PASSED_THE_TEST, 0);
        contentValues.put(Constant.DAY, Person.getDay());
        contentValues.put(Constant.MONTH, Person.getMonth());
        contentValues.put(Constant.YEAR, Person.getYear());
        dataBase.insert(Constant.DB_TABLE_ACCOUNT, null, contentValues);
        mSQLiteDataBase.close();
    }
}
