package edu.austral.starship.base.model;

import edu.austral.starship.base.controllers.BulletObserver;
import edu.austral.starship.base.controllers.SpaceshipObserver;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

public class Player implements SpaceshipObserver, BulletObserver {
    private Spaceship spaceship;
    private int lifes;
    private String name;
    private int score;

    public Player(String name, Vector2 direction, Vector2 position){
        this.name = name;
        spaceship = new Spaceship(direction,position);
        spaceship.addObserver(this);
        lifes = 3;
        score = 0;
    }

    public Spaceship getSpaceship(){
        return spaceship;
    }

    public int getLifes(){
        return lifes;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    public void drawLifes(PGraphics graphics, HashMap<String, PImage> images, int x, int y){
        graphics.text(name,x,y);
        graphics.fill(0,0,0);
        graphics.textSize(14);
        for (int i = 0; i < this.getLifes(); i++) {
            graphics.image(images.get("heart"), x, y,15,15);
            x += 20;
        }
    }

    public void drawBullets(PGraphics graphics, HashMap<String, PImage> images, int x, int y){
        graphics.text(spaceship.getGun().getAmmo(), x, y);
        y = 20;
        graphics.image(images.get("bullet"), x, y, 15, 15);
    }

    public void drawScore(PGraphics graphics, HashMap<String, PImage> images, int x, int y){
        graphics.text("score:", x, y);
        x += 50;
        graphics.text(score, x, y);
    }

    @Override
    public void onCrash(){
        lifes -= 1;
    }

    @Override
    public void onBulletImpact(){
        score += 100;
    }

}
