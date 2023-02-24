import GLOOP.*;
import java.util.Random;

class Ball extends Thread{
    GLKugel kugel;
    GLVektor ortsVektor;
    GLVektor richtungsVektor;
    int mass;
    double gravitation;
    int ballsize;
    double verformung;
    double transformingSpeed = 1;

    public Ball(int x, int y, int z){

        super();
        
        ballsize = 10;
        verformung = 1;
        ortsVektor = new GLVektor(x, y, z);
        richtungsVektor = new  GLVektor(getRandomNumberUsingNextDouble(-transformingSpeed, transformingSpeed), -1, getRandomNumberUsingNextDouble(-transformingSpeed, transformingSpeed));
        mass = 1;

        kugel = new GLKugel(ortsVektor, ballsize);

        gravitation = 0.5;

    }

    public void run(){
        while(true){
            ortsVektor.addiere(richtungsVektor);
            kugel.setzePosition(ortsVektor);

            if(ortsVektor.y < ballsize){

                if(Math.sqrt(Math.pow(ortsVektor.x, 2) + Math.pow(ortsVektor.z, 2)) > 100 + ballsize/2){
                    System.out.println("falling");

                    if(ortsVektor.y < -700){
                        kugel.loesche();
                        try {
                            this.interrupt();
                        } catch (RuntimeException e) {
                            System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping");
                        }

                    }
                }
                else if(Math.abs(richtungsVektor.y) < 5){
                    ortsVektor.y = ballsize;
                    System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping");
                    try {
                        this.interrupt();
                    } catch (RuntimeException e) {
                        System.out.println("Thread " + this.currentThread().getId() + ": " + "Stopping failed!");
                    }                   

                }
                else{
                    richtungsVektor.y = -richtungsVektor.y;
                    //richtungsVektor.y /= verformung;

                    richtungsVektor.x /= 1.2;
                    richtungsVektor.z /= 1.2;
                    
                    
                }
            }

            addiereGravitation();
            
            if(Thread.currentThread().isInterrupted()) {
                break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.currentThread().getId() + ": " +"Stopping");

            }

            System.out.println("Thread " + this.currentThread().getId() + ": " + "current y-Speed" + richtungsVektor.y);
        }
    }

    public void addiereGravitation(){
        richtungsVektor.y -= gravitation;
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        if(max <= min) return min + 1;

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public double getRandomNumberUsingNextDouble(double min, double max){
        if(max <= min) return min + 1;

        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

}