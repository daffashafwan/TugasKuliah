/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

/**
 *
 * @author PC MAINTENANCe
 */
public class JavaApplication12 {

    double tabel[][];
    double h = 0.125;
    double a = 0, b = 1;
    double x = (a + h) / 2;
    double k = 0;
    int data = 8;

    void hitung() {
        tabel = new double[data][3];
        for (int i = 0; i < data - 1; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    tabel[i][j] = k + 0.5;
                    k++;
                } else if (j == 1) {
                    tabel[i][j] = x;
                    x = x + h;
                } else if (j == 2) {
                    tabel[i][j] = (1 / (1 + x));
                }
            }
        }
    }

    void print() {
        for (int i = 0; i < data - 1; i++) {
            for (int j = 0; j < 3; j++) {
                  System.out.print(tabel[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        JavaApplication12 ff = new JavaApplication12();
        ff.hitung();
        ff.print();
    }

}
