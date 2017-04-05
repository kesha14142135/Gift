package g.girl.view.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.common.collect.Lists;

import java.util.List;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.view.fragmentDialog.AddPersonDialog;
import g.girl.view.adapter.PersonsAdapter;
import g.girl.contract.PersonsContract;
import g.girl.model.Person;
import g.girl.presenter.PersonsPresenter;
import g.girl.view.callBackActivity.CallBackPerson;
import g.girl.view.callBackActivity.EditPersonDialogListener;


public class PersonActivity extends AppCompatActivity implements View.OnClickListener, PersonsContract.View, EditPersonDialogListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mFloatingButton;
    private CoordinatorLayout mCoordinatorLayout;
    private PersonsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person);
        initializationUser();
        mPresenter = new PersonsPresenter();
        mPresenter.attachView(this);
    }

    private void initializationUser() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_person);
        mLayoutManager = new LinearLayoutManager(this);
        mFloatingButton = (FloatingActionButton) findViewById(R.id.floating_action_button_person_add);
        mFloatingButton.setOnClickListener(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getVisibility() == View.VISIBLE && dy > 0) {
                    mFloatingButton.hide();
                } else if (recyclerView.getVisibility() == View.VISIBLE && dy < 0) {
                    mFloatingButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getPersons();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_person_add: {
                DialogFragment newFragment = AddPersonDialog.newInstance();
                newFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                newFragment.show(getFragmentManager(), getResources().getString(R.string.person_dialog));
                break;
            }
        }
    }

    @Override
    public void showPersons(List<Person> persons) {
        List<Person> personsRevers = Lists.reverse(persons);
        mAdapter = new PersonsAdapter(personsRevers, getContext(), new CallBackPerson() {
            @Override
            public void successTest(int id, int type) {
                Intent intent = new Intent(getContext(), TestActivity.class);
                intent.putExtra(Constant.ID, String.valueOf(id));
                intent.putExtra(Constant.TYPE, String.valueOf(type));
                startActivity(intent);
            }

            @Override
            public void successUser(int id, int type) {
                Intent intent = new Intent(getContext(), UserActivity.class);
                intent.putExtra(Constant.ID, String.valueOf(id));
                intent.putExtra(Constant.TYPE, String.valueOf(type));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onFinishEditDialog(String name, int type) {
        mPresenter.addPerson(name, type);
        mPresenter.getPersons();
    }
}

