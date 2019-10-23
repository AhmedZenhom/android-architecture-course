package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity implements QuestionDetailsViewMvc.OnQuestionDetailsInteractionListener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }
    private String questionID ;

    private QuestionDetailsViewMvc viewMvc ;
    private StackoverflowApi stackoverflowApi ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null) ;
        viewMvc.registerListener(this);
        setContentView(viewMvc.getRootView());

        questionID = getIntent().getStringExtra(EXTRA_QUESTION_ID);
        stackoverflowApi = getCompositionRoot().getStackoverflowApi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionDetails();
    }

    private void fetchQuestionDetails() {
        stackoverflowApi.fetchQuestionDetails(questionID).enqueue(new Callback<QuestionDetailsResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                if (response.isSuccessful()) {
                    bindQuestions(response.body().getQuestion());
                } else {
                    networkCallFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                networkCallFailed();
            }
        } );
    }

    private void bindQuestions(QuestionSchema question) {
        viewMvc.bindData(new QuestionDetails(question.getId(),question.getTitle(),question.getBody()));
    }

    private void networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuestionDetailsClick() {
        Toast.makeText(this, R.string.details_click, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuestionDetailsLongClick() {
        Toast.makeText(this, R.string.details_long_click, Toast.LENGTH_SHORT).show();
    }
}
