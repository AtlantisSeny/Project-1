/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;


public class BoxBug extends Bug
{
    private int takes;
    private int edgelen;
    
    public BoxBug(int l)
    {
        takes = 0;
        edgelen = l;
    }
    public void act()
    {
        if (takes < edgelen && canMove())
        {
            move();
            takes++;
            return;
        }
        turn();
        turn();
        takes = 0;
    }
}
