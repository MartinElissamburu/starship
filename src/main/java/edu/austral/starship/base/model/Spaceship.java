package edu.austral.starship.base.model;

import edu.austral.starship.base.controllers.ObservableSpaceship;
import edu.austral.starship.base.controllers.SpaceshipObserver;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spaceship extends GameObject implements ObservableSpaceship {
    private Gun gun;
    private List<SpaceshipObserver> observers;

    public Spaceship(Vector2 direction, Vector2 position){
        super(direction,position,40,40);
        this.gun = new Gun();
        observers = new ArrayList<>();
    }


    @Override
    public void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images) {
        this.setShape(new Rectangle(((int)this.getPosition().getX())+1, ((int)this.getPosition().getY())+1, 40, 40));
        visitor.drawSpaceship(this,graphics,images);
    }

    public void moveUp(){
        setPosition(new Vector2(getPosition().getX(), getPosition().getY()-2));
    }

    public void moveDown(){
        setPosition(new Vector2(getPosition().getX(), getPosition().getY()+2));
    }

    public void moveRight(){
        setPosition(new Vector2(getPosition().getX()-2, getPosition().getY()));
    }

    public void moveLeft(){
        setPosition(new Vector2(getPosition().getX()+2, getPosition().getY()));
    }

    public void addObserver(SpaceshipObserver spaceshipObserver){
        observers.add(spaceshipObserver);
    }

    @Override
    public void move() {

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
        collisionable.collisionedWithSpaceship(this);
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {

    }

    @Override
    public void collisionedWithBulletPowerUp(BulletPowerUp bulletPowerUp) {
        this.getGun().addBullet(bulletPowerUp.getAmmo());
    }


    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        notifyCrash();
    }

    @Override
    public void collisionedWithSpaceship(Spaceship spaceship) {

    }

    public void notifyCrash() {
        for(SpaceshipObserver spaceshipObserver: observers){
            spaceshipObserver.onCrash();
        }
        gun.addBullet(10);
    }

    public Gun getGun(){
        return gun;
    }
}
