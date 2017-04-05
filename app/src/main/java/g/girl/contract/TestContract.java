package g.girl.contract;

import g.girl.model.ManSize;

/**
 * Created by sergej on 19.02.17.
 */

public interface TestContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<TestContract.View> {

        void addBirthday(int day, int month, int year, int id);

        void addParameters(ManSize manSize, int id, CallBackTest callBackTest);

        interface CallBackTest {

            void success();

            void error(String message);
        }
    }
}
