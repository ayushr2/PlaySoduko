package com.ayush.playsoduko.playsoduko.storyboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ayush.playsoduko.playsoduko.R;
import com.ayush.playsoduko.playsoduko.utilities.CircleTransformation;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

// Library Imports

/**
 * This class displays the home page and enables users to chose whether they want to play sudoku or
 * solve it. This is a relatively simple activity. Uses animation from a library linked below
 *
 * @see <a href="https://github.com/daimajia/AndroidViewAnimations">Animation Library</a>
 */
public class HomeActivity extends Activity {

    public static final String ROUTE_PERMISSION = "/me/permissions/";
    public static final String POSITIVE_TAG = "Yes";
    public static final String NEGATIVE_TAG = "No";
    public static final String CANCEL_TAG = "Cancel";
    public static final String WELCOME_TAG = "Welcome ";
    private LottieAnimationView playButton;
    private LottieAnimationView solveButton;
    private Button logOutButton;
    private Intent intentPlay;
    private Intent intentSolve;
    // private ProfilePictureView profilePictureView;
    private ImageView profilePictureView;

    /**
     * This populates the screen with the activity xml layout and sets the button on click listeners.
     *
     * @param savedInstanceState default bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);

        initComponents();
        animateLogo();
        animateButtons();

        // button listeners
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPlay);
            }
        });

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSolve);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogOutDialog();
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
        animateButtons();
    }

    /**
     * This helper function displays a Dialog box asking the user if they want to remove the app from
     * their facebook completely or not. Then in both cases it logs the user out anyways.
     */
    private void showLogOutDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage(getString(R.string.log_out_prompt));

        builder.setPositiveButton(POSITIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logoutCompletely();
                LoginManager.getInstance().logOut();
                finish();
            }
        });

        builder.setNegativeButton(NEGATIVE_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginManager.getInstance().logOut();
                finish();
            }
        });

        builder.setNeutralButton(CANCEL_TAG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * Initialises buttons on this xml with their respective elements. Updates facebook profile if
     * changed because the Profile is loaded asynchronously so it returns null right after log in.
     */
    private void initComponents() {
        playButton = findViewById(R.id.play_animation);
        solveButton = findViewById(R.id.solve_animation);
        logOutButton = findViewById(R.id.log_out_button);
        profilePictureView = findViewById(R.id.profile_picture);

        // Checks if the profile is changed and hence current profile is null. If so updates the profile
        if (Profile.getCurrentProfile() != null) {
            updateUiForCurrentUser(Profile.getCurrentProfile());
        } else {
            ProfileTracker profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    this.stopTracking();
                    Profile.setCurrentProfile(currentProfile);
                    updateUiForCurrentUser(currentProfile);
                }
            };
            profileTracker.startTracking();
        }

        intentPlay = new Intent(this, PlayActivity.class);
        intentSolve = new Intent(this, SolveActivity.class);
    }

    private void updateUiForCurrentUser(Profile currentProfile) {
        Picasso.with(getApplicationContext()).load(currentProfile.getProfilePictureUri(120, 120))
                .transform(new CircleTransformation())
                .into(profilePictureView);
    }

    /**
     * Helper function which logs out the user completely. The user is actually logged out in the on
     * click method which calls this function because this call runs in the background synchronously
     * But the user needs to be logged out before starting new activity
     * I got this code from the following link :
     *
     * @see <a href="https://stackoverflow.com/questions/29305232/facebook-sdk-4-for-android-how-to-log-out-programmatically">
     * Stack Overflow Complete Log Out</a>
     */
    public void logoutCompletely() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), ROUTE_PERMISSION, null,
                HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                // do nothing. The user is logged out after this completes in the background
            }
        }).executeAsync();
    }

    /**
     * This helper function uses the library to call animation on the logo elements.
     */
    private void animateLogo() {
        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(1)
                .playOn(findViewById(R.id.logo_first_home));
    }

    /**
     * This helper function uses the library to call animation on the button elements.
     */
    private void animateButtons() {
        playButton.playAnimation();
    }

    /**
     * Ensures that the user is not able to go the LogIn activity without logging out because if
     * logged in and on that activity, the user would get stuck as there is no button to reach
     * Home activity again.
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Click the Log Out button on top left corner to exit.",
                Toast.LENGTH_SHORT).show();
    }
}