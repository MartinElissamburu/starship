package edu.austral.starship.base.model;

import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Asteroid extends GameObject {

    public Asteroid(Vector2 direction, Vector2 position) {
        super(direction, position, 35, 35);
    }

    @Override
    public void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images) {
        visitor.drawAsteroid(this,graphics,images);
    }

    @Override
    public void move() {
        Random random = new Random();
        this.setShape(new Rectangle(((int) this.getPosition().getX()) + 1, ((int) this.getPosition().getY()+1), 35, 35));
        setPosition(getPosition().add(new Vector2(random.nextInt(3),random.nextInt(3))));
    }

    @Override
    public void outOfBounds() {
        if(this.getPosition().getX() > 600){
            this.setPosition(new Vector2(0, this.getPosition().getY()));
        }
        if(this.getPosition().getX() < 0){
            this.setPosition(new Vector2(600, this.getPosition().getY()));
        }
        if(this.getPosition().getY() > 600){
            this.setPosition(new Vector2(this.getPosition().getX(),0));
        }
        if(this.getPosition().getX() < 0){
            this.setPosition(new Vector2(this.getPosition().getX(),600));
        }
    }

    @Override
    public void collisionedWith(GameObject collisionable) {
        collisionable.collisionedWithAsteroid(this);
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        this.setHealth(0);
    }

    @Override
    public void collisionedWithBulletPowerUp(BulletPowerUp bulletPowerUp) {

    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {

    }

    @Override
    public void collisionedWithSpaceship(Spaceship spaceship) {
        this.setHealth(0);
    }


}
