package com.example.c1618639.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class MediaFragment extends Fragment {
//    implements YouTubePlayer.OnInitializedListener{
//        public static final String DEVELOPER_KEY = ""
//        private static final String VIDEO_ID = ""
//        private static final int RECOVERY_DIALOG_REQUEST = 1;
//
//        YouTubePlayerFragment myYoutubePlayerFragment;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_media);
//
//            myYoutubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
//                    .findFragmentById(R.id.youtubeplayerfragment);
//            myYoutubePlayerFragment.initialize(DEVELOPER_KEY, this);
//        }
//
//        @Override
//        protected void onInitializationFailure(YoutubePlayer.Provider provider,
//                                                YoutubeInitializationResult errorReason) {
//            if (errorReason.isUserRecoverableError()) {
//                errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
//            } else {
//                String errorMessage = String.format(
//                        "Error initializing player",
//                        errorReason.toString());
//                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG.show();
//            }
//        }
//        @Override
//        protected void onInitializationSuccess{YouTubePlayer.Provider provider, YoutubePlayer player,
//                                                boolean wasRestored) {
//                    if (!wasRestored) {
//                        player.cueVideo(VIDEO_ID);
//                    }
//            }
//    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_media, container, false);


        return v;
    }
}


