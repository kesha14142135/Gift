package g.girl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import g.girl.DBHelper;
import g.girl.constant.Constant;
import g.girl.constant.ConstantQuestion;
import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public class QuestionsUpdateDataBaseSource implements QuestionsUpdateDataSource {

    private DBHelper mDbHelper;
    private SQLiteDatabase mSQLiteDataBase;
    private Context mContext;

    public QuestionsUpdateDataBaseSource(Context context) {
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
    public void updateAnswers(List<AnswerToQuestion> answers, CallBackUpdate callBack) {
        SQLiteDatabase dataBase = connectionDataBase();

        ContentValues contentValues = new ContentValues();
       // contentValues.put(ConstantQuestion.SELECTED, answers.get().getSelected());

        try {
            dataBase.update(Constant.DB_TABLE_ACCOUNT, contentValues, "id = ?",
                    new String[]{Integer.toString(answers.get(0).getIdUser())});
            mSQLiteDataBase.close();
            callBack.success();
        } catch (SQLiteException e) {
            mSQLiteDataBase.close();
            callBack.error();
        }
    }

    @Override
    public void getQuestions(int typeUser, CallBackGetQuestions callBack) {
        List<Question> questions = new ArrayList<>();
        switch (typeUser) {
            case 0:
            case 1: {
                questions.add(new Question("Букет цветов", "flower", typeUser, "http://pozdravim1.ucoz.ru/_ph/17/507935495.png"));
                questions.add(new Question("Кольцо", "ring", typeUser, "https://img-fotki.yandex.ru/get/6813/2449448.21/0_10a222_a656d8e_orig"));
                questions.add(new Question("Обувь", "shoes", typeUser, "http://img1.liveinternet.ru/images/attach/c/9/106/947/106947535_0_981a2_be198f79_XL.png"));
                questions.add(new Question("Одежда", "clothes", typeUser, "http://originalstock.com.ua/wp-content/uploads/2016/12/girls.png"));
                questions.add(new Question("Аксесуары", "accessory", typeUser, "https://img-fotki.yandex.ru/get/38067/2839712.76/0_14fc77_a987f36e_orig"));
                questions.add(new Question("Книги", "book", typeUser, "https://files.allatra.tv/allatra-book/images/allatra-light.png"));
                questions.add(new Question("Косметика", "cosmetics", typeUser, "http://www.fainaidea.com/wp-content/uploads/2016/11/4.png"));
                questions.add(new Question("Алкоголь", "alcohol", typeUser, "http://www.profilaktika.info/images/%D0%B0%D0%BB%D0%BA%D0%BE%D0%B3%D0%BE%D0%BB%D1%8C.png"));
                questions.add(new Question("Спорт инвентарь", "sport", typeUser, "http://sportik.com.ua/images/stories/virtuemart/product/1465486473604.png"));
                callBack.success(questions);
                break;
            }
            case 2: {
                questions.add(new Question("Букет цветов", "flower", typeUser, "http://pozdravim1.ucoz.ru/_ph/17/507935495.png"));
                questions.add(new Question("Кольцо", "ring", typeUser, "https://img-fotki.yandex.ru/get/6813/2449448.21/0_10a222_a656d8e_orig"));
                questions.add(new Question("Обувь", "shoes", typeUser, "http://img1.liveinternet.ru/images/attach/c/9/106/947/106947535_0_981a2_be198f79_XL.png"));
                questions.add(new Question("Одежда", "clothes", typeUser, "http://originalstock.com.ua/wp-content/uploads/2016/12/girls.png"));
                questions.add(new Question("Аксесуары", "accessory", typeUser, "https://img-fotki.yandex.ru/get/38067/2839712.76/0_14fc77_a987f36e_orig"));
                questions.add(new Question("Книги", "book", typeUser, "https://files.allatra.tv/allatra-book/images/allatra-light.png"));
                callBack.success(questions);
                break;
            }
            case 3: {
                questions.add(new Question("Обувь", "shoes", typeUser, "http://www.uz.all.biz/img/uz/catalog/36073.png"));
                questions.add(new Question("Одежда", "clothes", typeUser, "http://i.mgalant.ru/u/pic/e3/bebb1424cf11e39955f6a9f3284aaa/-/palto.png"));
                questions.add(new Question("Аксесуары", "accessory", typeUser, "http://faberlic-shoponline.com.ua/image/cache/data/9475-350x350.png"));
                questions.add(new Question("Книги", "book", typeUser, "https://files.allatra.tv/allatra-book/images/allatra-light.png"));
                questions.add(new Question("Алкоголь", "alcohol", typeUser, "http://www.profilaktika.info/images/%D0%B0%D0%BB%D0%BA%D0%BE%D0%B3%D0%BE%D0%BB%D1%8C.png"));
                questions.add(new Question("Спорт инвентарь", "sport", typeUser, "http://images.ua.prom.st/558373299_w200_h200_pic_box.gif"));
                questions.add(new Question("Пренадлежности для охоты", "hunting", typeUser, "https://fs.4geo.ru/get/editors/landingpage/1481180119-853885.png"));
                callBack.success(questions);
                break;
            }
            case 4: {
                questions.add(new Question("Обувь", "shoes", typeUser, "http://www.uz.all.biz/img/uz/catalog/36073.png"));
                questions.add(new Question("Одежда", "clothes", typeUser, "http://i.mgalant.ru/u/pic/e3/bebb1424cf11e39955f6a9f3284aaa/-/palto.png"));
                questions.add(new Question("Аксесуары", "accessory", typeUser, "http://faberlic-shoponline.com.ua/image/cache/data/9475-350x350.png"));
                questions.add(new Question("Книги", "book", typeUser, "https://files.allatra.tv/allatra-book/images/allatra-light.png"));
                questions.add(new Question("Спорт инвентарь", "sport", typeUser, "http://images.ua.prom.st/558373299_w200_h200_pic_box.gif"));
                questions.add(new Question("Пренадлежности для охоты", "hunting", typeUser, "https://fs.4geo.ru/get/editors/landingpage/1481180119-853885.png"));
                callBack.success(questions);
                break;
            }
            default: {
                callBack.error("Еще нет вопросов для данного типа пользователя");
            }
        }
    }

    @Override
    public void getAnswers(int idUser, CallBackGetAnswers callBack) {
//       SQLiteDatabase dataBase = conectionDataBase();
//        List<AnswerToQuestion> answer = new ArrayList<>();
//        String where = ConstantQuestion.ID_USER + " = ?";
//        String[] whereArgs = {idUser + ""};
//       Cursor cursor = dataBase.query(ConstantQuestion.DB_TABLE_ANSWER_TO_QUESTION, null, where, whereArgs, null, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    answer.add(
//                            new AnswerToQuestion(
//                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.NAME)),
//                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.HASHTAG)),
//                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER))),
//                                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.URL)),
//                                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(ConstantQuestion.TYPE))),
//                                    Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ConstantQuestion.SELECTED)))
//                            ));
//                    cursor.getString(cursor.getColumnIndex(ConstantQuestion.ID_USER));
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//            mSQLiteDataBase.close();
//        }
//        callBack.onSuccess(answer);
   }
}
