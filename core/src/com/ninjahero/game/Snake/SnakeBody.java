package com.ninjahero.game.Snake;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bman on 5/7/2017.
 * This class handles the body
 *
 * How this works, the xy is for the head position.
 *
 */

public class SnakeBody {
    private int x, y;
    private Sprite part;
    private SnakeScreen.Direction direction;

    public SnakeBody(Sprite body, SnakeScreen.Direction dir){
        part = body;
//        part.setSize();
        direction = dir;
    }

    public void update(int x, int y, SnakeScreen.Direction newDir){
        direction = newDir;
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch){
        part.draw(batch);
    }
}
