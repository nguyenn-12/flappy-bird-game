package com.example.flappybird;

import java.util.Random;

public class Pipe {
    private int pipeX, topPipeY, bottomPipeY;
    private int velocity;
    private int gap; // Khoảng cách giữa 2 ống
    private boolean isScored;

    public Pipe(int startX) {
        pipeX = startX;
        velocity = 5;
        gap = 700; // Khoảng cách giữa ống trên và ống dưới

        Random random = new Random();
        int randomY = random.nextInt(AppConstants.SCREEN_HEIGHT / 3);
        topPipeY = randomY;
        bottomPipeY = randomY + gap;
        isScored = false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean scored) {
        isScored = scored;
    }
    public int getPipeX() {
        return pipeX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX = pipeX;
    }

    public int getTopPipeY() {
        return topPipeY;
    }

    public int getBottomPipeY() {
        return bottomPipeY;
    }

    public int getVelocity() {
        return velocity;
    }

    public void updatePosition() {
        pipeX -= velocity;
        if (pipeX + AppConstants.getBitmapBank().getPipeWidth() < 0) {
            resetPipe();
        }
    }

    public void resetPipe() {
        pipeX = AppConstants.SCREEN_WIDTH+200;
        Random random = new Random();
        int minPipeHeight = 300;
        int maxPipeHeight = AppConstants.SCREEN_HEIGHT / 2;
        int randomY = minPipeHeight + random.nextInt(maxPipeHeight - minPipeHeight);
        topPipeY = randomY;
        bottomPipeY = randomY + gap;
        isScored = false;
    }
}
