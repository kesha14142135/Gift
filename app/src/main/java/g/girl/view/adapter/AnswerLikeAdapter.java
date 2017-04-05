package g.girl.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import g.girl.R;
import g.girl.model.AnswerToQuestion;
import g.girl.view.callBackActivity.CallBackLike;

/**
 * Created by sergej on 26.02.17.
 */

public class AnswerLikeAdapter extends RecyclerView.Adapter<AnswerLikeAdapter.TestViewHolder> {

    private List<AnswerToQuestion> mAnswer;
    private Context mContext;
    private CallBackLike mCallBack;

    public AnswerLikeAdapter(List<AnswerToQuestion> tests, Context context, CallBackLike callBack) {
        mContext = context;
        mAnswer = tests;
        mCallBack = callBack;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_test,
                parent, false);
        TestViewHolder testViewHolder = new TestViewHolder(view);
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(final TestViewHolder holder, final int position) {
        Picasso.with(mContext).load(mAnswer.get(position).getUrl()).into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccess(mAnswer.get(position).getHashtag());
            }
        });
        holder.mTextViewName.setText(mAnswer.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mAnswer.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextViewName;

        public TestViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view_test);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_card);
        }
    }
}
