package g.girl.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import g.girl.R;
import g.girl.constant.Constant;
import g.girl.contract.QuestionContract;
import g.girl.contract.QuestionUpdateContract;
import g.girl.model.AnswerToQuestion;
import g.girl.model.Question;
import g.girl.presenter.QuestionPresenter;
import g.girl.presenter.QuestionUpdatePresenter;
import g.girl.view.adapter.QuestionAdapter;
import g.girl.view.callBackActivity.CallBackQuestion;

public class QuestionUpdateActivity extends AppCompatActivity implements QuestionUpdateContract.View, View.OnClickListener {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private QuestionUpdateContract.Presenter mPresenter;
    private CoordinatorLayout mCoordinatorLayout;
    private List<AnswerToQuestion> mAnswer;
    private Button mButtonComplete;
    private int mIdType;
    private int mIdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        mIdType = Integer.parseInt(intent.getStringExtra(Constant.TYPE));
        mIdUser = Integer.parseInt(intent.getStringExtra(Constant.ID));

        initializationUser();

        mAnswer = new ArrayList<>();
        mPresenter = new QuestionUpdatePresenter();
        mPresenter.attachView(this);
        mPresenter.getQuestion(mIdType);
    }

    private void initializationUser() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_question);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_question);
        mButtonComplete = (Button) findViewById(R.id.button_complite);
        mButtonComplete.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_complite: {
                //mPresenter.addAnswer(mAnswer);
                //finish();
                break;
            }
        }
    }

    @Override
    public void showQuestionAnswer(List<Question> questions, List<AnswerToQuestion> answer) {
        for (Question question : questions) {
            mAnswer.add(new AnswerToQuestion(question, mIdUser, false));
        }
        mAdapter = new QuestionAdapter(questions, new CallBackQuestion() {
            @Override
            public void success(int position, boolean checked) {
                mAnswer.get(position).setSelected(checked);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
