package jamesapps.example.youtubeplayer;

import android.os.Bundle;
import android.view.ViewGroup;

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
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}