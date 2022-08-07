package TanKGame;

import java.util.Vector;

public class MyTank extends TanK{
    Bullet b = null;
    boolean isLive = true;

    Vector<Bullet> bullets = new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }
    public void shoot(){
        switch (getDirect()){
            case 0:
                b = new Bullet(getX()+15,getY(),0);

                break;
            case 1:
                b = new Bullet(getX()+60,getY()+15,1);

                break;
            case 2:
                b = new Bullet(getX()+15,getY()+60,2);

                break;
            case 3:
                b = new Bullet(getX(),getY()+15,3);

                break;
        }
        bullets.add(b);

        new Thread(b).start();

    }
}
