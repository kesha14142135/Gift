package g.girl.view.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.contract.TestUpdateContract;
import g.girl.model.ManSize;
import g.girl.presenter.TestUpdatePresenter;
import g.girl.view.callBackActivity.CallBackDialogCalendar;
import g.girl.view.callBackActivity.CallBackDialogClothingSize;
import g.girl.view.callBackActivity.CallBackDialogRingSize;
import g.girl.view.callBackActivity.CallBackNumber;
import g.girl.view.callBackActivity.CallBackShoeSize;
import g.girl.view.fragmentDialog.CalendarUpdateDialogFragment;
import g.girl.view.fragmentDialog.ClothingSizePickerDialogFragment;
import g.girl.view.fragmentDialog.ClothingSizeUpdatePickerDialogFragment;
import g.girl.view.fragmentDialog.HeightDialogFragment;
import g.girl.view.fragmentDialog.HeightUpdateDialogFragment;
import g.girl.view.fragmentDialog.RingSizePickerDialogFragment;
import g.girl.view.fragmentDialog.RingSizeUpdatePickerDialogFragment;
import g.girl.view.fragmentDialog.ShoeSizeDialogFragment;
import g.girl.view.fragmentDialog.ShoeSizeUpdateDialogFragment;

public class TestUpdateActivity extends AppCompatActivity implements
        TestUpdateContract.View, View.OnClickListener, CallBackDialogCalendar,
        CallBackShoeSize, CallBackNumber, CallBackDialogClothingSize,
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
    private TestUpdateContract.Presenter mTestContract;
    private int mId;
    private int mYear;
    private int mMonth;
    private int mDay;
    private ManSize mManSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_test);
        Intent intent = getIntent();
        mId = Integer.parseInt(intent.getStringExtra(Constant.ID));
        initializationUser();
        mTestContract = new TestUpdatePresenter();
        mTestContract.attachView(this);
        mManSize = new ManSize();
        mTestContract.getBirthday(mId);
        mTestContract.getManSize(mId);
        initializationDialogFragment();
    }

    private void initializationUser() {
        mEditTextCalendar = (EditText) findViewById(R.id.edit_text_update_calendar);
        mEditTextHeight = (EditText) findViewById(R.id.edit_text_update_height);
        mEditTextRing = (EditText) findViewById(R.id.edit_text_update_ring);
        mEditTextClothing = (EditText) findViewById(R.id.edit_text_update_clothing);
        mEditTextShoeSize = (EditText) findViewById(R.id.edit_text_update_shoe_size);
        mFloatingActionButtonBirthday = (FloatingActionButton) findViewById(R.id.floating_action_button_update_person_birthday);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_update_test);
        mButtonBack = (Button) findViewById(R.id.button_back_pressed_update_test);

        mEditTextCalendar.setOnClickListener(this);
        mEditTextHeight.setOnClickListener(this);
        mEditTextRing.setOnClickListener(this);
        mEditTextClothing.setOnClickListener(this);
        mEditTextShoeSize.setOnClickListener(this);
        mFloatingActionButtonBirthday.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
    }

    private void initializationDialogFragment() {
        mClothingDialogFragment = ClothingSizeUpdatePickerDialogFragment.newInstance(mManSize.getHips());
        mClothingDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mRingDialogFragment = RingSizeUpdatePickerDialogFragment.newInstance(mManSize.getStomach());
        mRingDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mNumberDialogFragment = ShoeSizeUpdateDialogFragment.newInstance(31, 46, mManSize.getShoeSize());
        mNumberDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mHeightDialogFragment = HeightUpdateDialogFragment.newInstance(100, 229, mManSize.getHeight());
        mHeightDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mCalendarDialogFragment = CalendarUpdateDialogFragment.newInstance(mDay, mMonth, mYear);
        mCalendarDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
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
            case R.id.floating_action_button_update_person_birthday: {
                mTestContract.updateParameters(mManSize, mId, new TestUpdateContract.Presenter.CallBackTest() {
                    @Override
                    public void success() {
                        onBackPressed();
                    }

                    @Override
                    public void error(String message) {
                        showError(getResources().getString(R.string.error_no_sizes));
                    }
                });
                onBackPressed();
                break;
            }
            case R.id.edit_text_update_calendar: {
                mCalendarDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_update_height: {
                mHeightDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_update_shoe_size: {
                mNumberDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_update_ring: {
                mRingDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_update_clothing: {
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
        mTestContract.updateBirthday(day, month, year, mId);
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

    }

    @Override
    public void showManSize(ManSize manSize) {
        mManSize = manSize;
        mEditTextHeight.setText(manSize.getHeight() + "");
        mEditTextShoeSize.setText(manSize.getShoeSize() + "");
        mEditTextClothing.setText(manSize.getHips() + "");
        mEditTextRing.setText(manSize.getStomach() + "");
    }

    @Override
    public void showBirthday(int year, int month, int day) {
        mEditTextCalendar.setText(day + "/" + month + "/" + year);
        mYear = year;
        mMonth = month;
        mDay = day;
    }
}

