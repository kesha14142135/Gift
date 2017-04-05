package g.girl.presenter;

import java.util.List;

import g.girl.contract.QuestionContract;
import g.girl.data.QuestionsDataBaseSource;
import g.girl.data.QuestionsDataSource;
import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;

/**
 * Created by sergej on 07.03.17.
 */

public class QuestionPresenter implements QuestionContract.Presenter {

    QuestionContract.View mView;
    QuestionsDataSource mQuestionData;


    @Override
    public void addAnswer(List<AnswerToQuestion> answers) {
        mQuestionData.addAnswer(answers, new QuestionsDataSource.CallBackAdd() {
            @Override
            public void success() {

            }

            @Override
            public void error(String text) {
                mView.showError(text);
            }
        });
    }

    @Override
    public void getQuestion(int typeUser) {
        mQuestionData.getQuestion(typeUser, new QuestionsDataSource.CallBackGetQuestion() {
            @Override
            public void success(List<Question> questions) {
                mView.showQuestion(questions);
            }

            @Override
            public void error(String text) {
                mView.showError(text);
            }
        });
    }

    @Override
    public void attachView(QuestionContract.View view) {
        mView = view;
        mQuestionData = new QuestionsDataBaseSource(view.getContext());
    }

    @Override
    public void detachView() {

    }
}
