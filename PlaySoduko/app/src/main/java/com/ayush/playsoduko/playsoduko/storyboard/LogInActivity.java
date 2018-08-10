package com.ayush.playsoduko.playsoduko.storyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ayush.playsoduko.playsoduko.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * This is the start activity of the app. If the user is already logged in, it closes this activity
 * moves on to the next one. The activity provides facility to log in with Facebook authentication.
 * This activity utilises the Facebook SDK. Uses animation from a library linked below
 *
 * @author ayush ranjan
 * @see <a href="https://github.com/daimajia/AndroidViewAnimations">Animation Library</a>
 * @see <a href="https://developers.facebook.com/docs/facebook-login/android">Facebook Android Docs</a>
 * @since 09/04/17.
 */
public class LogInActivity extends Activity {

    public static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Intent homeActivityIntent;

    /**
     * This populates the screen with the activity xml layout and sets the log in button using
     * Facebook SDK.
     *
     * @param savedInstanceState default bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity_layout);
        homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);

        // check if user is already logged in
        if (isLoggedIn()) {
            startActivity(homeActivityIntent);
        }

        initLoginUtils();
        animateLogo();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.success_log_in),
                        Toast.LENGTH_SHORT).show();
                startActivity(homeActivityIntent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), getString(R.string.cancel_log_in),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_log_in),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * The is run when the user exits the app and re enters it.
     */
    @Override
    protected void onResume() {
        super.onResume();
        animateLogo();
    }

    /**
     * This uses the library to call animation on the logo elements to make it look cool!
     */
    private void animateLogo() {
        YoYo.with(Techniques.FadeIn)
                .duration(2500)
                .repeat(1)
                .playOn(findViewById(R.id.logo_first_login));
    }

    /**
     * Private helper function which initialises the components we need to Log In using FB SDK
     */
    private void initLoginUtils() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(EMAIL);
    }

    /**
     * This function is meant to forward the login results to the callbackManager created in
     * onCreate().
     *
     * @param requestCode holds an int value which tells about the type of request
     * @param resultCode  holds an int value which tells if the result is OK or not
     * @param data        The intent object containing all the data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Helper function which determines if the user is logged in or not.
     *
     * @return true if logged in
     */
    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
