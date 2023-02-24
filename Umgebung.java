import GLOOP.*;
import java.util.Random;

public class Umgebung extends Thread{
    Ball einBall;
    Empore hauptPilz;

    GLZylinder boden;
    GLZylinder stiel;
    GLTafel ballCounter;

    GLTastatur tastatur;
    GLNebel nebel;

    double gravitation = 9.81;
    GLLicht meinLicht;
    GLEntwicklerkamera meineKamera;

    boolean loopcutter = true;
    int ballsAmount = 0;
    public Umgebung(){
        meinLicht = new GLLicht();
        meinLicht.setzeFarbe(100, 100, 25);
        
        tastatur = new GLTastatur();
        nebel = new GLNebel();
        nebel.setzeFarbe(0, 0, 0);

        // erstelle Pilz auf dem die Kugeln hüpfen
        hauptPilz = new Empore(0, -13, 0, 250, "roterPilz.png");

        
        
        //Pilzwald erstellen
        pilzeErstellen("roterPilz.png");
        pilzeErstellen("gruenerPilz.png");
        pilzeErstellen("gelberPilz.png");
        pilzeErstellen("blauerPilz.png");
        pilzeErstellen("violetterPilz.png");
        
        ballCounter = new GLTafel(150, -25, 0, 50, 50);
        ballCounter.setzeTextur("transparent.png");    
        ballCounter.setzeText("Balls that lived: " + ballsAmount , 10);
        ballCounter.dreheDich(90, 90, 0);
        

        System.out.println("Starting Programm");
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
                ballsAmount++;
                ballCounter.setzeText("Balls that lived: " + ballsAmount , 10);
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

    public void pilzeErstellen(String textur){
        for(int i = 0; i <= 5; i++){

            Empore empore = new Empore(getRandomNumberUsingNextInt(-2000, 2000),getRandomNumberUsingNextInt(-500, -100), getRandomNumberUsingNextInt(-2000, 2000), getRandomNumberUsingNextInt(50, 200), textur);

        }

    }
}
