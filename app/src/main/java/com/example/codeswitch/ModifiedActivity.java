package com.example.codeswitch;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class ModifiedActivity extends Activity {

    public String getEditText(int editTextId)
    {
        EditText editText = (EditText) findViewById(editTextId);
        return editText.getText().toString();
    }

    public void printEditText(int editTextId, String message)
    {
        EditText editText = (EditText) findViewById(editTextId);
        editText.setText(message);
    }

    public void printPrompt (int textId, String message)
    {
        TextView tv1 = (TextView) findViewById(textId);
        tv1.setText(message);
    }
}

