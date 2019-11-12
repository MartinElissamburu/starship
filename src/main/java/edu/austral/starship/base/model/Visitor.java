package edu.austral.starship.base.model;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

public class Visitor implements VisitorInterface{

    public void drawSpaceship(Spaceship spaceship, PGraphics graphics, HashMap<String, PImage> images) {
        graphics.image(images.get("spaceship"), spaceship.getPosition().getX(), spaceship.getPosition().getY(), 40, 40);
    }

    public void drawBullet(Bullet bullet, PGraphics graphics, HashMap<String, PImage> images) {
        graphics.image(images.get("bullet"), bullet.getPosition().getX(), bullet.getPosition().getY(), bullet.getSize(), bullet.getSize());
    }

    public void drawAsteroid(Asteroid asteroid, PGraphics graphics, HashMap<String, PImage> images) {
        graphics.image(images.get("asteroid"), asteroid.getPosition().getX(), asteroid.getPosition().getY(), 35, 35);
    }

    public void drawBulletPowerUp(BulletPowerUp bulletPowerUp, PGraphics graphics, HashMap<String, PImage> images){
        graphics.text(bulletPowerUp.getAmmo(), bulletPowerUp.getPosition().getX(), bulletPowerUp.getPosition().getY());
        graphics.image(images.get("bullet"), bulletPowerUp.getPosition().getX(), bulletPowerUp.getPosition().getY(), bulletPowerUp.getSize(), bulletPowerUp.getSize());
    }

}
