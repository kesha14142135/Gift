package g.girl.contract;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public interface QuestionUpdateContract {

    interface View extends BaseContract.View {

        void showQuestionAnswer(List<Question> questions, List<AnswerToQuestion> answer);

    }

    interface Presenter extends BaseContract.Presenter<QuestionUpdateContract.View> {

        void updateAnswer(List<AnswerToQuestion> answer);

        void getQuestion(int typeUser);

        void getAnswer(int idUser);
    }

}
