/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.q1;

/**
 *
 * @author root
 */
// Superclass Shape
abstract class Shape {
    // Instance variable
    String color;
    
    // Constructor
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract methods (must be overridden by subclasses)
    public abstract double getArea();
    
    @Override
    public abstract String toString();
}

// Rectangle class inherits from Shape
class Rectangle extends Shape {
    // Instance variables
    int length, width;
    
    // Constructor
    public Rectangle(String color, int length, int width) {
        super(color);  // Calling Shape's constructor
        this.length = length;
        this.width = width;
    }
    
    // Implementing getArea() method
    @Override
    public double getArea() {
        return length * width; // Area of a rectangle
    }
    
    // Implementing toString() method
    @Override
    public String toString() {
        return "Rectangle [color=" + color + ", length=" + length + ", width=" + width + "]";
    }
}

// Triangle class inherits from Shape
class Triangle extends Shape {
    // Instance variables
    int base, height;
    
    // Constructor
    public Triangle(String color, int base, int height) {
        super(color);  // Calling Shape's constructor
        this.base = base;
        this.height = height;
    }
    
    // Implementing getArea() method
    @Override
    public double getArea() {
        return 0.5 * base * height; // Area of a triangle
    }
    
    // Implementing toString() method
    @Override
    public String toString() {
        return "Triangle [color=" + color + ", base=" + base + ", height=" + height + "]";
    }
}
public class Q1 {
    
    public static void main(String[] args) {
         // Upcasting
        Shape shape1 = new Rectangle("Red", 5, 8);  // Rectangle upcasted to Shape
        Shape shape2 = new Triangle("Blue", 4, 6);  // Triangle upcasted to Shape
        
        // Using polymorphism to call getArea() and toString() methods
        System.out.println("Area of shape1: " + shape1.getArea());  // Polymorphic method call
        System.out.println(shape1.toString());  // Polymorphic method call
        
        System.out.println("Area of shape2: " + shape2.getArea());  // Polymorphic method call
        System.out.println(shape2.toString());  // Polymorphic method call
        
        // Downcasting example
        Rectangle rect = (Rectangle) shape1;  // Downcasting back to Rectangle
        System.out.println("Downcasted Rectangle's area: " + rect.getArea());
        
        // Note: Downcasting can throw ClassCastException if the types don't match
        // Triangle tri = (Triangle) shape1;  // This would throw a ClassCastException
    }
}
