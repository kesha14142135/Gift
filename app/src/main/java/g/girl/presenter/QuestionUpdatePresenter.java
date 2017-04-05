package g.girl.presenter;

import java.util.List;

import g.girl.contract.QuestionUpdateContract;
import g.girl.data.QuestionsDataSource;
import g.girl.model.AnswerToQuestion;

/**
 * Created by sergej on 07.03.17.
 */

public class QuestionUpdatePresenter implements QuestionUpdateContract.Presenter {

    QuestionUpdateContract.View mView;
    QuestionsDataSource mQuestionData;

    @Override
    public void updateAnswer(List<AnswerToQuestion> answer) {

    }

    @Override
    public void getQuestion(int typeUser) {

    }

    @Override
    public void getAnswer(int idUser) {

    }

    @Override
    public void attachView(QuestionUpdateContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
