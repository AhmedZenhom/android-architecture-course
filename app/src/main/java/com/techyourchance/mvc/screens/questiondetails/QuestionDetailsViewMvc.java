package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.OnQuestionDetailsInteractionListener> {

    interface OnQuestionDetailsInteractionListener {
        void onQuestionDetailsClick() ;
        void onQuestionDetailsLongClick() ;
    }

    void bindData(QuestionDetails questionDetails);
}
