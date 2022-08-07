package TanKGame;

import java.awt.*;

public class Bullet implements Runnable {
    private int x;
    private int y;
    private int direct;
    private int speed = 10;
    boolean isLive = true;

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public Bullet(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;

    }

    public Bullet(int x, int y, int direct, int speed, boolean isLive) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
        this.isLive = isLive;
    }

    @Override
    public void run() {

        while (true) {

            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive = false;
                break;
            }

        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
