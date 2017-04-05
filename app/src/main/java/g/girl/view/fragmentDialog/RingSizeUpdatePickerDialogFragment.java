package g.girl.view.fragmentDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Arrays;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.view.callBackActivity.CallBackDialogRingSize;

/**
 * Created by sergej on 30.03.17.
 */

public class RingSizeUpdatePickerDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private NumberPicker mNumberPicker;
    private String[] mSizesRing;

    public static RingSizeUpdatePickerDialogFragment newInstance(String size) {
        RingSizeUpdatePickerDialogFragment fragment = new RingSizeUpdatePickerDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constant.SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_size_picker, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        String sizeRing = getArguments().getString(Constant.SIZE);
        mSizesRing = getResources().getStringArray(R.array.ring_sizes);
        int index = 0;
        for (String size : mSizesRing) {
            if (size.equals(sizeRing)){
                break;
            }
                index++;
        }
        mNumberPicker = (NumberPicker) view.findViewById(R.id.number_picker_person_size_update);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(mSizesRing.length - 1);
        mNumberPicker.setDisplayedValues(mSizesRing);
        mNumberPicker.setValue(index);

        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_dialog_person_title);
        textViewTitle.setText(getResources().getString(R.string.ring_size));

        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_update_size);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_update_size);
        mButtonNumberCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_update_size: {
                CallBackDialogRingSize activity = (CallBackDialogRingSize) getActivity();
                activity.onFinishedDialogRingSize(mSizesRing[mNumberPicker.getValue()]);
                this.dismiss();
                break;
            }
            case R.id.button_cancel_update_size: {
                this.dismiss();
                break;
            }
        }
    }

}
