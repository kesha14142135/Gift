package g.girl.presenter;

import g.girl.R;
import g.girl.contract.TestContract;
import g.girl.data.TestDataBaseSource;
import g.girl.data.TestDataSource;
import g.girl.model.ManSize;

/**
 * Created by sergej on 19.02.17.
 */

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View mView;
    private TestDataSource mTestDataSource;

    @Override
    public void addBirthday(int day, int month, int year, int id) {
        mTestDataSource.addBirthday(day, month, year, id, new TestDataSource.TestCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure() {
                mView.showError(mView.getContext().getString(R.string.error_birthday_not_changed));
            }
        });
    }

    @Override
    public void addParameters(final ManSize manSize, final int id, CallBackTest callBackTest) {
        if (manSize.getStomach() != "" &&
                manSize.getHips() != "" &&
                manSize.getShoeSize() != "") {
            callBackTest.success();
            mTestDataSource.addManSize(manSize, id);
        } else {
            callBackTest.error(mView.getContext().getString(R.string.error_not_fieled));
        }
    }

    @Override
    public void attachView(TestContract.View view) {
        mView = view;
        mTestDataSource = new TestDataBaseSource(mView.getContext());
    }

    @Override
    public void detachView() {

    }
}
