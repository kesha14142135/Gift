package g.girl.presenter;

import java.util.List;

import g.girl.R;
import g.girl.contract.UserContract;
import g.girl.data.UserDataBaseSource;
import g.girl.data.UserDataSource;
import g.girl.model.AnswerToQuestion;
import g.girl.model.ManSize;
import g.girl.model.Person;

/**
 * Created by sergej on 09.03.17.
 */

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View mView;
    private UserDataSource mUserDataSource;

    @Override
    public void attachView(UserContract.View view) {
        mView = view;
        mUserDataSource = new UserDataBaseSource(view.getContext());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getPerson(int id) {
        mUserDataSource.getPerson(id, new UserDataSource.HomeCallback() {
            @Override
            public void onSuccess(Person person) {
                mView.showPerson(person);
            }

            @Override
            public void onFailure() {
                mView.showError(mView.getContext().getString(R.string.error_no_data));
            }
        });
    }

    @Override
    public void getShowSize(int id) {
        mUserDataSource.getSize(id, new UserDataSource.SizeCallback() {
            @Override
            public void onSuccess(ManSize manSize) {
                mView.showSize(manSize);
            }

            @Override
            public void onFailure() {
                mView.showError(mView.getContext().getString(R.string.error_no_sizes));
            }
        });
    }

    @Override
    public void getLikeAnswer(int id) {
        mUserDataSource.getLike(id, new UserDataSource.LikeCallback() {

            @Override
            public void onSuccess(List<AnswerToQuestion> answers) {
                mView.showLikeAnswer(answers);
            }
        });
    }

    @Override
    public void getDislikeAnswer(int id) {
        mUserDataSource.getDislike(id, new UserDataSource.LikeCallback() {
            @Override
            public void onSuccess(List<AnswerToQuestion> answers) {
                mView.showDislikeAnswer(answers);
            }
        });
    }
}
