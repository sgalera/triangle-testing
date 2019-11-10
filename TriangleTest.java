package com.Sergio.Galera;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TriangleTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testing()
    {
        Point points[] = new Point[3];
        Triangle tri = new Triangle();
        Triangle tri2 = new Triangle();
        Triangle tri3 = new Triangle();
        points[0].setCoordinates(0, 0);
        points[1].setCoordinates(2,4);
        points[2].setCoordinates(4,0);
        tri.getCorners(points);
        tri.convert();
        tri.setType();
        assertEquals(true, tri.checkIfTriangle());
        assertEquals("This is an equilateral triangle.\n", tri.getType());
        points[0].setCoordinates(2, 2);
        points[1].setCoordinates(10,2);
        points[2].setCoordinates(6,3);
        tri2.getCorners(points);
        tri2.convert();
        tri2.setType();
        assertEquals(true, tri2.checkIfTriangle());
        assertEquals("This is an isosceles triangle.\n", tri2.getType());
        points[0].setCoordinates(3, 3);
        points[1].setCoordinates(10,5);
        points[2].setCoordinates(6,8);
        tri3.getCorners(points);
        tri3.convert();
        tri3.setType();
        assertEquals(true, tri3.checkIfTriangle());
        assertEquals("This is a scalene triangle.\n", tri3.getType());

    }
}
