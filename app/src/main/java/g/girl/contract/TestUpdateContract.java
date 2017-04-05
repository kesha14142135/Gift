package g.girl.contract;

import g.girl.model.ManSize;

/**
 * Created by sergej on 19.02.17.
 */

public interface TestUpdateContract {

    interface View extends BaseContract.View {

        void showManSize(ManSize manSize);

        void showBirthday(int year, int month, int day);
    }

    interface Presenter extends BaseContract.Presenter<TestUpdateContract.View> {

        void getManSize(int id);

        void getBirthday(int id);

        void updateBirthday(int day, int month, int year, int id);

        void updateParameters(ManSize manSize, int id, CallBackTest callBackTest);

        interface CallBackTest {

            void success();

            void error(String message);
        }
    }
}
