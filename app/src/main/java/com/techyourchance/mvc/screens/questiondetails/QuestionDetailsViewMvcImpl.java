package com.techyourchance.mvc.screens.questiondetails;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseObservableViewMvc<QuestionDetailsViewMvc.OnQuestionDetailsInteractionListener> implements QuestionDetailsViewMvc {

    private TextView titleTV , bodyTV ;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_question_details, parent, false));

        titleTV = findViewById(R.id.textView);
        bodyTV = findViewById(R.id.textView2);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (OnQuestionDetailsInteractionListener listener : getListeners()) {
                    listener.onQuestionDetailsClick();
                }
            }
        });
        getRootView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for (OnQuestionDetailsInteractionListener listener : getListeners()) {
                    listener.onQuestionDetailsLongClick();
                }
                return true;
            }
        });
    }
    @Override
    public void bindData(QuestionDetails questionDetails) {
        titleTV.setText(questionDetails.getTitle());
        bodyTV.setText(questionDetails.getBody());
    }
}
