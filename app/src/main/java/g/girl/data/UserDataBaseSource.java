package g.girl.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import g.girl.constant.Constant;
import g.girl.DBHelper;
import g.girl.constant.ConstantQuestion;
import g.girl.model.AnswerToQuestion;
import g.girl.model.ManSize;
import g.girl.model.Person;

/**
 * Created by sergej on 07.03.17.
 */

public class UserDataBaseSource implements UserDataSource {

    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private Context mContext;

    public UserDataBaseSource(Context context) {
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
    public void getPerson(int id, HomeCallback callback) {
        SQLiteDatabase dataBase = conectionDataBase();
        String where = Constant.ID + " = ?";
        String[] whereArgs = {id + ""};
        Cursor cursor = dataBase.query(Constant.DB_TABLE_ACCOUNT, null, where, whereArgs, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                Person person = new Person(
                        cursor.getString(cursor.getColumnIndex(Constant.NAME)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.TYPE))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.PASSED_THE_TEST))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.DAY))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.MONTH))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.YEAR))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constant.ID)))
                );
                callback.onSuccess(person);
            }
        } catch (Exception e) {
            callback.onFailure();
        } finally {
            cursor.close();
            mSQLiteDataBase.close();
        }
    }

    @Override
    public void getSize(int id, SizeCallback sizeCallback) {
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
    public void getLike(int id, LikeCallback answerCallBack) {
        SQLiteDatabase dataBase = conectionDataBase();
        List<AnswerToQuestion> answer = new ArrayList<>();
        String where = ConstantQuestion.ID_USER + " = ?" + " AND " + ConstantQuestion.SELECTED + " = ?";
        String[] whereArgs = {id + "",1+""};
        Cursor cursor = dataBase.query(ConstantQuestion.DB_TABLE_ANSWER_TO_QUESTION, null, where, whereArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    answer.add(
                            new AnswerToQuestion(
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.NAME)),
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.HASHTAG)),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER))),
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.URL)),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.TYPE))),
                                    Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ConstantQuestion.SELECTED)))
                            ));
                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER));
                } while (cursor.moveToNext());
            }
            cursor.close();
            mSQLiteDataBase.close();
        }
        answerCallBack.onSuccess(answer);
    }

    @Override
    public void getDislike(int id, LikeCallback answerCallBack) {
        SQLiteDatabase dataBase = conectionDataBase();
        List<AnswerToQuestion> answer = new ArrayList<>();
        String where = ConstantQuestion.ID_USER + " = ?" + " AND " + ConstantQuestion.SELECTED + " = ?";
        String[] whereArgs = {id + "",0+""};
        Cursor cursor = dataBase.query(ConstantQuestion.DB_TABLE_ANSWER_TO_QUESTION, null, where, whereArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    answer.add(
                            new AnswerToQuestion(
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.NAME)),
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.HASHTAG)),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER))),
                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.URL)),
                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.TYPE))),
                                    Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ConstantQuestion.SELECTED)))
                            ));
                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER));
                } while (cursor.moveToNext());
            }
            cursor.close();
            mSQLiteDataBase.close();
        }
        answerCallBack.onSuccess(answer);
    }
}
