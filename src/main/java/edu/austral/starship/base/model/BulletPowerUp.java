package edu.austral.starship.base.model;

import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;

public class BulletPowerUp extends Bullet{
    private int ammo;

    protected BulletPowerUp(int size, Vector2 direction, Vector2 position, int ammo){
        super(size, direction, position);
        this.ammo = ammo;
    }

    public int getAmmo(){
        return ammo;
    }

    @Override
    public void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images) {
        this.setShape(new Rectangle(((int) this.getPosition().getX())+1,((int) this.getPosition().getY())+1, getSize(), getSize()));
        visitor.drawBullet(this,graphics,images);
    }

    @Override
    public void move() {
    }

    @Override
    public void collisionedWith(GameObject collisionable) {
        collisionable.collisionedWithBulletPowerUp(this);
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        this.setHealth(0);
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        this.setHealth(0);
    }

    @Override
    public void collisionedWithSpaceship(Spaceship spaceship) {
        this.setHealth(0);
    }
}
