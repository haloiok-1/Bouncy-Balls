import GLOOP.*;
/**
 * Beschreiben Sie hier die Klasse Empore.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Empore
{
    
    GLZylinder platte, stiel;
    GLVektor positionsVektor;
    GLVektor richtungsVektor;
    
    int plattenGröße;
    String textur;
    
    
    
    public Empore(int xPos, int yPos, int zPos, int plattenGröße, String textur){
        this.plattenGröße = plattenGröße;
        this.textur = textur;
        
        
        positionsVektor = new GLVektor(xPos, yPos, zPos);
        richtungsVektor = new GLVektor(1, 0, 1);
        
        
        platte = new GLZylinder(positionsVektor, plattenGröße, 20);
        platte.drehe(90, 0, 0);
        platte.setzeTextur(textur);
        platte.setzeSelbstleuchten(50, 50, 50);
        
        
        stiel = new GLZylinder(positionsVektor.x, positionsVektor.y - 1505, positionsVektor.z, plattenGröße / 10, 3000);
        stiel.drehe(90, 0, 0);       
        stiel.setzeSelbstleuchten(150, 150, 150);
        stiel.setzeFarbe(100, 100, 100);
    
    
    
    }
}
