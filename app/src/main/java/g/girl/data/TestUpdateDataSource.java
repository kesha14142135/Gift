package g.girl.data;

import g.girl.model.ManSize;

/**
 * Created by sergej on 03.04.17.
 */

public interface TestUpdateDataSource {

    void updateBirthday(int day, int month, int year, int id, TestDataSource.TestCallback callback);

    interface TestCallback {

        void onSuccess();

        void onFailure();
    }

    void updateManSize(ManSize manSize, int id, SizeUpdateCallback callback);

    interface SizeUpdateCallback {

        void onSuccess();

        void onFailure();
    }

    void getSize(int id, SizeCallback sizeCallback);

    interface SizeCallback {

        void onSuccess(ManSize manSize);

        void onFailure();
    }

    void getBirthday(int id, BirthdayCallback sizeCallback);

    interface BirthdayCallback {

        void onSuccess(int year, int month, int day);

        void onFailure();
    }
}
