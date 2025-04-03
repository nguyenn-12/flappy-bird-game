package com.example.flappybird;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class AppConstants {

    public static GameThread gameThread; // Thêm biến này để lưu GameThread

    public static void setGameThread(GameThread thread) {
        gameThread = thread;
    }

    public static GameThread getGameThread() {
        return gameThread;
    }
    static BitmapBank bitmapBank;

    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;

    static int VELOCITY_WHEN_JUMP;
    static GameEngine gameEngine;

    public static void initialization(Context context) {
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        gameEngine = new GameEngine();

        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMP = -40;
    }

    public  static BitmapBank getBitmapBank(){
        return bitmapBank;
    }
    public static GameEngine getGameEngine(){
        return  gameEngine;
    }
    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        AppConstants.SCREEN_WIDTH= width;
        AppConstants.SCREEN_HEIGHT= height;
    }
}
