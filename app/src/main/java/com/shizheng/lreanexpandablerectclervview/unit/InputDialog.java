package com.shizheng.lreanexpandablerectclervview.unit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shizheng.lreanexpandablerectclervview.R;

import java.util.Objects;

public class InputDialog extends AlertDialog {

    private EditText editText;
    private Context context;
    private onDialogButtonClick onDialogButtonClick;
    private ProgressBar dialogProgressBar;
    private  Button button;

    public InputDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inptu_dialog_view);

        editText = findViewById(R.id.input_text);
        dialogProgressBar = findViewById(R.id.dialog_progress_bar);
        dialogProgressBar.setVisibility(View.GONE);
        button = findViewById(R.id.btn_ok);
        TextView tv_Title = findViewById(R.id.dialog_textView);
        tv_Title.setText(R.string.dialog_title_text);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
               onDialogButtonClick.onHitUp(view);
            }
        });
        Objects.requireNonNull(this.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    public String getEditTextString(){
        return editText.getText().toString();
    }

    public void setOnDialogButtonClick(onDialogButtonClick onDialogButtonClick){
        this.onDialogButtonClick = onDialogButtonClick;
    }

    public void setDialogProgressBarVisibility(){
        dialogProgressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
    }

    public void setDialogProgressBarGone(){
        dialogProgressBar.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
    }

    public void setEditTextError(String errorText){
        editText.setError(errorText);
    }

    public interface onDialogButtonClick {
        void onHitUp(View view);
    }

}
