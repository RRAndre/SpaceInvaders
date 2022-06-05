package org.academiadecodigo.bootcamp;

public class SpaceImpactGame {

    private Spaceship spaceship;
    private Background background;


    public SpaceImpactGame(){
        background = new Background();
        spaceship = new Spaceship();
        spaceship.init();
    }

    public void startGame(){

        while(true){
            spaceship.shoot();

           /* try {
                spaceship.shoot();
                Thread.sleep(200);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
    }
}
