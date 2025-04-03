package com.example.flappybird;

import android.bluetooth.le.ScanSettings;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

public class GameEngine {
    BackgroundImage backgroundImage;

    Bird bird;
    private Pipe[] pipes;

    private int score = 0;
    private int lastScoredPipeX = -1;


    private long lastPipeTime = System.currentTimeMillis(); // Lưu thời điểm xuất hiện cột cuối cùng
    private int pipeSpawnInterval = 4500; // Thời gian giữa các cột (2.5 giây)


    static int gameState;
    public GameEngine () {
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        pipes = new Pipe[2];
        pipes[0] = new Pipe(AppConstants.SCREEN_WIDTH + 50);
        pipes[1] = new Pipe(AppConstants.SCREEN_WIDTH + 800);

        //0 = not started
        //1 = playing
        //2 = gameover
        gameState = 0;
    }
    public int getScore() {
        return score;
    }

    public void updateAndDrawableBackgroundImage(Canvas canvas){
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() <- AppConstants.getBitmapBank().getBackgroundWidth()){
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX(), backgroundImage.getY(),null);
        if (backgroundImage.getX() <- (AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX()+AppConstants.getBitmapBank().getBackgroundWidth(),backgroundImage.getY(),null);
        }
    }

    public void updateAndDrawBird(Canvas canvas){

        if (gameState == 1){
            if (bird.getY() <(AppConstants.SCREEN_HEIGHT -AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() <0) {
                bird.setVelocity((bird.getVelocity() + AppConstants.gravity));
                bird.setY(bird.getY() +bird.getVelocity());
            }
        }

        if (checkCollision()) {
            gameState = 2; // Game over
            AppConstants.getGameThread().setRunning(false);
        }

        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(),bird.getY(), null);
        currentFrame++;
        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Score: " + score, AppConstants.SCREEN_WIDTH / 2, 150, paint);
    }

    public void updateAndDrawPipes(Canvas canvas) {
        /*long currentTime = System.currentTimeMillis();
        if (currentTime - lastPipeTime > pipeSpawnInterval) {
            lastPipeTime = currentTime;
            pipes[0] = pipes[1]; // Đẩy cột cũ xuống
            pipes[1] = new Pipe(AppConstants.SCREEN_WIDTH + 400); // Thêm cột mới
        }*/
        for (Pipe pipe : pipes) {
            pipe.updatePosition();
            canvas.drawBitmap(AppConstants.getBitmapBank().getTopPipe(), pipe.getPipeX(), pipe.getTopPipeY() - AppConstants.getBitmapBank().getPipeHeight(), null);
            canvas.drawBitmap(AppConstants.getBitmapBank().getBottomPipe(), pipe.getPipeX(), pipe.getBottomPipeY(), null);
            if (pipe.getPipeX() + AppConstants.getBitmapBank().getPipeWidth() < bird.getX() && !pipe.isScored()) {
                score++;  // Cộng điểm
                pipe.setScored(true); // Đánh dấu ống này đã được tính điểm
            }

        }
        if (pipes[0].getPipeX() + AppConstants.getBitmapBank().getPipeWidth() < 0) {
            pipes[0] = pipes[1]; // Đẩy ống thứ hai lên
            pipes[1] = new Pipe(AppConstants.SCREEN_WIDTH + 700); // Tạo ống mới
        }
    }

    public boolean checkCollision() {
        int birdRight = bird.getX() + AppConstants.getBitmapBank().getBirdWidth();
        int birdBottom = bird.getY() + AppConstants.getBitmapBank().getBirdHeight();
/*
        for (Pipe pipe : pipes) {
            if (AppConstants.getGameEngine().bird.getX() + AppConstants.getBitmapBank().getBirdWidth() > pipe.getPipeX() &&
                    AppConstants.getGameEngine().bird.getX() < pipe.getPipeX() + AppConstants.getBitmapBank().getPipeWidth()) {

                if (AppConstants.getGameEngine().bird.getY() < pipe.getTopPipeY() ||
                        AppConstants.getGameEngine().bird.getY() + AppConstants.getBitmapBank().getBirdHeight() > pipe.getBottomPipeY()) {
                    return true;
                }
            }
        }
        return false;*/
        for (Pipe pipe : pipes) {
            int pipeRight = pipe.getPipeX() + AppConstants.getBitmapBank().getPipeWidth();

            if (birdRight > pipe.getPipeX() && bird.getX() < pipeRight) {
                if (bird.getY() < pipe.getTopPipeY() || birdBottom > pipe.getBottomPipeY()) {
                    return true;
                }
            }
        }
        return false;
    }




}
