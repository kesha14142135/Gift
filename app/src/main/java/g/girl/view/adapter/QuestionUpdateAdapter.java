package g.girl.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import g.girl.R;
import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;
import g.girl.view.callBackActivity.CallBackQuestion;

/**
 * Created by sergej on 26.02.17.
 */

public class QuestionUpdateAdapter extends RecyclerView.Adapter<QuestionUpdateAdapter.TestViewHolder> {

    private List<Question> mQuestions;
    private List<AnswerToQuestion> mAnswers;
    private CallBackQuestion mCallBack;

    public QuestionUpdateAdapter(List<Question> questions, List<AnswerToQuestion> answers, CallBackQuestion callBack) {
        mQuestions = questions;
        mAnswers = answers;
        mCallBack = callBack;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question,
                parent, false);
        TestViewHolder testViewHolder = new TestViewHolder(view);
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(final TestViewHolder holder, final int position) {
        holder.textViewName.setText(mQuestions.get(position).getName());
        if (mQuestions.get(position).getHashtag() == mAnswers.get(position).getHashtag()) {
            holder.checkBoxSelected.setSelected(true);
        }
        holder.checkBoxSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.success(position, holder.checkBoxSelected.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private CheckBox checkBoxSelected;

        public TestViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.text_view_question);
            checkBoxSelected = (CheckBox) itemView.findViewById(R.id.check_box_question);
        }
    }
}
