package com.example.codeswitch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.codeswitch.model.BaseResponse;
import com.example.codeswitch.network.ApiManager;
import com.example.codeswitch.network.CustomCallback;
import com.example.codeswitch.network.Dao;

public class CreateAccountActivity extends ModifiedActivity {

    private String TAG = "CreateAccount";

    private String newEmail;
    private String newPassword;
    private String newConfirmPassword;
    private Dao dao;
    private Context thisContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        dao = ApiManager.getInstance().create(Dao.class);

    }

    public void onRegister (View view)
    {
        newEmail = getEditText(R.id.email_login_input);
        newPassword = getEditText(R.id.password_login_input);
        newConfirmPassword = getEditText(R.id.password_login_input2);

        if (newEmail.isEmpty() || newPassword.isEmpty() || newConfirmPassword.isEmpty())
        {
            Toast.makeText(this, "Complete All Fields", Toast.LENGTH_SHORT).show();
//            Log.i(TAG, "Complete All Fields");
            return;
        }
        else if (!newPassword.equals(newConfirmPassword))
        {
            Toast.makeText(this, "Confirmed Password is different", Toast.LENGTH_SHORT).show();
//            Log.i(TAG, "Confirmed Password is different");
            return;
        }

        Toast.makeText(this, "Password & Email OK", Toast.LENGTH_SHORT).show();

        // TODO : Authenticate validity of new password

        authenticateRegisterNew(newEmail, newPassword);

        // TODO : Display Success / Failure of adding new user

    }

    private void authenticateRegisterNew(String email, String password) {
        ApiManager.callApi(dao.createAccount(email, password), new CustomCallback<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                if (response.getSuccess()) {

                    // TODO: Redirect to ProfileActivity with the user's information
                    Log.d("Debug", response.toString());

                    //go to job search - tim
                    try {
                        Log.i (TAG, "SUCCESSFUL NEW USER!!");

                        Intent k = new Intent(CreateAccountActivity.this, JobSearchActivity.class);
                        startActivity(k);

                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    //end tim

                } else {
                    Toast.makeText(thisContext, response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
