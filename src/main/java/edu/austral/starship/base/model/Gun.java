package edu.austral.starship.base.model;

public class Gun {
    private int ammo;

    public Gun(){
        ammo = 20;
    }

    public void deleteBullet(){
        ammo -= 1;
    }

    public void addBullet(int bullets){
        ammo += bullets;
    }

    public int getAmmo(){
        return ammo;
    }

}

