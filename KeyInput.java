import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter{
    
    // public KeyInput(){}

    public String keypressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_UP){
            return "UP";
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            return "DOWN";
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            return "LEFT";
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            return "RIGHT";
        }
        return "NONE";
    }
    // public void keyreleased(KeyEvent e){
        
    // }
}