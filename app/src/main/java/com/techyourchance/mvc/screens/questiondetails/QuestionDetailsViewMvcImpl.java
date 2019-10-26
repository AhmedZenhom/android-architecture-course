package com.techyourchance.mvc.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.toolbar.ObservableToolbarViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;


public class QuestionDetailsViewMvcImpl extends BaseObservableViewMvc<QuestionDetailsViewMvc.Listener> implements QuestionDetailsViewMvc , ObservableToolbarViewMvc.ToolbarListener {

    private final TextView mTxtQuestionTitle;
    private final TextView mTxtQuestionBody;
    private final ProgressBar mProgressBar;

    private final ObservableToolbarViewMvc observableToolbarViewMvc ;
    private final Toolbar toolbar ;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container , ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));

        mTxtQuestionTitle = findViewById(R.id.txt_question_title);
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mProgressBar = findViewById(R.id.progress);

        toolbar = findViewById(R.id.toolbar) ;
        observableToolbarViewMvc = viewMvcFactory.getObservableToolbarViewMvc(container);
        observableToolbarViewMvc.registerListener(this);
        observableToolbarViewMvc.setTitle("Question details");
        toolbar.addView(observableToolbarViewMvc.getRootView());
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionTitle = question.getTitle();
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        for(QuestionDetailsViewMvc.Listener listener : getListeners()){
            listener.onBackButtonPressed();
        }
    }
}
