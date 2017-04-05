package g.girl.data;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public interface QuestionsDataSource {

    void addAnswer(List<AnswerToQuestion> answers, CallBackAdd callBack);

    interface CallBackAdd {

        void success();

        void error(String text);
    }

    void getQuestion(int typeUser, CallBackGetQuestion callBack);

    interface CallBackGetQuestion {

        void success(List<Question> questions);

        void error(String text);
    }
}
