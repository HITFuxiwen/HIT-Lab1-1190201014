/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i=0; i<4; i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double ret=360.0/sides;
    	ret=180-ret;
    	return ret; 
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        double tmp=180-angle;
        double sides=360/tmp;
        int ret=(int)Math.round(sides);
        return ret;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle=calculateRegularPolygonAngle(sides);
        for(int i=1;i<=sides;i++)
        {
        	turtle.forward(sideLength);
    		turtle.turn(180-angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	int dy=targetY-currentY;
    	int dx=targetX-currentX;
    	double pi=Math.acos(-1);
    	double angle=Math.atan2(dy,dx)*180/pi;
    	double ret=90.0-angle-currentBearing;
    	if(ret<0) ret+=360.0;
        return ret;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> ret=new ArrayList<>();
        double ang=0;
        int x=xCoords.get(0);
    	int y=yCoords.get(0);
        for(int i=1;i<xCoords.size();i++)
        {
        	int xx=xCoords.get(i);
        	int yy=yCoords.get(i);
        	ang=calculateBearingToPoint(ang,x,y,xx,yy);
        	ret.add(ang);
        	x=xx; y=yy;
        }
        return ret;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        if(points.size()<=3) return points;
        double xx=100000000.0; double yy=100000000.0;
        LinkedHashSet<Point> ret= new LinkedHashSet<Point>();
        boolean[] lis=new boolean[10000];
        int temp=0; int as=0;
       for(Point p : points)
       {
    	   temp++;
    	   double x=p.x();
    	   double y=p.y();
    	   if(xx>x || (xx==x && yy>y))
    	   {
    		   xx=x; yy=y;
    		   as=temp;
    	   }		   
       }
       lis[as]=true;
       Point a=new Point(xx, yy);
       Point b=a;
       double nowang=0.0;
       for(int i=0;i<points.size();i++)
       {
    	   double angle=360.0;
    	   int nowx=(int)a.x();
    	   int nowy=(int)a.y();
    	   int ansx=0; int ansy=0;
    	   int tmp=0; temp=0;
	       for(Point p : points)
	       {
	    	   tmp++;
	    	   int x=(int)p.x();
	    	   int y=(int) p.y();
	    	   if(x==nowx && y==nowy) continue;
	    	   double ang=calculateBearingToPoint(nowang,nowx,nowy,x,y);
	    	   if(angle>ang)
	    	   {
	    		   	angle=ang;
	    		   	ansx=x;
	    		   	ansy=y;
	    		    temp=tmp;
	    	   }
	       }
	       lis[temp]=true;
	       nowang+=angle;
	       a=new Point(ansx,ansy);
	       int x=(int)b.x();
	       int y=(int)b.y();

	       if(x==ansx && y==ansy) break;
			
       }
       temp=0;
       for(Point p : points)
       {
    	 temp++;
    	 if(lis[temp]==true) ret.add(p);
       }
        return ret; 
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        drawSquare(turtle, 40);
        for(int i=1;i<=100;i++)
        drawRegularPolygon(turtle,i,i*5);
        turtle.turn(180);
        for(int i=1;i<=100;i++)
            drawRegularPolygon(turtle,i,i*5);
        turtle.draw();
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

       //drawSquare(turtle, 40);
       drawPersonalArt(turtle);
        turtle.draw();
    }

}
