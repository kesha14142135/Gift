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
import g.girl.view.callBackActivity.CallBackDialogCalendar;

/**
 * Created by sergej on 30.03.17.
 */

public class CalendarDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mButtonNumberOk;
    private DatePicker mDatePickerBirthday;

    public static CalendarDialogFragment newInstance() {
        return new CalendarDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calendar, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        mDatePickerBirthday = (DatePicker) view.findViewById(R.id.date_picker_birthday);
        mDatePickerBirthday.setMaxDate(new Date().getTime());

        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_birthday);
        mButtonNumberOk.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_birthday: {
                CallBackDialogCalendar activity = (CallBackDialogCalendar) getActivity();
                activity.onFinishedDialogCalendar(
                        mDatePickerBirthday.getYear(),
                        mDatePickerBirthday.getMonth(),
                        mDatePickerBirthday.getDayOfMonth()
                );
                this.dismiss();
                break;
            }
        }
    }
}
