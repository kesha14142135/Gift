package g.girl.view.fragmentDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;
import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.view.callBackActivity.CallBackNumber;
import g.girl.view.callBackActivity.CallBackShoeSize;

/**
 * Created by sergej on 30.03.17.
 */

public class ShoeSizeUpdateDialogFragment extends DialogFragment implements View.OnClickListener, ObservableHorizontalScrollView.OnScrollChangedListener {
    private static final int LINE_RULER_MULTIPLE_SIZE = 3;
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private ScrollingValuePicker mScrollingValuePicker;
    private int mMin;
    private int mMax;
    private int mNumber;

    public static ShoeSizeUpdateDialogFragment newInstance(int min, int max, String currentValue) {
        ShoeSizeUpdateDialogFragment fragment = new ShoeSizeUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MIN_VALUE, min);
        args.putInt(Constant.MAX_VALUE, max);
        args.putInt(Constant.VALUE, Integer.valueOf(currentValue));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMin = getArguments().getInt(Constant.MIN_VALUE, 0);
        mMax = getArguments().getInt(Constant.MAX_VALUE, 0);

        View view = inflater.inflate(R.layout.dialog_update_ruler, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_dialog_ruler_title);
        textViewTitle.setText(getResources().getString(R.string.height_size));
        mScrollingValuePicker = (ScrollingValuePicker) view.findViewById(R.id.scrolling_value_picker_update);
        mScrollingValuePicker.setViewMultipleSize(LINE_RULER_MULTIPLE_SIZE);
        mScrollingValuePicker.setMaxValue(mMin, mMax);
        mScrollingValuePicker.setValueTypeMultiple(1);
        mScrollingValuePicker.setInitValue(getArguments().getInt(Constant.VALUE, 0));
        mScrollingValuePicker.getScrollView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mScrollingValuePicker.getScrollView().startScrollerTask();
                }
                return false;
            }
        });
        mScrollingValuePicker.setOnScrollChangedListener(this);
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_update_ruler);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_update_ruler);
        mButtonNumberCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_update_ruler: {
                CallBackShoeSize activity = (CallBackShoeSize) getActivity();
                activity.onFinishedDialogShoeSize(mNumber);
                this.dismiss();
                break;
            }
            case R.id.button_cancel_update_ruler: {
                this.dismiss();
                break;
            }
        }
    }

    @Override
    public void onScrollChanged(ObservableHorizontalScrollView observableHorizontalScrollView, int l, int t) {

    }

    @Override
    public void onScrollStopped(int l, int t) {
        mNumber = DWUtils.getValueAndScrollItemToCenter(mScrollingValuePicker.getScrollView()
                , l
                , t
                , mMax
                , mMin
                , mScrollingValuePicker.getViewMultipleSize());
    }
}
