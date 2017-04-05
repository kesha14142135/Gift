package g.girl.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import g.girl.constant.Constant;
import g.girl.R;
import g.girl.contract.UserContract;
import g.girl.model.AnswerToQuestion;
import g.girl.model.ManSize;
import g.girl.model.Person;
import g.girl.presenter.UserPresenter;
import g.girl.view.adapter.AnswerDislikeAdapter;
import g.girl.view.adapter.AnswerLikeAdapter;
import g.girl.view.callBackActivity.CallBackLike;

public class UserActivity extends AppCompatActivity implements UserContract.View, View.OnClickListener {

    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerView mRecyclerViewLike;
    private RecyclerView mRecyclerViewDislike;
    private Button mButtonBack;
    private int mId;
    private LinearLayoutManager mLayoutManagerLike;
    private LinearLayoutManager mLayoutManagerDislike;
    private RecyclerView.Adapter mAdapter;
    private UserContract.Presenter presenter;
    private ManSize mManSize;
    private AlertDialog.Builder mAlertDialog;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        mId = Integer.parseInt(intent.getStringExtra(Constant.ID));
        initializationUser();

        presenter = new UserPresenter();
        presenter.attachView(this);
        presenter.getPerson(mId);
        presenter.getShowSize(mId);
        presenter.getLikeAnswer(mId);
        presenter.getDislikeAnswer(mId);
    }

    private void initializationUser() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.user_collapsing);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_user);
        mRecyclerViewLike = (RecyclerView) findViewById(R.id.recycler_view_answer_like);
        mRecyclerViewDislike = (RecyclerView) findViewById(R.id.recycler_view_answer_dislike);
        mButtonBack = (Button) findViewById(R.id.button_back_pressed_user);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                onBackPressed();
            }
        });
        mLayoutManagerLike = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewLike.setLayoutManager(mLayoutManagerLike);
        mLayoutManagerDislike = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewDislike.setLayoutManager(mLayoutManagerDislike);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button_user);
        mFloatingActionButton.setOnClickListener(this);
        mManSize = new ManSize();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showPerson(Person person) {

        CircleImageView circleImagePerson;
        TextView textViewBirthday;
        circleImagePerson = (CircleImageView) findViewById(R.id.image_person_user);
        textViewBirthday = (TextView) findViewById(R.id.text_view_birthday_user);

        switch (person.getType()) {
            case 0:
                circleImagePerson.setImageResource(R.drawable.ic_girl);
                break;
            case 1:
                circleImagePerson.setImageResource(R.drawable.ic_mother);
                break;
            case 2:
                circleImagePerson.setImageResource(R.drawable.ic_daughter);
                break;
            case 3:
                circleImagePerson.setImageResource(R.drawable.ic_grandfather);
                break;
            case 4:
                circleImagePerson.setImageResource(R.drawable.ic_man);
                break;
        }


        mCollapsingToolbarLayout.setTitle(person.getName() + ", " + calculateAge(new GregorianCalendar(person.getYear(), person.getMonth(), person.getDay())) + " лет");
        GregorianCalendar birthday = (GregorianCalendar) GregorianCalendar.getInstance();
        birthday.set(
                Calendar.YEAR,
                person.getMonth(),
                person.getDay()
        );
        textViewBirthday.setText(dateOutput(birthday));
    }

    private String dateOutput(Calendar birthday) {
        String dateOutput;
        int currentDay = (GregorianCalendar.getInstance()).get(Calendar.DAY_OF_YEAR);
        int birthdayData = currentDay - birthday.get(Calendar.DAY_OF_YEAR);
        if (birthdayData < 0) {
            birthdayData = birthday.get(Calendar.DAY_OF_YEAR) - currentDay;
            dateOutput = getContext().getResources().getQuantityString(R.plurals.review_day_string, birthdayData, birthdayData);
        } else if (birthdayData == 0) {
            dateOutput = getContext().getResources().getString(R.string.today);
        } else {
            dateOutput = getContext().getResources().getQuantityString(R.plurals.review_day_string,
                    (birthday.getActualMaximum(Calendar.DAY_OF_YEAR) - birthdayData),
                    (birthday.getActualMaximum(Calendar.DAY_OF_YEAR) - birthdayData));
        }
        return dateOutput;
    }

    public static Integer calculateAge(final Calendar birthday) {
        Calendar today = Calendar.getInstance();
        birthday.add(Calendar.DAY_OF_MONTH, -1);
        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= birthday.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    @Override
    public void showSize(ManSize manSize) {
        mManSize = manSize;
        TextView textViewHeight = (TextView) findViewById(R.id.text_view_height_user);
        textViewHeight.setText(getResources().getString(R.string.height_rus) + " " + manSize.getHeight() + " " + getResources().getString(R.string.cm));

        TextView textViewShoeSize = (TextView) findViewById(R.id.text_view_shoe_size_user);
        textViewShoeSize.setText(getResources().getString(R.string.shoe_size_rus) + " " + manSize.getShoeSize());

        TextView textViewShoeStomach = (TextView) findViewById(R.id.text_view_stomach_user);
        textViewShoeStomach.setText(getResources().getString(R.string.stomach_rus) + " " + manSize.getStomach());

        TextView textViewShoeHips = (TextView) findViewById(R.id.text_view_hips_user);
        textViewShoeHips.setText(getResources().getString(R.string.hips_rus) + " " + manSize.getHips());
    }

    private void showDialogAnswer(String title, String answer) {
        mAlertDialog = new AlertDialog.Builder(this);
        final View view = getLayoutInflater()
                .inflate(R.layout.dialog_text_answer, null);
        mAlertDialog.setView(view);

        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_dialog_title_answer);
        textViewTitle.setText(title);

        TextView textViewAnswer = (TextView) view.findViewById(R.id.text_view_answer);
        textViewAnswer.setText(answer);

        mAlertDialog.setPositiveButton(Constant.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog dialog = mAlertDialog.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void showLikeAnswer(List<AnswerToQuestion> answers) {
        TextView mTextViewLike = (TextView) findViewById(R.id.like);
        if (answers.size() == 0) {
            mTextViewLike.setVisibility(View.INVISIBLE);
        }
        mAdapter = new AnswerLikeAdapter(answers, this, new CallBackLike() {
            @Override
            public void onSuccess(String hashTag) {
                switch (hashTag) {
                    case "ring": {
                        showDialogAnswer(getResources().getString(R.string.stomach_rus), mManSize.getStomach());
                        break;
                    }
                    case "shoes": {
                        showDialogAnswer(getResources().getString(R.string.shoe_size_rus), mManSize.getShoeSize());
                        break;
                    }
                    case "clothes": {
                        showDialogAnswer(getResources().getString(R.string.hips_rus), mManSize.getHips());
                        break;
                    }
                }
            }
        });
        mRecyclerViewLike.setAdapter(mAdapter);
    }

    @Override
    public void showDislikeAnswer(List<AnswerToQuestion> answers) {
        TextView mTextViewDislike = (TextView) findViewById(R.id.dislike);
        if (answers.size() == 0) {
            mTextViewDislike.setVisibility(View.INVISIBLE);
        }
        mAdapter = new AnswerDislikeAdapter(answers, this);
        mRecyclerViewDislike.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_action_button_user:{
                Intent intent = new Intent(getContext(), TestUpdateActivity.class);
                intent.putExtra(Constant.ID, String.valueOf(mId));
                startActivity(intent);
                break;
            }
        }
    }
}
