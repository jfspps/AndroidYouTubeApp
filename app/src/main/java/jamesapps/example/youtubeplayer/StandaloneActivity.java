package jamesapps.example.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_standalone);
        Button btnPlayVideo = (Button) findViewById(R.id.btnPlayVideo);
        Button btnPlaylist = (Button) findViewById(R.id.btnPlaylist);

        btnPlayVideo.setOnClickListener(this);
        btnPlaylist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // launch the YouTube app from this app (Intent objects effectively bind activities [single screen] together and can also grant access to actions from other apps)
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btnPlayVideo:
//                intent = YouTubeStandalonePlayer.createVideoIntent(this, YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOUTUBE_VIDEO_ID);

                //play the video automatically from the beginning in full-screen mode
                intent = YouTubeStandalonePlayer.createVideoIntent(this,
                        YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOUTUBE_VIDEO_ID, 0, true, false);
                break;
            case R.id.btnPlaylist:
//                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOUTUBE_PLAYLIST);

                //play the playlist automatically in full screen, starting at the first video from the beginning
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOUTUBE_PLAYLIST, 0, 0, true, false);
                break;
        }

        if (intent != null){
            startActivity(intent);
        }

    }
}
