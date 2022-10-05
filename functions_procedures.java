/*
Achmadya Ridwan Ilyawan (211511001)
Ari Maulana Hardan (211511007)
Meisya Puteri Ghefira (211511021)
Naufal Salman Mulyadi (211511028)
*/

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class functions_procedures {
  /*
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
              MASUK KE BAGIAN SPL GAUSS DAN GAUSS JORDAN
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  */
  public static void mainsplgauss() {
    Scanner scan = new Scanner(System.in);
    // System.out.print("Jumlah baris: ");
    // int baris = scan.nextInt();
    //
    // System.out.print("Jumlah kolom: ");
    // int kolom = scan.nextInt();

    int baris = 7;
    int kolom = 8;
    float[][] Matrix = new float[baris][kolom];
    // 1
    Matrix[0][0] = 1;
    Matrix[0][1] = 2;
    Matrix[0][2] = 3;
    Matrix[0][3] = 4;
    Matrix[0][4] = 2;
    Matrix[0][5] = 3;
    Matrix[0][6] = 4;
    Matrix[0][7] = 4;
    // 2
    Matrix[1][0] = 1;
    Matrix[1][1] = 2;
    Matrix[1][2] = 3;
    Matrix[1][3] = 4;
    Matrix[1][4] = 2;
    Matrix[1][5] = 6;
    Matrix[1][6] = 7;
    Matrix[1][7] = 4;
    // 3
    Matrix[2][0] = 3;
    Matrix[2][1] = 5;
    Matrix[3][2] = 3;
    Matrix[2][3] = 4;
    Matrix[2][4] = 2;
    Matrix[2][5] = 6;
    Matrix[2][6] = 4;
    Matrix[2][7] = 6;
    // 4
    Matrix[3][0] = 1;
    Matrix[3][1] = 5;
    Matrix[3][2] = 3;
    Matrix[3][3] = 4;
    Matrix[3][4] = 4;
    Matrix[3][5] = 3;
    Matrix[3][6] = 6;
    Matrix[3][7] = 4;
    // 5
    Matrix[4][0] = 1;
    Matrix[4][1] = 4;
    Matrix[4][2] = 3;
    Matrix[4][3] = 4;
    Matrix[4][4] = 2;
    Matrix[4][5] = 6;
    Matrix[4][6] = 4;
    Matrix[4][7] = 4;
    // 6
    Matrix[5][0] = 1;
    Matrix[5][1] = 2;
    Matrix[5][2] = 3;
    Matrix[5][3] = 8;
    Matrix[5][4] = 2;
    Matrix[5][5] = 3;
    Matrix[5][6] = 8;
    Matrix[5][7] = 4;
    // 7
    Matrix[6][0] = 1;
    Matrix[6][1] = 4;
    Matrix[6][2] = 3;
    Matrix[6][3] = 6;
    Matrix[6][4] = 5;
    Matrix[6][5] = 3;
    Matrix[6][6] = 6;
    Matrix[6][7] = 4;

    // inputMatrixdata(scan, Matrix, baris, kolom);

    if (kolom < baris) {
      System.out.println(
          "\nKarena jumlah kolom < jumlah baris, maka SPL bersifat inkonsisten");
    } else {
      System.out.println("\nMatriks yang diinputkan: ");
      printMatrixInput(Matrix, baris, kolom);
      System.out.println("\nMatrix Gauss dari matriks masukan Anda adalah");
      printMatrix(gauss(Matrix, baris, kolom), baris, kolom);
      printsolusi(gauss(Matrix, baris, kolom), baris, kolom);
    }
  }

  public static void printMatrixInput(float[][] Matrix, int baris, int kolom) {
    for (int i = 0; i < baris; i++) {
      System.out.print("[");
      for (int j = 0; j < kolom; j++) {
        System.out.print((int)Matrix[i][j] + " ");
      }
      System.out.println("]");
    }
  }

  public static void mainsplgaussjordan() {
    Scanner scan = new Scanner(System.in);
    // System.out.print("Jumlah baris: ");
    // int baris = scan.nextInt();

    // System.out.print("Jumlah kolom: ");
    // int kolom = scan.nextInt();

    int baris = 7;
    int kolom = 8;
    float[][] Matrix = new float[baris][kolom];
    // 1
    Matrix[0][0] = 1;
    Matrix[0][1] = 2;
    Matrix[0][2] = 3;
    Matrix[0][3] = 4;
    Matrix[0][4] = 2;
    Matrix[0][5] = 3;
    Matrix[0][6] = 4;
    Matrix[0][7] = 4;
    // 2
    Matrix[1][0] = 1;
    Matrix[1][1] = 2;
    Matrix[1][2] = 3;
    Matrix[1][3] = 4;
    Matrix[1][4] = 2;
    Matrix[1][5] = 6;
    Matrix[1][6] = 7;
    Matrix[1][7] = 4;
    // 3
    Matrix[2][0] = 3;
    Matrix[2][1] = 5;
    Matrix[3][2] = 3;
    Matrix[2][3] = 4;
    Matrix[2][4] = 2;
    Matrix[2][5] = 6;
    Matrix[2][6] = 4;
    Matrix[2][7] = 6;
    // 4
    Matrix[3][0] = 1;
    Matrix[3][1] = 5;
    Matrix[3][2] = 3;
    Matrix[3][3] = 4;
    Matrix[3][4] = 4;
    Matrix[3][5] = 3;
    Matrix[3][6] = 6;
    Matrix[3][7] = 4;
    // 5
    Matrix[4][0] = 1;
    Matrix[4][1] = 4;
    Matrix[4][2] = 3;
    Matrix[4][3] = 4;
    Matrix[4][4] = 2;
    Matrix[4][5] = 6;
    Matrix[4][6] = 4;
    Matrix[4][7] = 4;
    // 6
    Matrix[5][0] = 1;
    Matrix[5][1] = 2;
    Matrix[5][2] = 3;
    Matrix[5][3] = 8;
    Matrix[5][4] = 2;
    Matrix[5][5] = 3;
    Matrix[5][6] = 8;
    Matrix[5][7] = 4;
    // 7
    Matrix[6][0] = 1;
    Matrix[6][1] = 4;
    Matrix[6][2] = 3;
    Matrix[6][3] = 6;
    Matrix[6][4] = 5;
    Matrix[6][5] = 3;
    Matrix[6][6] = 6;
    Matrix[6][7] = 4;
    // inputMatrixdata(scan, Matrix, baris, kolom);

    if (kolom < baris) {
      System.out.println(
          "\nKarena jumlah kolom < jumlah baris, maka SPL bersifat inkonsisten");
    }

    else {
      System.out.println(
          "\nMatrix Gauss-Jordan dari matrix masukan Anda adalah");
      printMatrix(gaussjordan(Matrix, baris, kolom), baris, kolom);
      printsolusi(gaussjordan(Matrix, baris, kolom), baris, kolom);
    }
  }

  public static void inputMatrixdata(Scanner scan, float[][] Matrix, int baris,
                                     int kolom) {
    System.out.println("Input matriks augmented: ");

    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < kolom; j++) {
        System.out.print("Elemen matriks ke-[" + (i + 1) + "][" + (j + 1) +
                         "]: ");
        Matrix[i][j] = scan.nextFloat();
        printMatrixInput(Matrix, baris, kolom);
      }
    }
  }

  public static void printMatrix(float[][] Matrix, int baris, int kolom) {
    for (int i = 1; i < kolom; i++) {
      System.out.print("X" + i + "\t");
    }
    System.out.println();
    for (int i = 0; i < baris; i++) {
      System.out.print("[");
      for (int j = 0; j < kolom; j++) {
        if (j == kolom - 1) {
          System.out.printf("%.1f", Matrix[i][j]);
        } else {
          System.out.printf("%.1f", Matrix[i][j]);
          System.out.print("\t");
        }
      }
      System.out.print("]");
      System.out.println();
    }
  }

  public static int firstfoundX(float[][] Matrix, int i, int kolom, int X) {
    int firstfound = 0;
    for (int j = 0; j < kolom; j++) {
      if (Matrix[i][j] == X) {
        firstfound = j;
        break;
      }
    }
    return firstfound;
  }

  public static int firstfoundnotX(float[][] Matrix, int i, int kolom, int X) {
    int firstnotfound = kolom;
    for (int j = 0; j < kolom; j++) {
      if (Matrix[i][j] != X) {
        firstnotfound = j;
        break;
      }
    }
    return firstnotfound;
  }

  public static void make0centered(float[][] Matrix, int baris, int kolom) {
    for (int i = 0; i < baris; i++) {
      for (int j = 0; j < baris; j++) {
        if (firstfoundnotX(Matrix, i, kolom, 0) <
            firstfoundnotX(Matrix, j, kolom, 0)) {
          tukarbaris(Matrix, i, j, kolom);
        }
      }
    }
  }

  public static void tukarbaris(float[][] Matrix, int idxbaris1, int idxbaris2,
                                int kolom) {
    float[] temp = new float[kolom];

    // MENYIMPAN BARIS1 DI TEMP
    for (int j = 0; j < kolom; j++) {
      temp[j] = Matrix[idxbaris1][j];
    }

    // MENUKAR BARIS1 MENJADI BARIS2
    for (int j = 0; j < kolom; j++) {
      Matrix[idxbaris1][j] = Matrix[idxbaris2][j];
    }

    // MENUKAR BARIS2 MENJADI TEMP
    for (int j = 0; j < kolom; j++) {
      Matrix[idxbaris2][j] = temp[j];
    }
  }

  public static float[][] gauss(float[][] Matrix, int baris, int kolom) {

    int looping = baris;
    make0centered(Matrix, baris, kolom);

    for (int l = looping; l > 0; l--) {
      // MEMBAGI ELEMEN PERTAMA SETELAH NOL BIAR JADI 1
      float divider = Matrix[baris - l][baris - l];
      boolean dividernol;
      if (divider == 0) {
        dividernol = true;
        int jdiv = baris - l + 1;
        while (jdiv < kolom && dividernol) {
          divider = Matrix[baris - l][jdiv];
          if (divider == 0)
            dividernol = true;
          else {
            dividernol = false;
          }
          jdiv += 1;
        }
      } else {
        dividernol = false;
      }

      boolean nolsemua;
      if (dividernol)
        nolsemua = true;
      else
        nolsemua = false;

      if (!dividernol && !nolsemua) {
        for (int j = 0; j < kolom; j++) {
          Matrix[baris - l][j] /= divider;
        }
      }

      // MAU BIKIN JADI NOL SEMUA KE BAWAH
      int kol = firstfoundX(Matrix, baris - l, kolom, 1);
      for (int i = baris - l + 1; i < baris; i++) {
        float multiplier = (-1) * Matrix[i][kol];
        for (int j = 0; j < kolom; j++) {
          Matrix[i][j] += multiplier * Matrix[baris - l][j];
          if (Matrix[i][j] == -0.0) {
            Matrix[i][j] = Float.parseFloat("0.0");
          }
        }
      }
    }
    return Matrix;
  }

  public static float[][] gaussjordan(float[][] Matrix, int baris, int kolom) {
    Matrix = gauss(Matrix, baris, kolom);

    for (int i = 0; i < baris; i++) {
      boolean found1first = false;
      for (int j = 0; j < kolom; j++) {
        if (Matrix[i][j] == 1 && !found1first) {
          found1first = true;
          for (int k = 0; k < i; k++) {
            float multiplier = (-1) * Matrix[k][j];
            for (int l = 0; l < kolom; l++) {
              Matrix[k][l] += multiplier * Matrix[i][l];
            }
          }
        }
      }
    }
    return Matrix;
  }

  public static void printsolusi(float[][] Matrix, int baris, int kolom) {
    boolean unik = false, banyak = false, tidakada = false;

    if (firstfoundnotX(Matrix, baris - 1, kolom, 0) == kolom)
      banyak = true;
    else
      banyak = false;

    if (firstfoundnotX(Matrix, baris - 1, kolom, 0) == kolom - 1)
      tidakada = true;
    else
      tidakada = false;

    for (int i = 0; i < baris - 1; i++) {
      if (firstfoundX(Matrix, i, kolom, 1) ==
          firstfoundX(Matrix, i + 1, kolom, 1) - 1)
        unik = true;
      else
        unik = false;
    }

    if (!unik && !tidakada)
      banyak = true;

    if (banyak)
      System.out.println("Terdapat banyak solusi");
    if (tidakada)
      System.out.println("Tidak ada solusi");
    if (unik && !banyak && !tidakada)
      System.out.println("Terdapat solusi unik");

    if (unik && !banyak && !tidakada)
      printsolusiunik(Matrix, baris, kolom);
    if (banyak)
      printsolusibanyak(Matrix, baris, kolom);
  }

  public static void printsolusiunik(float[][] Matrix, int baris, int kolom) {
    float[] ArrayTemp = new float[kolom - 1];

    for (int i = baris - 1; i >= 0; i--) {
      ArrayTemp[i] = Matrix[i][kolom - 1];
      int k = kolom - 2;
      while (k > i) {
        ArrayTemp[i] -= Matrix[i][k] * ArrayTemp[k];
        k--;
      }
    }

    for (int j = 0; j < kolom - 1; j++) {
      int k = j + 1;
      System.out.print("X" + k + " = ");
      System.out.printf("%.1f\n", ArrayTemp[j]);
    }
  }

  public static void printsolusibanyak(float[][] Matrix, int baris, int kolom) {
    float[] ArrayTemp = new float[kolom - 1];

    for (int i = 0; i < kolom - 1; i++)
      ArrayTemp[i] = 99999;

    for (int i = baris - 1; i >= 0; i--) {
      ArrayTemp[firstfoundX(Matrix, i, kolom - 1, 1)] = Matrix[i][kolom - 1];
      int k = kolom - 2;
      while (k > i) {
        if (ArrayTemp[k] != 99999) {
          ArrayTemp[i] -= Matrix[i][k] * ArrayTemp[k];
        }
        k--;
      }
    }

    for (int j = 0; j < kolom - 1; j++) {
      int ascii = (96 + j + 1);
      char x = (char)ascii;
      int k = j + 1;
      System.out.print("X" + k + " = ");
      if (!(ArrayTemp[j] == 99999)) {
        System.out.printf("%.1f", ArrayTemp[j]);

        int bar = 0;
        for (int i = 0; i < baris; i++) {
          if (firstfoundX(Matrix, i, kolom, 1) == j) {
            bar = i;
            break;
          }
        }

        for (int l = j + 1; l < kolom - 1; l++) {
          int ascii_1 = (96 + l + 1);
          x = (char)ascii_1;
          if (ArrayTemp[l] == 99999) {
            if (Matrix[bar][l] > 0) {
              System.out.println(" - " + Matrix[bar][l] + x);
            } else if (Matrix[bar][l] < 0) {
              System.out.println(" + " + ((-1) * Matrix[bar][l]) + x);
            }
          }
        }
      } else {
        System.out.print(x);
      }
      System.out.print("\n");
    }
  }
}  
