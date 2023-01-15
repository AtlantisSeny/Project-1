package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

public class Jumper extends Actor
{
    //构造函数
    public Jumper(Color color) {
        setColor(color);
    }
    public Jumper() {

    }

    /*
     * 判断在当前位置和角度下，是否能够往前跳
     * 如果返回true，说明可以跳
     * 如果返回false，说明不能跳
     * */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location nextOne = getLocation().getAdjacentLocation(getDirection());
        Location nextTwo = nextOne.getAdjacentLocation(getDirection());
        if (!gr.isValid(nextTwo))
            return false;
        Actor targetDes = gr.get(nextTwo);
        return (targetDes == null) || (targetDes instanceof Flower);
    }

    //转换角度，每调用一次转45度角
    public void turn(){
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /*
    * 负责动作管理
    * 如果下一个位置能跳，则跳过去
    * 如果下一个位置不能挑，则转换角度
    * */
    public void act() {
        if(canJump())
            jump();
        else
            turn();
    }

    //跳跃函数，一次性挑两格
    public void jump(){
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location nextLoc = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (gr.isValid(nextLoc))
            moveTo(nextLoc);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

}