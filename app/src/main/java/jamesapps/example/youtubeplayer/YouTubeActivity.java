package jamesapps.example.youtubeplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    // see https://www.udemy.com/course/master-android-7-nougat-java-app-development-step-by-step/learn/lecture/5601514#questions
    // "video 120" - "Get Google API Key" for details about getting an API key
    static final String GOOGLE_API_KEY = "AIzaSyDaIPKwAjKePVSK14T92UonEMaj5Jr7fSw";
    static final String YOUTUBE_VIDEO_ID = "2SP1dV7AZ8M";   // Spitting Image
    static final String YOUTUBE_PLAYLIST = "PLmyvLI4z6b246K0caaQYLW9nThyOQkgwq";

    private static final String TAG = "YouTubeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);

        // building an activity by code (as opposed to using the designer)
//        Button button1 = new Button(this);
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
//        button1.setText("Button added");
//        constraintLayout.addView(button1);

        // again, built using code...
        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(playerView);

        // initialise the player (if this succeeds, then onInitializationSuccess() is called, otherwise
        // onInitializationFailure() is called
        playerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is: " + provider.getClass().toString());
        Toast.makeText(this, "Initialised YouTube Player successfully", Toast.LENGTH_LONG).show();

        // couple youTube event listeners, below, with Player
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        // wasRestored is true when the user is resuming a previous playback
        // i.e. play does not start automatically if the video has already been started at a prior point (handy when rotating the screen)
        if (!wasRestored){
            // play the video
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()){
            // this calls Google ErrorDialogs and automatically returns the relevant message to the user (install YouTube App or update YouTube App etc.)
            // all other non-recoverable errors are handled in the else block below
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initialising the YouTubePlayer (%1$s)", youTubeInitializationResult.toString());
            // Toast messages appear on the phone and then automatically fade away without needed user dismissal
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    // implement the interface PlayBackEventListener for the relevant player events (demo) as callbacks
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YouTubeActivity.this, "Video is playing OK", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YouTubeActivity.this, "Video is paused", Toast.LENGTH_LONG).show();
        }

        // this is also called when a video ends, see onVideoEnded() below
        // note that messages are not displayed at the same time but in sequence
        @Override
        public void onStopped() {
            Toast.makeText(YouTubeActivity.this, "Video has been stopped", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    // ...same demo intent below...
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YouTubeActivity.this, "Video ad running", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YouTubeActivity.this, "Video has started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YouTubeActivity.this, "Video has ended", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}