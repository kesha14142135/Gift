package g.girl.data;

import java.util.List;

import g.girl.model.Person;

/**
 * Created by sergej on 07.02.17.
 */

public interface PersonsDataSource {

    void readAll(PersonsCallback callback);

    void addPerson(Person Person);

    interface PersonsCallback {

        void onSuccess(List<Person> persons);

        void onFailure();
    }

}
