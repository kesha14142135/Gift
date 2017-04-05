package g.girl.data;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.ManSize;
import g.girl.model.Person;

/**
 * Created by sergej on 20.02.17.
 */

public interface UserDataSource {

    void getPerson(int id, UserDataSource.HomeCallback callback);

    interface HomeCallback {

        void onSuccess(Person person);

        void onFailure();
    }

    void getSize(int id, SizeCallback sizeCallback);

    interface SizeCallback {

        void onSuccess(ManSize manSize);

        void onFailure();
    }

    void getLike(int id, LikeCallback answerCallBack);

    interface LikeCallback {

        void onSuccess(List<AnswerToQuestion> answers);

    }

    void getDislike(int id, LikeCallback answerCallBack);
}
