package com.Sergio.Galera;

import java.util.Scanner;
import java.lang.*;
import java.lang.Character;
import java.lang.String;
import java.lang.Math;

class Point {
    private double x;
    private double y;

    public void setCoordinates(double cord_x, double cord_y) {
        x = cord_x;
        y = cord_y;
    }
    public double getCoordinateX() {
        return x;
    }
    public double getCoordinateY() {
        return y;
    }
}
class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;
    String type;
    private double legA;
    private double legB;
    private double hypo;
    public void getCorners(Point[] p) {
        p1 = p[0];
        p2 = p[1];
        p3 = p[2];
    }
    public boolean checkIfTriangle() {
        if ((legA == 0) || (legB == 0) || (hypo == 0)) {
            System.out.println("Length of 0 does not exist.\n");
            return false;
        }
        else if ((legA + legB) < hypo) {
            System.out.println("Invalid triangle. Two sides of the triangle are not strictly greater than the third side.\n");
            return false;
        }
        else {
            return true;
        }
    }
    public void convert() {
        double x1, y1, x2, y2, x3, y3, tmp;
        x1 = p1.getCoordinateX();
        y1 = p1.getCoordinateY();
        x2 = p2.getCoordinateX();
        y2 = p2.getCoordinateY();
        x3 = p3.getCoordinateX();
        y3 = p3.getCoordinateY();

        legA = Math.sqrt(((x2 - x1)*(x2 - x1)) + ((y2 - y1)*(y2 - y1)));
        legB = Math.sqrt(((x3 - x2)*(x3 - x2)) + ((y3 - y2)*(y3 - y2)));
        hypo = Math.sqrt(((x3 - x1)*(x3 - x1)) + ((y3 - y1)*(y3 - y1)));
        if((legA > legB) && (legA > hypo)) {
            tmp = hypo;
            hypo = legA;
            legA = tmp;
        }
        else if((legB > legA) && (legB > hypo)) {
            tmp = hypo;
            hypo = legB;
            legB = tmp;
        }
    }
    public void getSides(double[] arr) {
        legA = arr[0];
        legB = arr[1];
        hypo = arr[2];
        double tmp;
        if((legA > legB) && (legA > hypo)) {
            tmp = hypo;
            hypo = legA;
            legA = tmp;
        }
        else if((legB > legA) && (legB > hypo)) {
            tmp = hypo;
            hypo = legB;
            legB = tmp;
        }
    }
    public double getArea() {
        double semi_p = (legA+legB+hypo)/2;
        double x = semi_p*(semi_p-legA)*(semi_p-legB)*(semi_p-hypo);
        double area = Math.sqrt(x);
        return area;
    }
    public String getType() {
        return type;
    }
    public void setType() {
        if((legA == legB) && (legA == hypo)){
            type = "This is an equilateral triangle.\n";
        }
        else if (((legA*legA) + (legB*legB)) == (hypo*hypo)) {
            if((legA == legB) || (legB == hypo) || (legA == hypo)) {
                type = "This is a right isosceles triangle.\n";
            }
            else {
                type = "This is a right scalene triangle.\n";
            }
        }
        else if((legA == legB) || (legB == hypo) || (legA == hypo)) {
            type = "This is an isosceles triangle.\n";
        }
        else {
            type = "This is a scalene triangle.\n";
        }
    }
    public void printSides() {
        System.out.println("Length of sides for the triangle: " +legA+ " " +legB+ " " +hypo);
    }
    public void info() {
        //boolean mes = checkIfTriangle();
        //if(mes == true) {
            printSides();
            double area = getArea();
            System.out.println(area);
            System.out.println(getType());
        //}
    }
}

public class Main {
    public static void main(String[] args) {
        int side;
        int choice;
        boolean error = false;
        Triangle tri = new Triangle();
        Point corner = new Point();
        String s, tmpS;
        char tmpChar;
        int counter = 0;
        double[] arr = new double[10];
        Point[] points = new Point[10];
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter '1' to input the corners of your triangle.");
            System.out.println("Enter '2' to input the sides of your triangle.");
            choice = input.nextInt();
            input.nextLine();
            if(choice == 1) {
                System.out.println("Enter the coordinates for each corner of your triangle. Leave a space between each number.");
                for(int d = 0; d < 3; d++) {
                    System.out.print("Enter the x-coordinate and y-coordinate of your corner:");
                    if (input.hasNextInt() || input.hasNextDouble()) {
                        tmpS = input.nextLine();
                        String[] tmpA2 = tmpS.split(" ");
                        for (int q = 0; q < tmpA2.length; q++) {
                            tmpChar = tmpA2[q].charAt(0);
                            if (Character.isDigit(tmpChar)) {
                                if (tmpA2[q].indexOf('.') >= 0) {
                                    arr[q] = Double.parseDouble(tmpA2[q]);
                                } else {
                                    arr[q] = Integer.parseInt(tmpA2[q]);
                                }
                            }
                            else {
                                error = true;
                                System.out.println("Invalid input. Characters cannot be used for coordinates of a point. Resetting.\n");
                                break;
                            }
                        }
                        if(error == true) {
                            break;
                        }
                        corner.setCoordinates(arr[0], arr[1]);
                        points[d] = corner;
                        corner = new Point();
                        counter++;
                    }
                    else {
                        s = input.nextLine();
                        System.out.println("Wrong input. Resetting.\n");
                        break;
                    }
                    arr = new double[10];
                }
                if(counter == 3) {
                    tri.getCorners(points);
                    tri.convert();
                    tri.setType();
                    tri.info();
                }
            }
            else if(choice == 2) {
                System.out.println("Enter three numbers for the sides of your triangle. Enter a space between each number.");
                System.out.println("Type 'exit' to exit program.");
                System.out.print("Numbers:");
                if (input.hasNextInt() || input.hasNextDouble()) {
                    tmpS = input.nextLine();
                    String[] tmpA = tmpS.split(" ");
                    for (int a = 0; a < tmpA.length; a++) {
                        tmpChar = tmpA[a].charAt(0);
                        if (Character.isDigit(tmpChar)) {
                            if (tmpA[a].indexOf('.') >= 0) {
                                arr[a] = Double.parseDouble(tmpA[a]);
                                counter++;
                            } else {
                                arr[a] = Integer.parseInt(tmpA[a]);
                                counter++;
                            }
                        }
                        else {
                            System.out.println("Invalid input. Characters cannot be used for length of a side.\n");
                            break;
                        }
                    }
                }
                else {
                    s = input.nextLine();
                    if (s.equals("exit")) {
                        System.out.println("Exiting program.");
                        break;
                    } else {
                        System.out.println("Wrong input.\n");
                        counter = 0;
                        arr = new double[10];
                        continue;
                    }
                }
                if (counter > 3) {
                    System.out.println("Invalid triangle. Too many sides.\n");
                }
                else if (counter < 3) {
                    System.out.println("Invalid triangle. Not enough sides.\n");
                }
                else {
                    tri.getSides(arr);
                    tri.setType();
                    tri.info();
                }
            }
            else {
                System.out.println("Not a choice.");
            }
            counter = 0;
            error = false;
            points = new Point[10];
            arr = new double[10];
            corner = new Point();
            tri = new Triangle();
        }
    }
}
