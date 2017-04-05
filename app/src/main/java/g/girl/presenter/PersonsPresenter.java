package g.girl.presenter;

import java.util.List;

import g.girl.R;
import g.girl.contract.PersonsContract;
import g.girl.data.PersonsDataBaseSource;
import g.girl.data.PersonsDataSource;
import g.girl.model.Person;

/**
 * Created by sergej on 08.02.17.
 */

public class PersonsPresenter implements PersonsContract.Presenter {

    private PersonsContract.View mView;
    private PersonsDataSource mPersonsDataSource;

    @Override
    public void addPerson(String name, int type) {
        mPersonsDataSource.addPerson(new Person(name, type, 0, -1, -1, -1, 0));
    }

    @Override
    public void getPersons() {
        mPersonsDataSource.readAll(new PersonsDataSource.PersonsCallback() {

            @Override
            public void onSuccess(List<Person> persons) {
                mView.showPersons(persons);
            }

            @Override
            public void onFailure() {
                mView.showError(mView.getContext().getString(R.string.error_receive_data));
            }
        });
    }

    @Override
    public void attachView(PersonsContract.View view) {
        mView = view;
        mPersonsDataSource = new PersonsDataBaseSource(mView.getContext());
    }

    @Override
    public void detachView() {

    }
}
