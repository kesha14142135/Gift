package g.girl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import g.girl.DBHelper;
import g.girl.constant.Constant;
import g.girl.model.ManSize;

/**
 * Created by sergej on 07.03.17.
 */

public class TestUpdateDataBaseSource implements TestUpdateDataSource {

    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private Context mContext;

    public TestUpdateDataBaseSource(Context context) {
        mContext = context;
    }

    private SQLiteDatabase connectionDataBase() {
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
    public void updateBirthday(int day, int month, int year, int id, TestDataSource.TestCallback callback) {
        SQLiteDatabase dataBase = connectionDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.PASSED_THE_TEST, 1);
        contentValues.put(Constant.DAY, day);
        contentValues.put(Constant.MONTH, month);
        contentValues.put(Constant.YEAR, year);
        try {
            dataBase.update(Constant.DB_TABLE_ACCOUNT, contentValues, "id = ?",
                    new String[]{Integer.toString(id)});
            mSQLiteDataBase.close();
            callback.onSuccess();
        } catch (SQLiteException e) {
            mSQLiteDataBase.close();
            callback.onFailure();
        }
    }

    @Override
    public void updateManSize(ManSize manSize, int id, SizeUpdateCallback callback) {
        SQLiteDatabase dataBase = connectionDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.HEIGHT, manSize.getHeight());
        contentValues.put(Constant.STOMACH, manSize.getStomach());
        contentValues.put(Constant.HIPS, manSize.getHips());
        contentValues.put(Constant.SHOE_SIZE, manSize.getShoeSize());
        try {
            dataBase.update(Constant.DB_TABLE_TEST_SIZE, contentValues, "id = ?",
                    new String[]{Integer.toString(id)});
            mSQLiteDataBase.close();
            callback.onSuccess();
        } catch (SQLiteException e) {
            mSQLiteDataBase.close();
            callback.onFailure();
        }
    }

    @Override
    public void getSize(int id, SizeCallback sizeCallback) {
        SQLiteDatabase dataBase = connectionDataBase();
        String where = Constant.ID_USER + " = ?";
        String[] whereArgs = {id + ""};
        Cursor cursor = dataBase.query(Constant.DB_TABLE_TEST_SIZE, null, where, whereArgs, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                ManSize manSize = new ManSize(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.HEIGHT))),
                        cursor.getString(cursor.getColumnIndex(Constant.STOMACH)),
                        cursor.getString(cursor.getColumnIndex(Constant.HIPS)),
                        cursor.getString(cursor.getColumnIndex(Constant.SHOE_SIZE))
                );
                sizeCallback.onSuccess(manSize);
            }
        } catch (Exception e) {
            sizeCallback.onFailure();
        } finally {
            cursor.close();
            mSQLiteDataBase.close();
        }
    }

    @Override
    public void getBirthday(int id, BirthdayCallback birthdayCallback) {
        SQLiteDatabase dataBase = connectionDataBase();
        int year;
        int month;
        int day;
        String where = Constant.ID + " = ?";
        String[] whereArgs = {id + ""};
        Cursor cursor = dataBase.query(Constant.DB_TABLE_ACCOUNT, null, where, whereArgs, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                day = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.DAY)));
                month = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.MONTH)));
                year = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.YEAR)));
                birthdayCallback.onSuccess(year, month, day);
            }
        } catch (Exception e) {
            birthdayCallback.onFailure();
        } finally {
            cursor.close();
            mSQLiteDataBase.close();
        }
    }

}
