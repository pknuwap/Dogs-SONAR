package game;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import Character.*;
import savingModule.SaveManager;
import soundCapture.LevelMeter;
import soundCapture.SoundHandler;

public class Window extends JFrame{
    public static int CHARACTER_Y=400;
    public SaveManager sv;
    public LevelMeter lm;
    VoiceHandler vh;
    Dog dog;
    @Override
    public void removeAll() {
        getContentPane().removeAll();
        getContentPane().repaint();
    }
    boolean isGaming=false,isPaused=false;
    public static void main(String[] args) {
        Window win=new Window();
        if(win.sv.getItem("money").getValueInt()==-1)
            win.sv.getItem("money").setValueInt(0);
        if(win.sv.getItem("dmgUp").getValueInt()==-1)
            win.sv.getItem("dmgUp").setValueInt(0);
        if(win.sv.getItem("critChanceUp").getValueInt()==-1)
            win.sv.getItem("critChanceUp").setValueInt(0);
        if(win.sv.getItem("critDmgUp").getValueInt()==-1)
            win.sv.getItem("critDmgUp").setValueInt(0);
        if(win.sv.getItem("day").getValueInt()==-1)
            win.sv.getItem("day").setValueInt(0);
        win.sv.save();
    }
    public void setDog(Dog dog) {
        Window.this.dog=dog;
    }
    public void pause(KeyEvent e) {
        processKeyEvent(e);
    }
    public Window() {
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        sv=new SaveManager("sav.dat");
        this.add(new TitlePane(1280,720,17,this));
        vh=new VoiceHandler();
        lm=new LevelMeter(vh,0.1,12);
    }
    public void pauseSound() {
        isPaused=true;
    }
    public void resumeSound() {
        isPaused=false;
    }
    class VoiceHandler implements SoundHandler{
        @Override
        public synchronized void action(double now, double peak) {
            if(isGaming&&!isPaused) {
                dog.attack(now*0.2);               
            }
        }
    }
}
