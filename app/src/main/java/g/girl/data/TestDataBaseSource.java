package g.girl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import g.girl.constant.Constant;
import g.girl.DBHelper;
import g.girl.model.ManSize;

/**
 * Created by sergej on 07.03.17.
 */

public class TestDataBaseSource implements TestDataSource {

    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private Context mContext;

    public TestDataBaseSource(Context context) {
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
    public void addBirthday(int day, int month, int year, int id, TestCallback callback) {

        SQLiteDatabase dataBase = conectionDataBase();
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
    public void addManSize(ManSize manSize, int id) {
        SQLiteDatabase dataBase = conectionDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.ID_USER, id);
        contentValues.put(Constant.HEIGHT, manSize.getHeight());
        contentValues.put(Constant.STOMACH, manSize.getStomach());
        contentValues.put(Constant.HIPS, manSize.getHips());
        contentValues.put(Constant.SHOE_SIZE, manSize.getShoeSize());
        dataBase.insert(Constant.DB_TABLE_TEST_SIZE, null, contentValues);
        mSQLiteDataBase.close();
    }

    @Override
    public void getManSize(int id, ManSizeCallback callBack) {
        SQLiteDatabase dataBase = conectionDataBase();
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
                callBack.onSuccess(manSize);
            } else {
                callBack.onFailure();
            }
        } catch (Exception e) {
        } finally {
            cursor.close();
            mSQLiteDataBase.close();
        }
    }

}
