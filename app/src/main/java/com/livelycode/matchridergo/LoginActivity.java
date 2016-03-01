package com.livelycode.matchridergo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.livelycode.matchridergo.data.MatchRiderError;
import com.livelycode.matchridergo.data.MatchRiderException;
import com.livelycode.matchridergo.data.Session;
import com.livelycode.matchridergo.global.DataReceiver;
import com.livelycode.matchridergo.global.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, DataReceiver.Receiver {

    private String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private LoginActivity instance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        DataReceiver receiver = new DataReceiver(new Handler());
        receiver.setReceiver(instance);
        Intent dataIntent = new Intent(Intent.ACTION_SYNC, null, instance, DataService.class);
        dataIntent.putExtra("receiver", receiver);
        dataIntent.putExtra("email", mEmailView.getText().toString());
        dataIntent.putExtra("password", mPasswordView.getText().toString());
        dataIntent.putExtra("request", DataService.LOGIN);
        startService(dataIntent);

/*        HttpResultReceiver receiver = new HttpResultReceiver(new Handler());
        receiver.setReceiver(instance);
        Intent httpIntent = new Intent(Intent.ACTION_SYNC, null, instance, HttpService.class);
        httpIntent.putExtra("endpoint", "/login");
        JSONObject loginData = new JSONObject();
        try {
            loginData.put("email", mEmailView.getText().toString());
            loginData.put("password", mPasswordView.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpIntent.putExtra("receiver", receiver);
        httpIntent.putExtra("data", loginData.toString());
        startService(httpIntent);*/

        //TODO: Check for valid password and email!
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        final int shortAnimTime = getResources().getInteger(android.R.integer.config_longAnimTime);

        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0.25F).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? .25F : 1F);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) throws MatchRiderException {
        switch (resultCode)
        {
            case DataService.STATUS_RUNNING:
                showProgress(true);
                break;

            case DataService.STATUS_FINISHED:
                showProgress(false);
                MatchRiderError error = resultData.getParcelable("error");

                if(error == null) {
                    Intent bookedRidesIntent = new Intent(instance, BookedRidesActivity.class);
                    startActivity(bookedRidesIntent);
                    finish();
                } else {
                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;

            case DataService.STATUS_ERROR:
                showProgress(false);
                break;
        }
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

}

