package g.girl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import g.girl.constant.Constant;
import g.girl.constant.ConstantQuestion;

/**
 * Created by sergej on 03.02.17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "
                + Constant.DB_TABLE_ACCOUNT
                + " ("
                + "id integer primary key autoincrement,"
                + Constant.NAME + " text,"
                + Constant.TYPE + " text,"
                + Constant.PASSED_THE_TEST + " integer,"
                + Constant.DAY + " integer,"
                + Constant.MONTH + " integer,"
                + Constant.YEAR + " integer"
                + ");");

        db.execSQL("create table "
                + Constant.DB_TABLE_TEST_SIZE
                + " ("
                + "id integer primary key autoincrement,"
                + Constant.ID_USER + " integer default 0,"
                + Constant.HEIGHT + " integer default 0,"
                + Constant.STOMACH + " text,"
                + Constant.HIPS + " text,"
                + Constant.SHOE_SIZE + " text"
                + ");");

        db.execSQL("create table "
                + ConstantQuestion.DB_TABLE_ANSWER_TO_QUESTION
                + " ("
                + "id integer primary key autoincrement,"
                + ConstantQuestion.NAME + " text,"
                + ConstantQuestion.HASHTAG + " text,"
                + ConstantQuestion.ID_USER + " integer default 0,"
                + ConstantQuestion.URL + " text,"
                + ConstantQuestion.TYPE + " integer default 0,"
                + ConstantQuestion.SELECTED + " integer default 0"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
