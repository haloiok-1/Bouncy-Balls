import GLOOP.*;
import java.util.Random;

public class Umgebung{
    Ball einBall;

    GLZylinder boden;
    GLTastatur tastatur;

    double gravitation = 9.81;
    GLLicht meinLicht;
    GLEntwicklerkamera meineKamera;

    boolean loopcutter = true;

    public Umgebung(){
        meinLicht = new GLLicht();
        meineKamera = new GLEntwicklerkamera();
        boden = new GLZylinder(0, -8, 0, 100, 10);
        boden.drehe(90, 0, 0);

        tastatur = new GLTastatur();

        System.out.println("start loop");

        
        for(int i = 0; i < 10; i++){
            Ball kugel = new Ball(getRandomNumberUsingNextInt(-80, 80), 100 , getRandomNumberUsingNextInt(-80, 80));
            kugel.start();

        }

        /* while(loopcutter){
        if(tastatur.oben()){

        //Ball kugel = new Ball(getRandomNumberUsingNextInt(-80, 80),100 , getRandomNumberUsingNextInt(-80, 80));
        //kugel.start();

        }   

        if(tastatur.backspace()){
        loopcutter = false;
        System.out.println("stop creating");

        }

        }*/
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        if(max <= min) return min + 1;

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}

