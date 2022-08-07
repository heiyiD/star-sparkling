package TanKGame;

import javax.swing.*;

public class TankGame extends JFrame {
    MyPanel mp ;
    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1020,790);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
