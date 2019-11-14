import java.util.Scanner;
public class Regresi {

    Scanner sc = new Scanner(System.in);
    //DecimalFormat df = new DecimalFormat("#.00");
    int data;
    double sigmaX = 0, sigmaY = 0;
    double sigmax2 = 0, sigmaxy = 0;
    double tabel[][];
    double gaussJordan[][];
    double hasilHitung[][];

    void inputTabel() {
        System.out.println("Masukkan Jumlah Data : ");
        data = sc.nextInt();
        tabel = new double[data][4];
        for (int i = 0; i < data; i++) {
            System.out.println("Data ke " + (i + 1) + " : ");
            for (int j = 0; j < 2; j++)
                if (j == 0) {
                    System.out.println("Masukkan Nilai Xi : ");
                    tabel[i][j] = sc.nextDouble();
                    sigmaX = sigmaX + tabel[i][j];
                    tabel[i][j + 2] = Math.pow(tabel[i][j], 2);
                    sigmax2 = sigmax2 + tabel[i][j + 2];
                } else if (j == 1) {
                    System.out.println("Masukkan Nilai Yi : ");
                    tabel[i][j] = sc.nextDouble();
                    sigmaY = sigmaY + tabel[i][j];
                    tabel[i][j + 2] = tabel[i][0] * tabel[i][1];
                    sigmaxy = sigmaxy + tabel[i][j + 2];
                }
        }
    }

    void printTabel() {
        for (int i = 0; i < tabel.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < tabel[0].length; j++)
                System.out.print(tabel[i][j] + "\t\t");
        }
    }

    void hitungGaussJordan() {
        gaussJordan = new double[2][3];
        gaussJordan[0][0] = data;
        gaussJordan[0][1] = sigmaX;
        gaussJordan[0][2] = sigmaY;
        gaussJordan[1][0] = sigmaX;
        gaussJordan[1][1] = sigmax2;
        gaussJordan[1][2] = sigmaxy;
        double temp, temp2;
        for (int i = 0; i < gaussJordan.length; i++) {
            temp = gaussJordan[i][i];
            for (int j = 0; j < gaussJordan[0].length; j++) {
                gaussJordan[i][j] = gaussJordan[i][j] / temp;
            }
            if (i == 0) {
                temp2 = gaussJordan[1][0];
                for (int j = 0; j < gaussJordan[0].length; j++) {
                    gaussJordan[i + 1][j] = gaussJordan[i + 1][j] - gaussJordan[i][j] * temp2;
                }
            } else if (i == 1) {
                temp2 = gaussJordan[0][1];
                for (int j = 0; j < gaussJordan[0].length; j++) {
                    gaussJordan[i - 1][j] = gaussJordan[i - 1][j] - gaussJordan[i][j] * temp2;
                }
            }
        }

        for (int i = 0; i < gaussJordan.length; i++) {
            System.out.println("");
            for (int j = 0; j < gaussJordan[0].length; j++) {
                System.out.print(gaussJordan[i][j] + "\t\t");
            }

        }
    }

    void Hasil() {
        hasilHitung = new double[data][3];
        for (int i = 0; i < hasilHitung.length; i++) {
            for (int j = 0; j < 2; j++) {
                hasilHitung[i][j] = tabel[i][j];
            }

        }
        for (int i = 0; i < hasilHitung.length; i++) {
            hasilHitung[i][2] = gaussJordan[0][2] + gaussJordan[1][2] * hasilHitung[i][0];
        }
        for (int i = 0; i < hasilHitung.length; i++) {
            System.out.println("");
            for (int j = 0; j < hasilHitung[0].length; j++) {
                System.out.print(hasilHitung[i][j] + "\t\t");
            }

        }
    }





    public static void main(String[] args) {
        System.out.println("==== Regresi Linear ===");
        Regresi rr = new Regresi();
        rr.inputTabel();
        System.out.println("Tabel yang diinput : ");
        rr.printTabel();
        System.out.println("\n\n");
        System.out.println("Matriks Gauss Jordan : ");
        rr.hitungGaussJordan();
        System.out.println("\nNilai a : "+rr.gaussJordan[0][2]);
        System.out.println("Nilai b : "+rr.gaussJordan[1][2]);
        System.out.println("Sistem Persamaan garis regresi : f(x) = "+rr.gaussJordan[0][2]+" + "+rr.gaussJordan[1][2]+"x");
        System.out.println("");
        System.out.println("Hasil perhitungan persamaan : ");
        System.out.println("Xi\t\tYi\t\tf(Xi)");
        rr.Hasil();
    }
}
