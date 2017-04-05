package g.girl.view.fragmentDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Date;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.view.callBackActivity.CallBackDialogCalendar;

/**
 * Created by sergej on 30.03.17.
 */

public class CalendarUpdateDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private DatePicker mDatePickerBirthday;

    public static CalendarUpdateDialogFragment newInstance(int day, int month, int year) {
        CalendarUpdateDialogFragment fragment = new CalendarUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.DAY, day);
        args.putInt(Constant.MONTH, month);
        args.putInt(Constant.YEAR, year);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_calendar, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        mDatePickerBirthday = (DatePicker) view.findViewById(R.id.date_picker_update_birthday);
        mDatePickerBirthday.setMaxDate(new Date().getTime());
        mDatePickerBirthday.updateDate(
                getArguments().getInt(Constant.YEAR, 0),
                getArguments().getInt(Constant.MONTH, 0),
                getArguments().getInt(Constant.DAY, 0)
        );
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_update_birthday);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_update_birthday);
        mButtonNumberCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_update_birthday: {
                CallBackDialogCalendar activity = (CallBackDialogCalendar) getActivity();
                activity.onFinishedDialogCalendar(
                        mDatePickerBirthday.getYear(),
                        mDatePickerBirthday.getMonth(),
                        mDatePickerBirthday.getDayOfMonth()
                );
                this.dismiss();
                break;
            }
            case R.id.button_cancel_update_birthday: {
                this.dismiss();
                break;
            }
        }
    }
}
