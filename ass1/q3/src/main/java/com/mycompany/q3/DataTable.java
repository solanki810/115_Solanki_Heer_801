/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q3;

/**
 *
 * @author root
 */
import java.util.Hashtable;

public class DataTable implements Featurable {

    private String[][] table;
    private int rows, cols;

    private String foreground = "Black";
    private String background = "White";
    private String font = "Arial";

    /* Constructor */
    public DataTable(int rows, int cols) throws TableException {
        if (rows > 200 || cols > 200) {
            throw new TableException("Rows or Columns cannot exceed 200");
        }
        this.rows = rows;
        this.cols = cols;
        table = new String[rows][cols];
    }

    /* Insert value */
    public void setValue(int r, int c, String value) {
        table[r][c] = value;
    }

    /* Insert Row */
    public void insertRow() throws TableException {
        if (rows + 1 > 200)
            throw new TableException("Row limit exceeded");

        String[][] newTable = new String[rows + 1][cols];

        for (int i = 0; i < rows; i++)
            newTable[i] = table[i];

        table = newTable;
        rows++;
    }

    /* Delete Row */
    public void deleteRow() {
        String[][] newTable = new String[rows - 1][cols];

        for (int i = 0; i < rows - 1; i++)
            newTable[i] = table[i];

        table = newTable;
        rows--;
    }

    /* Insert Column */
    public void insertColumn() throws TableException {
        if (cols + 1 > 200)
            throw new TableException("Column limit exceeded");

        String[][] newTable = new String[rows][cols + 1];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                newTable[i][j] = table[i][j];

        table = newTable;
        cols++;
    }

    /* Delete Column */
    public void deleteColumn() {
        String[][] newTable = new String[rows][cols - 1];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols - 1; j++)
                newTable[i][j] = table[i][j];

        table = newTable;
        cols--;
    }

    /* Display Table */
    public void display() {
        System.out.println("\nTable Data:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print((table[i][j] == null ? "-" : table[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    /* Populate using Hashtable */
    public void populate(Hashtable<Integer, String> data) {
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data.containsKey(k))
                    table[i][j] = data.get(k++);
            }
        }
    }

    /* Featurable methods */
    public void setForeground(String color) { foreground = color; }
    public void setBackground(String color) { background = color; }
    public void setFont(String font) { this.font = font; }

    public String getForeground() { return foreground; }
    public String getBackground() { return background; }
    public String getFont() { return font; }
}

