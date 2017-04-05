package g.girl.data;

import g.girl.model.ManSize;

/**
 * Created by sergej on 19.02.17.
 */

public interface TestDataSource {

    void addBirthday(int day, int month, int year, int id, TestDataSource.TestCallback callback);

    interface TestCallback {

        void onSuccess();

        void onFailure();
    }

    void addManSize(ManSize manSize, int id);

    void getManSize(int id, ManSizeCallback manSizeCallback);

    interface ManSizeCallback {

        void onSuccess(ManSize manSize);

        void onFailure();
    }
}
