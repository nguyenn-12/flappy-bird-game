package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    Bitmap background_game;

    Bitmap [] bird;
    Bitmap topPipe, bottomPipe;

    public BitmapBank(Resources resources)
    {
        background_game = BitmapFactory.decodeResource(resources, R.drawable.background_game);
        background_game = scaleImage(background_game);
        bird = new Bitmap[4];

        bird [0] = BitmapFactory.decodeResource(resources, R.drawable.bo1);
        bird [1] = BitmapFactory.decodeResource(resources, R.drawable.bo2);
        bird [2] = BitmapFactory.decodeResource(resources, R.drawable.bo3);
        bird [3] = BitmapFactory.decodeResource(resources, R.drawable.bo4);

        topPipe = BitmapFactory.decodeResource(resources, R.drawable.pipe_top);
        bottomPipe = BitmapFactory.decodeResource(resources, R.drawable.pipe_bottom);

    }

    public Bitmap getBird(int frame) {
        return bird[frame];
    }
    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight() {
        return bird[0].getHeight();
    }

    public Bitmap getTopPipe() {
        return topPipe;
    }

    public Bitmap getBottomPipe() {
        return bottomPipe;
    }

    public int getPipeWidth() {
        return topPipe.getWidth();
    }

    public int getPipeHeight() {
        return topPipe.getHeight();
    }

    public Bitmap getBackground_game(){
        return background_game;
    }

    public int getBackgroundWidth(){
        return background_game.getWidth();
    }

    public int getBackgroundHeight(){
        return background_game.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        int backgroundScaleWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;

        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
