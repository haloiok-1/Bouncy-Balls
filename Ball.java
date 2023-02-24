import GLOOP.*;
import java.util.Random;

class Ball extends Thread{
    GLKugel kugel;
    GLVektor ortsVektor;
    GLVektor richtungsVektor;

    boolean falling;
    boolean laying;
    int mass;
    double gravitation;
    int ballsize;
    double verformung;
    double transformingSpeed = 2;
    

    public Ball(int x, int y, int z){

        super();

        ballsize = 10;
        verformung = 1.05;
        ortsVektor = new GLVektor(x, y, z);
        richtungsVektor = new  GLVektor(getRandomNumberUsingNextDouble(-transformingSpeed, transformingSpeed), 10, getRandomNumberUsingNextDouble(-transformingSpeed, transformingSpeed));

        kugel = new GLKugel(ortsVektor, ballsize);
        gravitation = 0.9;
    }
    
    
    public void run(){
        //kugel.setzeFarbe(getRandomNumberUsingNextInt(100, 255), getRandomNumberUsingNextInt(100, 255), getRandomNumberUsingNextInt(100, 255));
        
        while(true){
            ortsVektor.addiere(richtungsVektor);
            kugel.setzePosition(ortsVektor);

            if(ortsVektor.y < ballsize ){
                
                
                // wird ausgeführt, wenn Kugel über den Rand hinaus geht. -> fällt dann runter
                if(Math.sqrt(Math.pow(ortsVektor.x, 2) + Math.pow(ortsVektor.z, 2)) > 100 + ballsize/4){
                    falling = true;
                    System.out.println("Thread " + this.currentThread().getId() + " is falling!");

                    if(ortsVektor.y < -500){
                        kugel.loesche();
                        try {
                            this.interrupt();
                        } catch (RuntimeException e) {
                            System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping failed 2");
                        }

                    }
                }
                
                // wird ausgeführt, wenn die Kugel sich langsamer bewegt als 0.5 und sie den boden berührt -> stoppt alles
                else if(Math.abs(richtungsVektor.y) <= 0.5){
                    ortsVektor.y = ballsize - 0.5;
                    System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping because of low speed");
                    laying = true;
                    try {
                        this.interrupt();
                    } catch (RuntimeException e) {
                        System.out.println("Thread " + this.currentThread().getId() + ": " + "Stopping failed! 5");
                    }                   

                }
                else{
                    if(ortsVektor.y < ballsize-2){
                        System.out.println(ortsVektor.y);
                        ortsVektor.y = ballsize-0.5;
                                            }
                    
                    
                    //ermöglicht das abprallen am boden
                    richtungsVektor.y = -richtungsVektor.y;
                    richtungsVektor.y /= verformung;
                    
                    //verlangsamt x und z richtung prozentual
                    
                    
                
                }
            }

            if(!laying){addiereGravitation();}
            richtungsVektor.x /= 1.005;
            richtungsVektor.z /= 1.005;
            
            if(Thread.currentThread().isInterrupted()) {
                break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping failed");

            }

            
        }
    }

    public void addiereGravitation(){   
        if(Math.abs(richtungsVektor.y) <= 0.5 && ortsVektor.y < 5){
            gravitation *= 0.995;
            System.out.println(gravitation);
        }

        richtungsVektor.y -= gravitation;
    }

    
    public int getRandomNumberUsingNextInt(int min, int max) {
        if(max <= min) return min + 1;

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public double getRandomNumberUsingNextDouble(double min, double max){
        if(max < min) return min + 1;

        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

}