package edu.austral.starship.base.controllers;

import edu.austral.starship.base.model.Model;
import edu.austral.starship.base.model.Player;

import java.util.List;

public class PlayerController implements KeyEventObserver {
    private Player player;
    private Model model;
    private List<Integer> keyActions;

    public PlayerController(Player player, Model model, List<Integer> keyActions) {
        this.player = player;
        this.model = model;
        this.keyActions = keyActions;
    }

    @Override
    public void onKeyEvent(int keyCode) {
        if (keyCode == keyActions.get(0)){
            player.getSpaceship().moveUp();
        }else if(keyCode == keyActions.get(1)){
            player.getSpaceship().moveDown();
        }else if(keyCode == keyActions.get(2)){
            player.getSpaceship().moveLeft();
        }else if(keyCode == keyActions.get(3)){
            player.getSpaceship().moveRight();
        }
    }

    @Override
    public void onKeyEventKeyPressed(int keyCode) {
        if (keyCode == keyActions.get(4)){
            model.shootBullet(player, 25);
        }
    }
}
