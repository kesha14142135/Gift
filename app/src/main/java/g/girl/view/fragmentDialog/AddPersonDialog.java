package g.girl.view.fragmentDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import g.girl.R;
import g.girl.view.callBackActivity.EditPersonDialogListener;

/**
 * Created by sergej on 31.03.17.
 */

public class AddPersonDialog extends DialogFragment implements View.OnClickListener {
    private EditText mEditTextName;
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private RadioGroup mRadioGroup;


    public static AddPersonDialog newInstance() {
        return new AddPersonDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_person, container, false);
        mEditTextName = (EditText) view.findViewById(R.id.edit_text_person_name);
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_person);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_person);
        mButtonNumberCancel.setOnClickListener(this);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_person);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_person: {
                int type = 0;
                if ("".equals(mEditTextName.getText().toString())) {
                    mEditTextName.setError(getResources().getString(R.string.error_name));
                } else {
                    switch (mRadioGroup.getCheckedRadioButtonId()) {
                        case R.id.radio_button_mother:
                            type = 1;
                            break;
                        case R.id.radio_button_daughter:
                            type = 2;
                            break;
                        case R.id.radio_button_grandfather:
                            type = 3;
                            break;
                        case R.id.radio_button_son:
                            type = 4;
                            break;
                    }
                    EditPersonDialogListener activity = (EditPersonDialogListener) getActivity();
                    activity.onFinishEditDialog(mEditTextName.getText().toString(),type);
                    this.dismiss();
                }
                break;
            }
            case R.id.button_cancel_person: {
                this.dismiss();
                break;
            }
        }
    }
}

