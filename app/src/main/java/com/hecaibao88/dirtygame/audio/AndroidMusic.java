package com.hecaibao88.dirtygame.audio;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * @author WangGuoWei
 * @time 2017/12/7 11:39
 * @des ${TODO}
 * <p>
 * ┽
 * ┽                            _ooOoo_
 * ┽                           o8888888o
 * ┽                           88" . "88
 * ┽                           (| -_- |)
 * ┽                           O\  =  /O
 * ┽                        ____/`---'\____
 * ┽                      .'  \\|     |//  `.
 * ┽                     /  \\|||  :  |||//  \
 * ┽                    /  _||||| -:- |||||-  \
 * ┽                    |   | \\\  -  /// |   |
 * ┽                    | \_|  ''\---/''  |   |
 * ┽                    \  .-\__  `-`  ___/-. /
 * ┽                  ___`. .'  /--.--\  `. . __
 * ┽               ."" '<  `.___\_<|>_/___.'  >'"".
 * ┽              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * ┽              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ┽         ======`-.____`-.___\_____/___.-`____.-'======
 * ┽                            `=---='
 * ┽         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * ┽                      佛祖保佑       永无BUG
 * ┽
 * ┽
 * ┽
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetDescriptor){
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        }catch(Exception e){
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void onCompletion(MediaPlayer player) {
        // TODO Auto-generated method stub
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        if(mediaPlayer.isPlaying())
            return;
        try{
            synchronized (this){
                if(!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        mediaPlayer.stop();
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
        // TODO Auto-generated method stub
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        // TODO Auto-generated method stub
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        // TODO Auto-generated method stub
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        // TODO Auto-generated method stub
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        // TODO Auto-generated method stub
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
    }

}
