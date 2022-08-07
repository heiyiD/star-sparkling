package TanKGame;

public class TanK {
    private int x;
    private int y;
    private int direct = 0;
    private int speed = 3;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上下左右移动
    public void moveUp(){
        y-=speed;
    }
    public void moveDowm(){
        y+=speed;
    }

    public void moveLeft(){
        x-=speed;
    }
    public void moveRight(){
        x+=speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public TanK(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public TanK(int x, int y) {
        this.x = x;
        this.y = y;
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
