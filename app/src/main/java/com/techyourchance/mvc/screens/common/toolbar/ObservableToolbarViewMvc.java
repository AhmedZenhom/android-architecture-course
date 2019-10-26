package com.techyourchance.mvc.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;

public class ObservableToolbarViewMvc extends BaseObservableViewMvc<ObservableToolbarViewMvc.ToolbarListener> {

    public interface ToolbarListener {
        void onBackPressed() ;
    }
    private final TextView mTxtTitle;

    public ObservableToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);

        ImageButton mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.setVisibility(View.VISIBLE);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ToolbarListener listener : getListeners()){
                    listener.onBackPressed();
                }
            }
        });
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }
}
