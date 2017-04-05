package g.girl.data;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public interface QuestionsUpdateDataSource {

    void updateAnswers(List<AnswerToQuestion> answers, CallBackUpdate callBack);

    interface CallBackUpdate {

        void success();

        void error();
    }

    void getQuestions(int typeUser, CallBackGetQuestions callBack);

    interface CallBackGetQuestions {

        void success(List<Question> questions);

        void error(String text);
    }

    void getAnswers(int idUser, CallBackGetAnswers callBack);

    interface CallBackGetAnswers {

        void onSuccess(List<AnswerToQuestion> questions);

        void error(String text);
    }
}
