package g.girl.contract;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public interface QuestionContract {

    interface View extends BaseContract.View {
        void showQuestion(List<Question> questions);
    }

    interface Presenter extends BaseContract.Presenter<QuestionContract.View> {
        void addAnswer(List<AnswerToQuestion> answer);

        void getQuestion(int typeUser);
    }

}
