package com.redo.wu.camera;


import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by wu on 2015/11/30.
 */
public class BassActivity extends Activity {

    protected ImageView flashLight;
    protected ImageView cont;
    protected ImageView cont2;  //警笛
 
    protected Camera mCamera;
    protected Camera.Parameters mParameters;
    protected MediaPlayer mySong;
    protected MediaPlayer myJinDi;
    protected int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashLight = (ImageView) findViewById(R.id.content_main_img);
       
        cont = (ImageView) findViewById(R.id.conter);
        cont2 = (ImageView) findViewById(R.id.conter2);
        
        flashLight.setTag(false);
  

    }

}
