package org.academiadecodigo.bootcamp;

public class EnemyFactory {


    public EnemyFactory(){

    }

    public Enemy newEnemy(){
        //método para definir posição
        //Method to define position
       Enemy newEnemy = new RegularEnemy(60* Background.CELLSIZE, 20* Background.CELLSIZE);
        return newEnemy;
    }

}
