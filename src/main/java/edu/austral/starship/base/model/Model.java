package edu.austral.starship.base.model;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private List<GameObject> objects;
    private CollisionEngine collisionEngine;

    public Model(){
        this.objects = new ArrayList<>();
        this. collisionEngine = new CollisionEngine();
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public List<GameObject> getObjects(){
        return objects;
    }

    public void checkOutOfBounds(){
        for (GameObject object: objects) {
            object.outOfBounds();
        }
    }

    public void createAsteroid(){
        Random random = new Random();
        Asteroid asteroid = new Asteroid(new Vector2(random.nextInt(600), random.nextInt(600)), new Vector2(random.nextInt(600), random.nextInt(600)));
        addObject(asteroid);
    }

    public void createExtraBullets(){
        Random random = new Random();
        int amount = random.nextInt(10);
        if (amount > 5) {
            BulletPowerUp bulletPowerUp = new BulletPowerUp(15, new Vector2(random.nextInt(500), random.nextInt(500))
                    , new Vector2(random.nextInt(500), random.nextInt(500)), amount);
            addObject(bulletPowerUp);
        }
    }

    public void shootBullet(Player player, int size){
        Gun gun = player.getSpaceship().getGun();
        Bullet bullet = new Bullet(size, player.getSpaceship().getDirection(),
                new Vector2(player.getSpaceship().getPosition().getX() + 5, player.getSpaceship().getPosition().getY() -40));
        gun.deleteBullet();
        bullet.addObserver(player);
        addObject(bullet);
    }

    public void checkCollisions(){
        collisionEngine.checkCollisions(objects);
    }

    public int nextMove(int counter, float timesinceLastDraw){
        Random random = new Random();
        checkOutOfBounds();
        checkCollisions();
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getHealth()==0){
                objects.remove(objects.get(i));
            }
        }
        counter++;
        if ((int)timesinceLastDraw % 10 == 0 && counter > 300){
            createAsteroid();
            createAsteroid();
            if (random.nextBoolean()){
                createExtraBullets();
            }
            return 0;
        }
        return counter;
    }
}
