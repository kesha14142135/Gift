package g.girl.contract;

import java.util.List;

import g.girl.model.AnswerToQuestion;
import g.girl.model.ManSize;
import g.girl.model.Person;

/**
 * Created by sergej on 09.03.17.
 */

public interface UserContract {
    interface View extends BaseContract.View {

        void showPerson(Person person);

        void showSize(ManSize manSize);

        void showLikeAnswer(List<AnswerToQuestion> answers);

        void showDislikeAnswer(List<AnswerToQuestion> answers);
    }

    interface Presenter extends BaseContract.Presenter<UserContract.View> {

        void getPerson(int id);

        void getShowSize(int id);

        void getLikeAnswer(int id);

        void getDislikeAnswer(int id);
    }
}
