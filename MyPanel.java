package TanKGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank mtk = null;
    Bullet b = null;
    EnemyTank etk = null;

    int enemyTankSize = 3;
    Vector<EnemyTank> enemyTanks = new Vector<>();

    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;


    //画板构造器
    public MyPanel() {

        mtk = new MyTank(100, 600);//初始化自己的坦克对象
        mtk.setSpeed(3);


        for (int i = 0; i < enemyTankSize; i++) {//初始化敌人的坦克对象

            EnemyTank etk = new EnemyTank(100 * (i + 1), 0);
            etk.setDirect(2);
            new Thread(etk).start();//启动敌人坦克自动移动的线程
//            b = etk.b;
//            Bullet b = new Bullet(etk.getX() + 15, etk.getY() + 60, etk.getDirect(), 10, true);
//            etk.bullets.add(b);
//            new Thread(b).start();
            //敌人子弹线程
            enemyTanks.add(etk);
        }

        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/6.jpeg"));

        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/5.jpeg"));

        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/2.jpg"));

        image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/42.gif"));

    }


    //创建画笔，画出坦克等
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        g.drawImage(image4, 0, 0, 1000, 750, this);


        //画自己的坦克
        if (mtk.isLive) {
            drawTank(mtk.getX(), mtk.getY(), g, mtk.getDirect(), 0);
        }
        //画敌人的坦克
        for (int j = 0; j < enemyTanks.size(); j++) {
            EnemyTank etk = enemyTanks.get(j);

            if (etk.isLive) {

                drawTank(etk.getX(), etk.getY(), g, etk.getDirect(), 1);
                //画敌人的子弹
                for (int i = 0; i < etk.bullets.size(); i++) {
                    Bullet b = etk.bullets.get(i);
                    if (b.isLive) {
                        g.fillOval(b.getX(), b.getY(), 10, 10);
                    } else
                        etk.bullets.remove(b);
                }
            } else {
                enemyTanks.remove(j);
            }
        }
        for (int i = 0; i < mtk.bullets.size(); i++) {
            b = mtk.bullets.get(i);
            if (b != null && b.isLive) {
                drawBullet(b.getX(), b.getY(), g, 0);
//            g.fillOval(mtk.b.getX(),mtk.b.getY(),10,10);
            }else {
                mtk.bullets.remove(b);
            }
        }
//        if (mtk.b != null && mtk.b.isLive) {
//            drawBullet(mtk.b.getX(), mtk.b.getY(), g, 0);
////            g.fillOval(mtk.b.getX(),mtk.b.getY(),10,10);
//        }

        for (int i = 0; i < bombs.size(); i++) {

            Bomb bomb = bombs.get(i);

            if (bomb.life > 3) {
                //解决第一次击中坦克不爆炸，等待图片加载完再画
                Image img = image1;
                MediaTracker t = new MediaTracker(this);
                t.addImage(img, 0); //img就是你要显示的图片
                try {
                    t.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);


            } else {
                Image img = image2;
                MediaTracker t = new MediaTracker(this);
                t.addImage(img, 0); //img就是你要显示的图片
                try {
                    t.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }

            bomb.lifeDown();

            if (bomb.life == 0) {
                bombs.remove(bomb);

            }

        }


//        drawBullet(mtk.b.getX(),mtk.b.getY(),g,0);
    }


    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.MAGENTA);
                break;
        }

        switch (direct) {
            case 0://上
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//身体
                g.fillOval(x + 10, y + 20, 20, 20);//圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//炮筒
                break;
            case 1://右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2://下
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//身体
                g.fillOval(x + 10, y + 20, 20, 20);//圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case 3://左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;


        }
    }

    public void drawBullet(int x, int y, Graphics g, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.RED);
                break;
            case 1:

                g.setColor(Color.white);
        }

        g.fillOval(x, y, 10, 10);

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//40 KeyEvent.VK_DOWN 方向下键
            mtk.setDirect(2);
//            mtk.setY(mtk.getY()+3);
            if (mtk.getY() + 60 < 750) {
                mtk.moveDowm();
            }
        } else if (e.getKeyCode() == 37) {//左方向键
            mtk.setDirect(3);
//            mtk.setX(mtk.getX()-3);
            if (mtk.getX() > 0) {
                mtk.moveLeft();
            }

        } else if (e.getKeyCode() == 38) {//上方向键
            mtk.setDirect(0);
//            mtk.setY(mtk.getY()-3);
            if (mtk.getY() > 0) {
                mtk.moveUp();
            }

        } else if (e.getKeyCode() == 39) {//右方向键
            mtk.setDirect(1);
//            mtk.setX(mtk.getX()+3);
            if (mtk.getX() + 60 < 1000) {
                mtk.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
            mtk.shoot();
        }
        this.repaint();//重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void hitEnemy(){


        for (int j = 0;j<mtk.bullets.size();j++){
            b = mtk.bullets.get(j);
            if (b != null && b.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank etk = enemyTanks.get(i);
                    shootEnemyTank(b, etk);
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemy();

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank etk = enemyTanks.get(i);
                if (etk.b != null && etk.b.isLive) {
                    shootMyTank(etk.b, mtk);
                }
            }


            this.repaint();
        }

    }

    public void shootEnemyTank(Bullet b, EnemyTank etk) {
        switch (etk.getDirect()) {
            case 0:
            case 2:
                if (b.getX() > etk.getX() && b.getX() < etk.getX() + 40 && b.getY() > etk.getY() && b.getY() < etk.getY() + 60) {
                    b.isLive = false;
                    etk.isLive = false;
                    Bomb bomb = new Bomb(etk.getX(), etk.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:
            case 3:
                if (b.getX() > etk.getX() && b.getX() < etk.getX() + 60 && b.getY() > etk.getY() && b.getY() < etk.getY() + 40) {
                    b.isLive = false;
                    etk.isLive = false;
                    Bomb bomb = new Bomb(etk.getX(), etk.getY());
                    bombs.add(bomb);

                }
                break;
        }
    }

    public void shootMyTank(Bullet b, MyTank mtk) {
        switch (mtk.getDirect()) {
            case 0:
            case 2:
                if (b.getX() > mtk.getX() && b.getX() < mtk.getX() + 40 && b.getY() > mtk.getY() && b.getY() < mtk.getY() + 60) {
                    b.isLive = false;
                    mtk.isLive = false;
                    Bomb bomb = new Bomb(mtk.getX(), mtk.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:
            case 3:
                if (b.getX() > mtk.getX() && b.getX() < mtk.getX() + 60 && b.getY() > mtk.getY() && b.getY() < mtk.getY() + 40) {
                    b.isLive = false;
                    mtk.isLive = false;
                    Bomb bomb = new Bomb(mtk.getX(), mtk.getY());
                    bombs.add(bomb);

                }
                break;
        }

    }
}

