package com.ayush.playsoduko.playsoduko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
    public static final String USER_ID = "Player ID";
    public static final String AUTH_TOKEN = "Auth Token";
    private CallbackManager callbackManager;
    private LoginButton loginButton;

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

        // check if user is already logged in
        if (isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        initLoginUtils();

        animateLogo();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.success_log_in),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra(USER_ID, loginResult.getAccessToken().getUserId());
                intent.putExtra(AUTH_TOKEN, loginResult.getAccessToken().getToken());
                startActivity(intent);
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
        YoYo.with(Techniques.BounceInLeft)
                .duration(2500)
                .repeat(1)
                .playOn(findViewById(R.id.logo_first_login));

        YoYo.with(Techniques.BounceInRight)
                .duration(2500)
                .repeat(1)
                .playOn(findViewById(R.id.logo_second_login));
    }

    /**
     * Private helper function which initialises the components we need to Log In using FB SDK
     */
    private void initLoginUtils() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
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
