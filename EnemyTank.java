package TanKGame;

import java.util.Vector;

public class EnemyTank extends TanK implements Runnable {
    private int x;
    private int y;
    private int direct = 2;
    boolean isLive = true;
    Bullet b;
    Vector<Bullet> bullets = new Vector<>();

    public EnemyTank(int x, int y, Bullet b) {
        super(x, y);
        this.b = b;
    }

    public EnemyTank(int x, int y) {
        super(x, y);

    }


    @Override
    public void run() {
        while (true) {
            switch (getDirect()) {
                case 0:
                    b = new Bullet(getX()+15,getY(),getDirect(),10,true);
                    bullets.add(b);
                    new Thread(b).start();

                    for (int i = 0; i < 20; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    b = new Bullet(getX()+60,getY()+15,getDirect(),10,true);
                    bullets.add(b);
                    new Thread(b).start();
                    for (int i = 0; i < 20; i++) {
                        if (getX()+60<1000){
                            moveRight();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
                case 2:
                    b = new Bullet(getX()+15,getY()+60,getDirect(),10,true);
                    bullets.add(b);
                    new Thread(b).start();
                    for (int i = 0; i < 20; i++) {
                        if (getY()+60<750){
                            moveDowm();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 3:
                    b = new Bullet(getX(),getY()+15,getDirect(),10,true);
                    bullets.add(b);
                    new Thread(b).start();
                    for (int i = 0; i < 30; i++) {
                        if (getX()>0){
                            moveLeft();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
            }

            setDirect((int) (Math.random() * 4));

            if (!isLive) {
                break;
            }
        }
    }
}
