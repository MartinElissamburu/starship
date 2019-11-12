package edu.austral.starship.base.model;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

public interface VisitorInterface {
    void drawSpaceship(Spaceship spaceship, PGraphics graphics, HashMap<String, PImage> images);
    void drawBullet(Bullet bullet, PGraphics graphics, HashMap<String, PImage> images);
    void drawAsteroid(Asteroid asteroid, PGraphics graphics, HashMap<String, PImage> images);
    void drawBulletPowerUp(BulletPowerUp bulletPowerUp, PGraphics graphics, HashMap<String, PImage> images);
}
