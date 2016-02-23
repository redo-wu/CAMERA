package com.redo.wu.camera;


import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wu on 2015/11/30.
 */
public class FlashLight extends BassActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        LayoutParams laParams = (LayoutParams)cont.getLayoutParams();
        LayoutParams laParams2 = (LayoutParams)cont2.getLayoutParams();
        laParams.height = point.y/8;
        laParams.width = point.x/5;
        
        laParams2.height = point.y/20;
        laParams2.width = point.x/6;

        cont.setLayoutParams(laParams);
        cont2.setLayoutParams(laParams2);
    }

   
    //闪光灯
    public void onClick_FlashLight(View view){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(this,"该设备没有闪光灯",Toast.LENGTH_LONG).show();
            return;
        }

        if ((Boolean)flashLight.getTag()==false){
            openFlashlight();
        }
        else {
            clossFlashlight();
        }
    }

   
    //打开闪光灯
    protected void openFlashlight(){
        TransitionDrawable drawable = (TransitionDrawable)flashLight.getDrawable();
        drawable.startTransition(200);
        flashLight.setTag(true);
       try{
           mCamera = Camera.open();
    
           mySong= MediaPlayer.create(this, R.raw.luo);
           //播放
           mySong.start();

           int textId = 0;
           mCamera.setPreviewTexture(new SurfaceTexture(textId));
           mCamera.startPreview();

           mParameters = mCamera.getParameters();
           mParameters.setFlashMode(mParameters.FLASH_MODE_TORCH);
           mCamera.setParameters(mParameters);



       }catch (Exception e){

       }
    }
    //关闭闪光灯
    protected void clossFlashlight(){
        TransitionDrawable drawable = (TransitionDrawable)flashLight.getDrawable();
        if (((Boolean)flashLight.getTag())){
            drawable.reverseTransition(200);
            if (mCamera!=null){
                mParameters = mCamera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.stopPreview();
                mCamera.release();
                mCamera=null;
                flashLight.setTag(false);
                mySong.stop();
                mySong.reset();
                mySong.release();
            }
        }

    }

    
    //警笛
    public void onClick_JinDi(View view){
        if (i == 0){
            openMusic();
        }
        else {
            clossMusic();
        }
    }


    //打开警笛
    protected void openMusic(){
        myJinDi= MediaPlayer.create(this, R.raw.jindi);
        if (((Boolean)flashLight.getTag())){
            if (mCamera!=null){
                mySong.stop();
            }
        }
        myJinDi.start();
        i = 1;
    }
    //关闭警笛
    protected void clossMusic(){
        myJinDi.stop();
        myJinDi.reset();
        myJinDi.release();
        i = 0;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub  
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数  
        }
        return false;
    }
    /**
     * 双击退出函数 
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出  
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出  
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  

        } else {
            finish();
            System.exit(0);
        }
    }
}
