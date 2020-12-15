package com.example.videoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private Button playOnline, playLocal;
    private int Request_CODE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.video_view);
        playLocal = findViewById(R.id.play_Local);

        playLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("video/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Video"), Request_CODE);
            }
        });
    }

    private void playVideo(Uri uri) {
        MediaController controller = new MediaController(MainActivity.this);
        videoView.setMediaController(controller);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getData();
                    playVideo(uri);
                }
            }
        }
    }
}