package g.girl.view.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import g.girl.constant.Constant;
import g.girl.R;

import g.girl.contract.TestContract;
import g.girl.model.ManSize;
import g.girl.presenter.TestPresenter;
import g.girl.view.callBackActivity.CallBackDialogCalendar;
import g.girl.view.callBackActivity.CallBackDialogClothingSize;
import g.girl.view.callBackActivity.CallBackDialogRingSize;
import g.girl.view.callBackActivity.CallBackNumber;
import g.girl.view.callBackActivity.CallBackShoeSize;
import g.girl.view.fragmentDialog.CalendarDialogFragment;
import g.girl.view.fragmentDialog.ClothingSizePickerDialogFragment;
import g.girl.view.fragmentDialog.HeightDialogFragment;
import g.girl.view.fragmentDialog.RingSizePickerDialogFragment;
import g.girl.view.fragmentDialog.ShoeSizeDialogFragment;

public class TestActivity extends AppCompatActivity implements
        TestContract.View,
        View.OnClickListener,
        CallBackDialogCalendar,
        CallBackShoeSize,
        CallBackNumber,
        CallBackDialogClothingSize,
        CallBackDialogRingSize {

    private EditText mEditTextCalendar;
    private EditText mEditTextHeight;
    private EditText mEditTextRing;
    private EditText mEditTextClothing;
    private EditText mEditTextShoeSize;

    private DialogFragment mNumberDialogFragment;
    private DialogFragment mHeightDialogFragment;
    private DialogFragment mCalendarDialogFragment;
    private DialogFragment mRingDialogFragment;
    private DialogFragment mClothingDialogFragment;

    private FloatingActionButton mFloatingActionButtonBirthday;
    private CoordinatorLayout mCoordinatorLayout;
    private Button mButtonBack;
    private TestContract.Presenter mTestContract;
    private int mId;
    private int mType;
    private ManSize mManSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();

        mId = Integer.parseInt(intent.getStringExtra(Constant.ID));
        mType = Integer.parseInt(intent.getStringExtra(Constant.TYPE));

        mTestContract = new TestPresenter();
        mTestContract.attachView(this);
        mManSize = new ManSize();
        initializationUser();
        showDialogTest();
    }

    private void initializationUser() {
        mEditTextCalendar = (EditText) findViewById(R.id.edit_text_calendar);
        mEditTextHeight = (EditText) findViewById(R.id.edit_text_height);
        mEditTextRing = (EditText) findViewById(R.id.edit_text_ring);
        mEditTextClothing = (EditText) findViewById(R.id.edit_text_clothing);
        mEditTextShoeSize = (EditText) findViewById(R.id.edit_text_shoe_size);
        mFloatingActionButtonBirthday = (FloatingActionButton) findViewById(R.id.floating_action_button_person_birthday);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_test);
        mButtonBack = (Button) findViewById(R.id.button_back_pressed_test);

        mEditTextCalendar.setOnClickListener(this);
        mEditTextHeight.setOnClickListener(this);
        mEditTextRing.setOnClickListener(this);
        mEditTextClothing.setOnClickListener(this);
        mEditTextShoeSize.setOnClickListener(this);
        mFloatingActionButtonBirthday.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);

        mClothingDialogFragment = ClothingSizePickerDialogFragment.newInstance();
        mClothingDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mRingDialogFragment = RingSizePickerDialogFragment.newInstance();
        mRingDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mNumberDialogFragment = ShoeSizeDialogFragment.newInstance(31, 46);
        mNumberDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mHeightDialogFragment = HeightDialogFragment.newInstance(100, 229);
        mHeightDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mCalendarDialogFragment = CalendarDialogFragment.newInstance();
        mCalendarDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void showDialogTest() {
        mClothingDialogFragment.show(getFragmentManager(), getResources().getString(R.string.number_dialog));
        mRingDialogFragment.show(getFragmentManager(), getResources().getString(R.string.number_dialog));
        mNumberDialogFragment.show(getFragmentManager(), getResources().getString(R.string.number_dialog));
        mHeightDialogFragment.show(getFragmentManager(), getResources().getString(R.string.number_dialog));
        mCalendarDialogFragment.show(getFragmentManager(), getResources().getString(R.string.calendar_dialog));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_back_pressed_test: {
                onBackPressed();
            }
            case R.id.edit_text_calendar: {
                mCalendarDialogFragment.show(getFragmentManager(), getResources().getString(R.string.number_dialog));
            }
            case R.id.floating_action_button_person_birthday: {
                if (!"".equals(mEditTextCalendar.getText().toString())) {
                    mTestContract.addParameters(mManSize, mId, new TestContract.Presenter.CallBackTest() {
                        @Override
                        public void success() {
                            finish();
                            Intent intent = new Intent(getContext(), QuestionActivity.class);
                            intent.putExtra(Constant.ID, String.valueOf(mId));
                            intent.putExtra(Constant.TYPE, String.valueOf(mType));
                            getContext().startActivity(intent);
                        }

                        @Override
                        public void error(String message) {
                            Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
                } else {
                    Snackbar.make(mCoordinatorLayout, getResources().getString(R.string.error_birthday), Snackbar.LENGTH_LONG)
                            .show();
                }
                break;
            }
            case R.id.edit_text_height: {
                mHeightDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_shoe_size: {
                mNumberDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_ring: {
                mRingDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_clothing: {
                mClothingDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
        }
    }

    @Override
    public void onFinishedDialogCalendar(int year, int month, int day) {
        mTestContract.addBirthday(day, month, year, mId);
        mEditTextCalendar.setText(day + "/" + month + "/" + year);
    }

    @Override
    public void onFinishedDialogHeight(int number) {
        mManSize.setHeight(number);
        mEditTextHeight.setText(number + "");
    }

    @Override
    public void onFinishedDialogShoeSize(int number) {
        mManSize.setShoeSize(String.valueOf(number));
        mEditTextShoeSize.setText(number + "");
    }

    @Override
    public void onFinishedDialogRingSize(String size) {
        mManSize.setStomach(size);
        mEditTextRing.setText(size);
    }

    @Override
    public void onFinishedDialogClothingSize(String size) {
        mManSize.setHips(size);
        mEditTextClothing.setText(size);

        mTestContract.addParameters(mManSize, mId, new TestContract.Presenter.CallBackTest() {
            @Override
            public void success() {
                finish();
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                intent.putExtra(Constant.ID, String.valueOf(mId));
                intent.putExtra(Constant.TYPE, String.valueOf(mType));
                getContext().startActivity(intent);
            }

            @Override
            public void error(String message) {
                Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }
}

