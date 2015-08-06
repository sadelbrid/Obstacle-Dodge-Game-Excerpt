package com.blinkideacompany.pop.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by dgallagher on 7/2/15.
 */
public class Part {
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_POLY = 1;
    public static final int TYPE_CUSTOM = 2;

    public int radius;
    public int type;
    public double rotation;
    public ArrayList<Point> rep;
    int x_vel, y_vel;
    Point center;

    //number of points, radius, type of part, intial x, intial y
    public Part(int numPoints, int r, int t, int init_x, int init_y) {
        rep = new ArrayList<Point>();
        int xAverage = 0, yAverage = 0;
        x_vel = y_vel = 0;
        this.radius = r;
        this.type = t;
        this.rotation = 0;
        for(int i = 0; i< numPoints; i++){
            double x_pos = Math.cos((2.0*Math.PI/numPoints)*i)*radius + init_x;
            double y_pos = Math.sin((2.0*Math.PI/numPoints)*i)*radius + init_y;
            rep.add(new Point((int)x_pos, (int)y_pos));
            xAverage += (int)x_pos;
            yAverage += (int)y_pos;
        }

        center = new Point(xAverage/numPoints, yAverage/numPoints);
    }
    public Part(ArrayList<Point> points) {
        rep = new ArrayList<Point>();
        int numPoints = points.size();
        int xAverage = 0, yAverage = 0;
        x_vel = y_vel = 0;
        this.type = TYPE_CUSTOM;
        this.rotation = 0;
        while(points.size() > 0){
            xAverage += points.get(0).x;
            yAverage += points.get(0).y;
            rep.add(points.remove(0));
        }
        center = new Point(xAverage/numPoints, yAverage/numPoints);

    }

    public boolean contains(int x, int y) {
        if(this.type != TYPE_CIRCLE) {
            int i, j, count = 0;
            for (i = 0, j = rep.size() - 1; i < rep.size(); j = i++) {
                if (((rep.get(i).y > y) != (rep.get(j).y > y)) &&
                        (x < (rep.get(j).x - rep.get(i).x) * (y - rep.get(i).y) / (rep.get(j).y - rep.get(i).y) + rep.get(i).x)) {
                    count++;
                }
            }
            //Return true if count is odd
            return count % 2 != 0;
        }
        else{
            double distance = Math.abs(Math.sqrt(Math.pow(x - rep.get(0).x, 2) + Math.pow(y - rep.get(0).y, 2)));
            return distance <= this.radius;
        }
    }

    public void update() {
        center.x += x_vel;
        center.y += y_vel;
        for(int i = 0; i < rep.size(); i++){
            rep.get(i).x += x_vel;
            rep.get(i).y += y_vel;
        }
    }

    public void draw(ShapeRenderer s) {

        float[] vertices={};



//        path.reset();
        if (rep.size() > 1 && rep.size() != 0) {
            for(int i = 1; i < rep.size(); i++){

                s.line(rep.get(i-1).x, rep.get(i-1).y,rep.get(i).x,rep.get(i).y);
            }
        }
        else if(type == TYPE_CIRCLE){

            s.circle(center.x, center.y, radius);
        }


    }

    public void translateX(int dX) {
        center.x += dX;
        for (int i = 0; i < rep.size(); i++)
            rep.get(i).x += dX;
    }

    /*
    Translation methods
     */

    public void translateY(int dY){
        center.y += dY;
        for(int i = 0; i < rep.size(); i++)
            rep.get(i).y += dY;
    }

    public void translateXY(int dX, int dY){
        for(int i = 0; i < rep.size(); i++) {
            rep.get(i).x += dX;
            rep.get(i).y += dY;
        }
        center.x += dX;
        center.y += dY;
    }

    public void translateByAngle(double angleRad, int amount){
        /*
        sin(angle) = dY/amount;
        cos(angle) = dX/amount;
         */
        int dX = (int)(Math.cos(angleRad)*amount);
        int dY = (int)(Math.sin(angleRad)*amount);
        for(int i = 0; i < rep.size(); i++) {
            rep.get(i).x += dX;
            rep.get(i).y += dY;
        }
        center.x += dX;
        center.y += dY;
    }

    public void rotate(double angleRad) {
        rotation += angleRad;
        if (rotation >= Math.PI * 4) rotation -= Math.PI * 4;
        if (type == TYPE_POLY) {
            int numPoints = rep.size();
            rep.clear();
            for (int i = 0; i < numPoints; i++) {
                double x_pos = Math.cos(((2.0 * Math.PI / numPoints) * i) + rotation) * radius + center.x;
                double y_pos = Math.sin(((2.0 * Math.PI / numPoints) * i) + rotation) * radius + center.y;
                rep.add(new Point((int) x_pos, (int) y_pos));
                //xAverage += (int)x_pos;
                //yAverage += (int)y_pos;
            }
        } else {

        }
    }
    public void changeSize(int amount) {
        radius += amount;
        int numPoints = rep.size();
        rep.clear();
        for (int i = 0; i < numPoints; i++) {
            double x_pos = Math.cos(((2.0*Math.PI/numPoints)*i) + rotation)*radius + center.x;
            double y_pos = Math.sin(((2.0*Math.PI/numPoints)*i) + rotation)*radius + center.y;
            rep.add(new Point((int)x_pos, (int)y_pos));
        }
    }

    public static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
