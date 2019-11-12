package edu.austral.starship.base.controllers;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.model.GameObject;
import edu.austral.starship.base.model.Model;
import edu.austral.starship.base.model.Player;
import edu.austral.starship.base.model.Visitor;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainController extends ObservableKeyEvent implements GameFramework {
    private HashMap<String, PImage> images;
    private Model model = new Model();
    private Visitor visitor = new Visitor();
    private int counter = 300;
    private List<Player> players = new ArrayList<>();
    private List<List<Integer>> keyActions = new ArrayList<>();

    public HashMap loadImages(ImageLoader imageLoader){
        HashMap map = new HashMap<String, PImage>();
        map.put("spaceship",imageLoader.load("spaceship.png"));
        map.put("asteroid",imageLoader.load("asteroid.png"));
        map.put("bullet",imageLoader.load("bullet.png"));
        map.put("heart",imageLoader.load("heart.png"));
        return map;
    }


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        Player player1 = new Player("Player 1", new Vector2(0,0), new Vector2(300, 500));
        Player player2 = new Player("Player 2", new Vector2(0,0), new Vector2(200, 500));
        images = loadImages(imageLoader);
        windowsSettings.setSize(600,600).enableHighPixelDensity();

        players.add(player1);
        players.add(player2);

        List<Integer> keyActionsP1 = new ArrayList<>();
        keyActionsP1.add(PConstants.UP);
        keyActionsP1.add(PConstants.DOWN);
        keyActionsP1.add(PConstants.RIGHT);
        keyActionsP1.add(PConstants.LEFT);
        keyActionsP1.add(PConstants.SHIFT);
        keyActions.add(keyActionsP1);

        List<Integer> keyActionsP2 = new ArrayList<>();
        keyActionsP2.add(java.awt.event.KeyEvent.VK_W);
        keyActionsP2.add(java.awt.event.KeyEvent.VK_S);
        keyActionsP2.add(java.awt.event.KeyEvent.VK_A);
        keyActionsP2.add(java.awt.event.KeyEvent.VK_D);
        keyActionsP2.add(java.awt.event.KeyEvent.VK_1);
        keyActions.add(keyActionsP2);

        model.createAsteroid();

        for (int i = 0; i < players.size(); i++) {
            PlayerController playerController = new PlayerController(players.get(i), model, keyActions.get(i));
            addObserver(playerController);
            model.addObject(players.get(i).getSpaceship());
        }
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        graphics.background(255,255,255);
        int xHeart = 0;
        int yHeart = 15;
        int xBullets = 65;
        int yBullets = 15;
        int xScore = 90;
        int yScore = 15;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getLifes() == 0 || players.get(i).getSpaceship().getGun().getAmmo() == 0){
                endGame(graphics);
                return;
            }else{
                for (Integer event : keySet) {
                    notifyAll(event);
                }
                players.get(i).drawLifes(graphics, images, xHeart, yHeart);
                players.get(i).drawBullets(graphics,images,xBullets,yBullets);
                players.get(i).drawScore(graphics,images,xScore,yScore);
                counter = model.nextMove(counter, timeSinceLastDraw);
                xHeart += 180;
                xBullets += 180;
                xScore += 180;
            }
        }
        List<GameObject> objects = model.getObjects();
        for (GameObject object : objects) {
            if (object.getHealth() > 0){
                object.draw(visitor,graphics,images);
                object.move();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent event) {
        notifyAllKeyPressed(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private void endGame(PGraphics graphics){
        graphics.background(0,0,0);
        for (int i = 0; i < model.getObjects().size(); i++) {
            model.getObjects().remove(i);
        }
        Player winner = players.get(0);
        for(int i = 0; i < players.size(); i++){
            if (players.get(i).getScore() > winner.getScore()){
                winner = players.get(i);
            }
        }
        graphics.fill(255,255,255);
        graphics.textSize(50);
        graphics.text("End Game", 140, 250);
        graphics.text(winner.getName() + " won", 140, 350);
    }
}
