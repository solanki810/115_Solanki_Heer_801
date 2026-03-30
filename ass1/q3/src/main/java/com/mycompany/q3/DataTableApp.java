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
import java.util.Scanner;

public class DataTableApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DataTable table = null;

        try {
            System.out.print("Enter number of rows: ");
            int r = sc.nextInt();

            System.out.print("Enter number of columns: ");
            int c = sc.nextInt();

            table = new DataTable(r, c);

        } catch (TableException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n1.Insert Value");
            System.out.println("2.Insert Row");
            System.out.println("3.Delete Row");
            System.out.println("4.Insert Column");
            System.out.println("5.Delete Column");
            System.out.println("6.Display Table");
            System.out.println("7.Set Colors & Font");
            System.out.println("8.Populate using Hashtable");
            System.out.println("9.Exit");

            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        System.out.print("Row: ");
                        int r = sc.nextInt();
                        System.out.print("Column: ");
                        int c = sc.nextInt();
                        System.out.print("Value: ");
                        table.setValue(r, c, sc.next());
                        break;

                    case 2:
                        table.insertRow();
                        break;

                    case 3:
                        table.deleteRow();
                        break;

                    case 4:
                        table.insertColumn();
                        break;

                    case 5:
                        table.deleteColumn();
                        break;

                    case 6:
                        table.display();
                        break;

                    case 7:
                        System.out.print("Foreground: ");
                        table.setForeground(sc.next());
                        System.out.print("Background: ");
                        table.setBackground(sc.next());
                        System.out.print("Font: ");
                        table.setFont(sc.next());
                        break;

                    case 8:
                        Hashtable<Integer, String> ht = new Hashtable<>();
                        ht.put(0, "A");
                        ht.put(1, "B");
                        ht.put(2, "C");
                        ht.put(3, "D");
                        table.populate(ht);
                        break;

                    case 9:
                        System.exit(0);
                }
            } catch (TableException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
