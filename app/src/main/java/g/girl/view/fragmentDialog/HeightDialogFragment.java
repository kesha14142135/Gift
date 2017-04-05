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

/**
 * Created by sergej on 30.03.17.
 */

public class HeightDialogFragment extends DialogFragment implements View.OnClickListener, ObservableHorizontalScrollView.OnScrollChangedListener {
    private static final int LINE_RULER_MULTIPLE_SIZE = 10;
    private Button mButtonNumberOk;
    private ScrollingValuePicker mScrollingValuePicker;
    private int mMin;
    private int mMax;
    private int mNumber;

    public static HeightDialogFragment newInstance(int min, int max) {
        HeightDialogFragment fragment = new HeightDialogFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MIN_VALUE, min);
        args.putInt(Constant.MAX_VALUE, max);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMin = getArguments().getInt(Constant.MIN_VALUE, 0);
        mMax = getArguments().getInt(Constant.MAX_VALUE, 0);

        View view = inflater.inflate(R.layout.dialog_ruler, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_dialog_ruler_title);
        textViewTitle.setText(getResources().getString(R.string.height_size));
        mScrollingValuePicker = (ScrollingValuePicker) view.findViewById(R.id.scrolling_value_picker);
        mScrollingValuePicker.setViewMultipleSize(LINE_RULER_MULTIPLE_SIZE);
        mScrollingValuePicker.setMaxValue(mMin, mMax);
        mScrollingValuePicker.setValueTypeMultiple(5);
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
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_ruler);
        mButtonNumberOk.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_ruler: {
                CallBackNumber activity = (CallBackNumber) getActivity();
                activity.onFinishedDialogHeight(mNumber);
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
