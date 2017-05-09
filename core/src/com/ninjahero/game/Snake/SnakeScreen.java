package com.ninjahero.game.Snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ninjahero.game.InitGame;

/**
 * Created by Bman on 5/6/2017.
 * This is just a simple snake game following the book
 * For now just add to this project
 */



public class SnakeScreen implements Screen, GestureDetector.GestureListener {

    //CONTROLS/////////////////////////////////////////////////////////////////////
//    private Vector2 startPt = new Vector2();
//    private Vector2 endPt = new Vector2();

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
//        startPt.set(x, y);
//        Gdx.app.log("TouchDown", "Start point set");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log("Tap", "Done");
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
//        Gdx.app.log("Long press", "Should not change anything");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log("fling", "direction by velocity");
        //Is it more horizontal or more vertical
        if (Math.abs(velocityX) > Math.abs(velocityY)){
            //more horizontal
            if(velocityX > 0){
                setDirection(Direction.RIGHT);
            } else {
                setDirection(Direction.LEFT);
            }
        } else {
            //more vertical
            if (velocityY > 0){
                setDirection(Direction.DOWN);
            } else {
                setDirection(Direction.UP);
            }
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log("Pan", "direction by delta");
        if (Math.abs(deltaX) > Math.abs(deltaY)){
            //more horizontal
            if(deltaX > 0){
                setDirection(Direction.RIGHT);
            } else {
                setDirection(Direction.LEFT);
            }
        } else {
            //more vertical
            if (deltaY > 0){
                setDirection(Direction.DOWN);
            } else {
                setDirection(Direction.UP);
            }
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("Pan stop", "not sure what to do");
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.log("zoom", "Android ONLY");
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        Gdx.app.log("pinch", "do nothing, android only");
        return true;
    }

    @Override
    public void pinchStop() {
        Gdx.app.log("Pinching", "stopped");
    }

    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
// end controls///////////////////////////////////////////////////////////////////////////////////////////

    private static final float MOVE_TIME = 0.3f;
    private static final int MOVE_DIST = 32;
    private InitGame game;
    private TextureAtlas atlas;
    private Sprite head, body, tail;

    private Sprite apple;
    private boolean isAppleAvailable = false;
    private int appleX, appleY;

    private ShapeRenderer sr;
    private float width, height;
    private float timer = MOVE_TIME;
    private int headX, headY;
    private Direction move_dir;
    private GestureDetector gd;


    public SnakeScreen(InitGame game){
//        Gdx.app.log(game.TAG, "Constructor");
        this.game = game;
        sr = new ShapeRenderer();
        atlas = new TextureAtlas(Gdx.files.internal("Snake.atlas"));
        head = new Sprite(atlas.createSprite("head-up"));
        head.setSize(MOVE_DIST, MOVE_DIST);

        apple = new Sprite(atlas.createSprite("apple"));
        apple.setSize(MOVE_DIST, MOVE_DIST);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        move_dir = Direction.RIGHT;
        gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
    }


    @Override
    public void show() {
//        Gdx.app.log(game.TAG, "Show");
    }


    private void getHeadDirection(){
        switch (move_dir){
            case UP:
                head.setRegion(atlas.findRegion("head-up"));
                break;
            case DOWN:
                head.setRegion(atlas.findRegion("head-down"));
                break;
            case RIGHT:
                head.setRegion(atlas.findRegion("head-right"));
                break;
            case LEFT:
                head.setRegion(atlas.findRegion("head-left"));
                break;
        }
    }

    private void move(){
        switch (move_dir){
            case RIGHT:
                headX += MOVE_DIST;
                break;
            case LEFT:
                headX -= MOVE_DIST;
                break;
            case DOWN:
                headY -= MOVE_DIST;
                break;
            case UP:
                headY += MOVE_DIST;
                break;
        }
        checkBounds();
    }

    public void setDirection(Direction direction){
        move_dir = direction;
    }

    private void checkBounds(){
//        if the snake leaves the screen bring it to the opposite end
        switch (move_dir){
            case RIGHT:
                if(headX >= Gdx.graphics.getWidth()){
                    headX = 0;
                }
                break;
            case LEFT:
                if (headX < 0){
                    headX = Gdx.graphics.getWidth() - MOVE_DIST;
                }
                break;
            case UP:
                if (headY >= Gdx.graphics.getHeight()){
                    headY = 0;
                }
                break;
            case DOWN:
                if (headY < 0){
                    headY = Gdx.graphics.getHeight() - MOVE_DIST;
                }
                break;
        }

    }

    private void checkForNewApple(){
        if (isAppleAvailable && appleX == headX && appleY == headY){
            isAppleAvailable = false;
        }
        if (!isAppleAvailable){
            do{
                appleX = MathUtils.random(Gdx.graphics.getWidth() / MOVE_DIST - 1) * MOVE_DIST;
                appleY = MathUtils.random(Gdx.graphics.getHeight() / MOVE_DIST - 1) * MOVE_DIST;
                isAppleAvailable = true;
            } while (appleX == headX && appleY == headY);
            apple.setPosition(appleX, appleY);
        }
    }

    private void update(float dt){
        timer -= dt;
        if (timer <= 0){
            timer = MOVE_TIME;
            move();
            getHeadDirection();
            head.setPosition(headX, headY);
        }
        checkForNewApple();

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        head.draw(game.batch);
        if (isAppleAvailable)
            apple.draw(game.batch);
        game.batch.end();

        sr.setColor(Color.SKY);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int a = 0; a < width / head.getWidth(); a++){
            sr.line(a * head.getWidth(),0, a * head.getWidth(), height );
        }
        for (int b = 0; b< height / head.getHeight(); b++){
            sr.line(0, b* head.getHeight(), width, b*head.getHeight());
        }
        sr.end();
    }

    @Override
    public void resize(int width, int height) {
//        Gdx.app.log(game.TAG, "Resize Snake screen");
    }

    @Override
    public void pause() {
//        Gdx.app.log(game.TAG, "Pause Snake screen");
    }

    @Override
    public void resume() {
//        Gdx.app.log(game.TAG, "Resume Snake screen");
    }

    @Override
    public void hide() {
//        Gdx.app.log(game.TAG, "Hide Snake screen");
    }

    @Override
    public void dispose() {
//        Gdx.app.log(game.TAG, "Dispose Snake screen");
        atlas.dispose();
        sr.dispose();
    }
}


