package g.girl.presenter;

import g.girl.R;
import g.girl.contract.TestUpdateContract;
import g.girl.data.TestDataSource;
import g.girl.data.TestUpdateDataBaseSource;
import g.girl.data.TestUpdateDataSource;
import g.girl.model.ManSize;

/**
 * Created by sergej on 03.04.17.
 */

public class TestUpdatePresenter implements TestUpdateContract.Presenter {

    TestUpdateContract.View mView;
    TestUpdateDataSource mDataSource;

    @Override
    public void attachView(TestUpdateContract.View view) {
        mView = view;
        mDataSource = new TestUpdateDataBaseSource(view.getContext());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getManSize(int id) {
        mDataSource.getSize(id, new TestUpdateDataSource.SizeCallback() {
            @Override
            public void onSuccess(ManSize manSize) {
                mView.showManSize(manSize);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void getBirthday(int id) {
        mDataSource.getBirthday(id, new TestUpdateDataSource.BirthdayCallback() {
            @Override
            public void onSuccess(int year, int month, int day) {
                mView.showBirthday(year, month, day);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void updateBirthday(int day, int month, int year, int id) {
        mDataSource.updateBirthday(day, month, year, id, new TestDataSource.TestCallback() {
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
    public void updateParameters(ManSize manSize, int id, CallBackTest callBackTest) {
        if (manSize.getStomach() != "" &&
                manSize.getHips() != "" &&
                manSize.getShoeSize() != "") {
            callBackTest.success();
            mDataSource.updateManSize(manSize, id, new TestUpdateDataSource.SizeUpdateCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure() {

                }
            });
        } else {
            callBackTest.error(mView.getContext().getString(R.string.error_not_fieled));
        }
    }
}
