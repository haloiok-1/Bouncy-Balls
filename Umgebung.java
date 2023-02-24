import GLOOP.*;
import java.util.Random;

public class Umgebung extends Thread{
    Ball einBall;

    GLZylinder boden;
    GLTastatur tastatur;
    GLNebel nebel;

    double gravitation = 9.81;
    GLLicht meinLicht;
    GLEntwicklerkamera meineKamera;

    boolean loopcutter = true;
    public Umgebung(){
        meinLicht = new GLLicht();
        nebel = new GLNebel();
        nebel.setzeFarbe(0, 0, 0);

        boden = new GLZylinder(0, -6, 0, 100, 10);
        boden.drehe(90, 0, 0);

        tastatur = new GLTastatur();

        System.out.println("start loop");
        meineKamera = new GLEntwicklerkamera();
        meineKamera.setzePosition(500, 500, 500);
        meineKamera.setzeBlickpunkt(0, -5, 0);
        run();

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

    public void run(){
        while(true){  
            if(loopcutter){
                Ball kugel = new Ball(getRandomNumberUsingNextInt(-10, 10), 300 , getRandomNumberUsingNextInt(-10, 10));
                kugel.start();
            }
            
            if(tastatur.enter()){
                if(loopcutter){
                    loopcutter = false;
                }
                else{
                    loopcutter = true;
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping failed");

            }

        }
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        if(max <= min) return min + 1;

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
