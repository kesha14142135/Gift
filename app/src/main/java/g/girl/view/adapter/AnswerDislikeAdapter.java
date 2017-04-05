package g.girl.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import g.girl.R;
import g.girl.model.AnswerToQuestion;

/**
 * Created by sergej on 26.02.17.
 */

public class AnswerDislikeAdapter extends RecyclerView.Adapter<AnswerDislikeAdapter.TestViewHolder> {

    private List<AnswerToQuestion> mAnswer;
    private Context mContext;

    public AnswerDislikeAdapter(List<AnswerToQuestion> tests, Context context) {
        mContext = context;
        mAnswer = tests;
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
