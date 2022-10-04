/*
Achmadya Ridwan Ilyawan (211511001)
Ari Maulana Hardan (211511007)
Meisya Puteri Ghefira (211511021)
Naufal Salman Mulyadi (211511028)
*/

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class functions_procedures {
    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                MASUK KE BAGIAN SPL GAUSS DAN GAUSS JORDAN
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */
    public static void mainsplgauss() {
        Scanner scan = new Scanner(System.in);
            System.out.print("Jumlah baris: ");
            int baris = scan.nextInt();

            System.out.print("Jumlah kolom: ");
            int kolom = scan.nextInt();

            float[][] Matrix = new float[baris][kolom];

            inputMatrixdata(scan,Matrix,baris,kolom);

            if (kolom < baris) {
                System.out.println("\nKarena jumlah kolom < jumlah baris, maka SPL bersifat inkonsisten");           
            }
            else {
                System.out.println("\nMatriks yang diinputkan: ");
                printMatrixInput(Matrix,baris,kolom);
                System.out.println("\nMatrix Gauss dari matriks masukan Anda adalah");
                printMatrix(gauss(Matrix,baris,kolom),baris,kolom);
                printsolusi(gauss(Matrix,baris,kolom), baris, kolom);
            }
    }

    public static void printMatrixInput(float[][] Matrix, int baris, int kolom){
        for (int i = 0; i < baris; i++) {
            System.out.print("[");
            for (int j = 0; j < kolom; j++) {
                System.out.print((int) Matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    public static void mainsplgaussjordan() {
        Scanner scan = new Scanner(System.in);
            System.out.print("Jumlah baris: ");
            int baris = scan.nextInt();

            System.out.print("Jumlah kolom: ");
            int kolom = scan.nextInt();

            float[][] Matrix = new float[baris][kolom];

            inputMatrixdata(scan,Matrix,baris,kolom);

            if (kolom < baris) {
                System.out.println("\nKarena jumlah kolom < jumlah baris, maka SPL bersifat inkonsisten");
            }

            else {
                System.out.println("\nMatrix Gauss-Jordan dari matrix masukan Anda adalah");
                printMatrix(gaussjordan(Matrix, baris, kolom),baris,kolom);
                printsolusi(gaussjordan(Matrix,baris,kolom),baris,kolom);
            }
    }

    public static void inputMatrixdata(Scanner scan,float[][] Matrix,int baris,int kolom) {
        System.out.println("Input matriks augmented: ");

        for(int i=0; i<baris; i++) {
            for(int j=0; j<kolom; j++) {
                System.out.print("Masukkan elemen matriks ke-[" + (i+1) + "][" + (j+1) + "]: ");
                Matrix[i][j] = scan.nextFloat();
                printMatrixInput(Matrix,baris,kolom);
            }
        }
    }

    public static void printMatrix(float[][] Matrix, int baris, int kolom) {
        for(int i=1; i < kolom; i++){
            System.out.print("X" + i + "\t");
        }
        System.out.println();
        for(int i=0; i<baris; i++) {
            System.out.print("[");
            for(int j=0; j<kolom; j++) {
                if (j == kolom-1) {
                    System.out.printf("%.1f", Matrix[i][j]);
                } 
                else {
                    System.out.printf("%.1f", Matrix[i][j]);
                    System.out.print("\t");
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public static int firstfoundX(float[][] Matrix,int i,int kolom,int X) {
        int firstfound = 0;
        for(int j=0; j<kolom; j++) {
            if (Matrix[i][j] == X) {
                firstfound = j;
                break;
            }
        }
        return firstfound;
    }

    public static int firstfoundnotX(float[][] Matrix,int i,int kolom,int X) {
        int firstnotfound = kolom; 
        for(int j=0; j<kolom; j++) {
            if (Matrix[i][j] != X) {
                firstnotfound = j;
                break;
            }
        }
        return firstnotfound;
    }

    public static void make0centered(float[][] Matrix, int baris, int kolom) {
        for(int i=0; i<baris; i++) {
            for(int j=0; j<baris; j++) {
                if (firstfoundnotX(Matrix, i, kolom,0) < firstfoundnotX(Matrix, j, kolom,0)) {
                    tukarbaris(Matrix, i, j, kolom);
                }
            }
        }
    }

    public static void tukarbaris(float[][] Matrix, int idxbaris1, int idxbaris2, int kolom) {
        float[] temp = new float[kolom];

        //MENYIMPAN BARIS1 DI TEMP
        for(int j=0; j<kolom; j++) {
            temp[j] = Matrix[idxbaris1][j];
        }

        //MENUKAR BARIS1 MENJADI BARIS2
        for(int j=0; j<kolom; j++) {
            Matrix[idxbaris1][j] = Matrix[idxbaris2][j];
        }

        //MENUKAR BARIS2 MENJADI TEMP
        for(int j=0; j<kolom; j++) {
            Matrix[idxbaris2][j] = temp[j];
        }
    }

    public static float[][] gauss(float[][] Matrix,int baris, int kolom) {

        int looping = baris;
        make0centered(Matrix, baris, kolom);

        for(int l=looping; l>0; l--) {
            //MEMBAGI ELEMEN PERTAMA SETELAH NOL BIAR JADI 1
            float divider = Matrix[baris-l][baris-l];
            boolean dividernol;
            if (divider==0) {
                dividernol = true;
                int jdiv = baris-l+1;
                while (jdiv<kolom && dividernol) {
                    divider = Matrix[baris-l][jdiv];
                    if (divider==0) dividernol = true;
                    else {
                        dividernol = false;
                    }
                    jdiv += 1;
                }
            }
            else {
                dividernol = false;
            }

            boolean nolsemua;
            if (dividernol) nolsemua = true;
            else nolsemua = false;

            if (!dividernol && !nolsemua) {
                for(int j=0; j<kolom; j++) {
                    Matrix[baris-l][j] /= divider;
                }
            }
        
            //MAU BIKIN JADI NOL SEMUA KE BAWAH
            int kol = firstfoundX(Matrix, baris-l, kolom, 1);
            for(int i=baris-l+1; i<baris; i++) {
                float multiplier = (-1)*Matrix[i][kol];
                for(int j=0; j<kolom; j++) {
                    Matrix[i][j] += multiplier*Matrix[baris-l][j];
                    if (Matrix[i][j] == -0.0) {
                        Matrix[i][j] = Float.parseFloat("0.0");
                    }
                }   
            } 
        }
        return Matrix; 
    }

    public static float[][] gaussjordan(float[][] Matrix,int baris, int kolom) {
        Matrix = gauss(Matrix,baris,kolom);

        for(int i=0; i<baris; i++) {
            boolean found1first = false;
            for(int j=0; j<kolom; j++) {
                if (Matrix[i][j] == 1 && !found1first) {
                    found1first = true;
                    for(int k=0; k<i; k++) {
                        float multiplier = (-1)*Matrix[k][j];
                        for(int l=0; l<kolom; l++) {
                            Matrix[k][l] += multiplier*Matrix[i][l];
                        }
                    }
                }
            }
        }
        return Matrix;
    }

    public static void printsolusi (float[][] Matrix, int baris, int kolom) {
        boolean unik=false,banyak=false,tidakada = false;

        if (firstfoundnotX(Matrix, baris-1, kolom, 0)==kolom) banyak = true;
        else banyak = false;

        if (firstfoundnotX(Matrix, baris-1, kolom, 0)==kolom-1) tidakada = true;
        else tidakada = false;

        for(int i=0; i<baris-1; i++) {
            if (firstfoundX(Matrix,i,kolom,1) == firstfoundX(Matrix,i+1,kolom,1)-1) unik = true;
            else unik = false;
        }

        if (!unik && !tidakada) banyak = true;

        if (banyak) System.out.println("Terdapat banyak solusi");
        if (tidakada) System.out.println("Tidak ada solusi");
        if (unik && !banyak && !tidakada) System.out.println("Terdapat solusi unik");

        if (unik && !banyak && !tidakada) printsolusiunik(Matrix, baris, kolom);
        if (banyak) printsolusibanyak(Matrix, baris, kolom);
    }

    public static void printsolusiunik (float[][] Matrix, int baris, int kolom) {
        float[] ArrayTemp = new float[kolom-1]; 
        
        for(int i=baris-1; i>=0; i--) {
            ArrayTemp[i] = Matrix[i][kolom-1];
            int k=kolom-2;
            while(k>i) {
                ArrayTemp[i] -= Matrix[i][k]*ArrayTemp[k];
                k--;
            }
        }

        for(int j=0; j<kolom-1; j++) {
            int k = j+1;
            System.out.print("X"+k+" = ");
            System.out.printf("%.1f\n", ArrayTemp[j]);
        }
    }

    public static void printsolusibanyak (float[][] Matrix, int baris, int kolom) {
        float[] ArrayTemp = new float[kolom-1];

        for(int i=0; i<kolom-1; i++) ArrayTemp[i] = 99999;

        for(int i=baris-1; i>=0; i--) {
            ArrayTemp[firstfoundX(Matrix, i, kolom-1, 1)] = Matrix[i][kolom-1];
            int k=kolom-2;
            while(k>i) { 
                if (ArrayTemp[k] != 99999) {
                    ArrayTemp[i] -= Matrix[i][k]*ArrayTemp[k];
                }
                k--;
            }
        }

        for(int j=0; j<kolom-1; j++) {
            int ascii = (96+j+1);
            char x = (char) ascii;
            int k = j+1;
            System.out.print("X"+k+" = ");
            if (!(ArrayTemp[j] == 99999)) {
                System.out.printf("%.1f", ArrayTemp[j]);
                
                int bar = 0;
                for(int i=0; i<baris; i++) {
                    if (firstfoundX(Matrix, i, kolom, 1) == j) {
                        bar = i;
                        break;
                    }
                }
    
                for(int l=j+1; l<kolom-1; l++) {
                    int ascii_1 = (96+l+1);
                    x = (char) ascii_1;
                    if (ArrayTemp[l] == 99999) {
                        if (Matrix[bar][l] > 0) {
                            System.out.println(" - "+Matrix[bar][l]+x);
                        }
                        else if (Matrix[bar][l] < 0) {
                            System.out.println(" + "+((-1)*Matrix[bar][l])+x);
                        }
                    }
                }
            }
            else {
                System.out.print(x);
            }
            System.out.print("\n");
        }
    }

    public static void mainsplinvers()
    {
        Scanner keyboard = new Scanner(System.in);
        int sumSPL = 1;
        int sumvar = 2;
        
        while(sumSPL != sumvar) {
			try {
				System.out.println("-------------------------------------------------------------------------");
				System.out.print("Masukkan jumlah sistem persamaan linear Anda: ");
                sumSPL = keyboard.nextInt();
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("PERINGATAN: Jumlah variabel dan jumlah persamaan harus sama!");
                System.out.println("Jika tidak sama, maka SPL Anda tidak dapat diselesaikan dengan metode invers!");
                System.out.print("Masukkan jumlah variabel persamaan linear Anda: ");
                sumvar = keyboard.nextInt();
			} catch (InputMismatchException ex) {
				keyboard.next();
			}
		}
        
        int i;
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Jika SPL adalah x + 2y + 3z = 4");
        System.out.println("Maka masukan matriks konstanta variabel adalah 1 2 3 dan masukan hasil adalah 4");
        System.out.println("-------------------------------------------------------------------------");
        double [][]thematriks = new double[sumSPL][sumvar];
        double [][]constants = new double[sumSPL][1];
        //input Sistem Persamaan Linear
        for (i=0; i<sumSPL; i++){
            for (int j=0; j<sumvar; j++){
                System.out.print("Masukkan konstanta variabel ke-"+(j+1)+" untuk persamaan ke-"+(i+1)+": ");
                thematriks[i][j] = keyboard.nextDouble();
            }
            System.out.print("Masukkan hasil persamaan ke-"+(i+1)+": ");
            constants[i][0] = keyboard.nextDouble();
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Matriks augmented dari inputan Anda adalah :");
        //Menuliskan Sistem Persamaan Linear ke bentuk matriks
        for(i=0; i<sumSPL; i++)
        {
            for(int j=0; j<sumvar; j++)
            {System.out.print(" "+thematriks[i][j]);}
            System.out.print("  ");
            System.out.print("= "+ constants[i][0]);
            System.out.println();
        }

        //melakukan pengecekan apakah matriks invertible atau tidak.
 
        if(determinant(thematriks, sumSPL) == 0)
        {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Matriks tidak invertible, karena determinannya adalah : "+determinant(thematriks, sumSPL));
            System.out.println("Sistem Persamaan Linear Anda tidak memiliki solusi");
            System.out.println("-------------------------------------------------------------------------");
        }
        else
        {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Matriks invertible, karena determinannya adalah : "+determinant(thematriks, sumSPL));
            double d[][] = invert(thematriks); 
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Invers dari matriks Anda adalah: ");
            for (i=0; i<sumSPL; ++i) 
            {
                for (int j=0; j<sumSPL; ++j)
                {System.out.print(d[i][j]+"  "); //menampilkan inverse dari matriks
                }System.out.println();}
            //Perkalian antara inverse matriks dengan contant untuk mendapatkan solusi
            double hasil[][] = new double[sumSPL][1];
            for (i = 0; i < sumSPL; i++) 
            {for (int j = 0; j < 1; j++){
                    for (int k = 0; k < sumSPL; k++)
                    {hasil[i][j] = hasil[i][j] + d[i][k] * constants[k][j];}}
            }
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Maka solusi sistem persamaan linear Anda adalah");
            System.out.println("-------------------------------------------------------------------------");
            for(i=0; i<sumSPL; i++)
            {
                System.out.println("X"+(i+1)+" = "+hasil[i][0] + " ");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }
    
    //fungsi inverse matriks 
    public static double[][] invert(double thematriks[][]) 
    {
        int sumSPL = thematriks.length;
        int sumvar = thematriks.length;
        double a[][] = new double[sumSPL][sumvar];
        double b[][] = new double[sumSPL][sumvar];
        int index[] = new int[sumSPL];
        for (int i=0; i<sumSPL; ++i) 
            b[i][i] = 1;
        gaussian(thematriks, index);
        for (int i=0; i<sumSPL-1; ++i)
            for (int j=i+1; j<sumvar; ++j)
                for (int k=0; k<sumSPL; ++k)
                    b[index[j]][k]
                    	    -= thematriks[index[j]][i]*b[index[i]][k];
        for (int i=0; i<sumSPL; ++i) 
        {
            a[sumSPL-1][i] = b[index[sumSPL-1]][i]/thematriks[index[sumSPL-1]][sumvar-1];
            for (int j=sumSPL-2; j>=0; --j) 
            {
                a[j][i] = b[index[j]][i];
                for (int k=j+1; k<sumSPL; ++k) 
                {a[j][i] -= thematriks[index[j]][k]*a[k][i];}
                a[j][i] /= thematriks[index[j]][j];}}
        return a;
    }

    //fungsi eliminasi gauss
    public static void gaussian(double thematriks[][], int index[]) 
    {
        int sumSPL = index.length;
        int sumvar = thematriks.length;
        double c[] = new double[sumSPL];
        // Inisialisasi indeks
        for (int i=0; i<sumSPL; ++i) 
            index[i] = i;
        for (int i=0; i<sumSPL; ++i) {double c1 = 0;
            for (int j=0; j<sumvar; ++j) {double c0 = Math.abs(thematriks[i][j]);if (c0 > c1) c1 = c0;}
            c[i] = c1;}
        int k = 0;
        for (int j=0; j<sumvar-1; ++j) {
            double d1 = 0;
            for (int i=j; i<sumvar; ++i) {
                double d0 = Math.abs(thematriks[index[i]][j]);
                d0 /= c[index[i]];
                if (d0 > d1) 
                {d1 = d0;k = i;}}
            int tmp = index[j];
            index[j] = index[k];
            index[k] = tmp;
            for (int i=j+1; i<sumSPL; ++i) 	
            {
                double e = thematriks[index[i]][j]/thematriks[index[j]][j];
                thematriks[index[i]][j] = e;
                for (int l=j+1; l<sumSPL; ++l)
                    thematriks[index[i]][l] -= e*thematriks[index[j]][l];
            }
        }
    }

    //fungsi mencari determinan matriks
    public static double determinant(double thematriks[][],int sumSPL)
    {
        //membuat variabel det untuk menampung nilai determinan
        double det=0;
        if(sumSPL == 1){det = thematriks[0][0];}// jika matriks hanya memiliki satu element
        //jika matriks 2x2
        else if (sumSPL == 2){det = thematriks[0][0]*thematriks[1][1] - thematriks[1][0]*thematriks[0][1];}
        //jika matriks 3x3 atau lebih
        else
        {det=0;
            for(int j1=0;j1<sumSPL;j1++)
            {
                double[][] newmatriks = new double[sumSPL-1][];
                for(int k=0;k<(sumSPL-1);k++){newmatriks[k] = new double[sumSPL-1];}
                for(int i=1;i<sumSPL;i++){
                    int j2=0;
                    for(int j=0;j<sumSPL;j++){
                        if(j == j1)
                            continue;
                        newmatriks[i-1][j2] = thematriks[i][j];
                        j2++;}}
                det += Math.pow(-1.0,1.0+j1+1.0)* thematriks[0][j1] * determinant(newmatriks,sumSPL-1);}
        }
        return det;
    }

    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                MASUK KE BAGIAN SPL CRAMER
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    public static void mainsplcramer() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: ");
        int baris = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int kolom = scan.nextInt();
        if (kolom != baris) {
            System.out.print("Kaidah Cramer hanya dapat digunakan untuk matriks koefisien berukuran n x n.");
        }
        else {
            double [][] A = new double [baris][kolom];
           double [][] B = new double [baris][1];
           System.out.print("Masukkan matriks koefisien: ");
           masukanMatriks(scan,A,baris,kolom);
           if(determinantDOUBLE(A,baris) != 0) {
               System.out.print("Masukkan matriks hasil: ");
               masukanMatriks(scan,B,baris,1);
               metodeCramer(A,B,baris);
           }
           else {
               System.out.print("Kaidah Cramer tidak dapat digunakan untuk menghitung solusi karena determinan matriks koefisien bernilai nol.");
           }
        }
    }

    public static void masukanMatriks(Scanner scan, double Matrix[][], int baris, int kolom) {
        // Menerima pasangan x, y
        for (int i=0;i<baris;i++) {
            for(int j=0;j<kolom;j++) {
                Matrix[i][j] = scan.nextDouble();
            }
        }
    }

    public static double [][] tempMatriksKonstanta(double Matrix[][], int baris) {
        double [][] temp = new double [baris][baris];
        for (int i=0;i<baris;i++) {
            for (int j=0;j<baris;j++) {
                temp[i][j] = Matrix[i][j];
            }
        }
        return temp;
    }

    public static void metodeCramer(double A[][], double B[][], int baris) {
    double [] det = new double[baris];
    System.out.print("Solusi SPL sebagai berikut" + "\n");
        for (int j=0; j<baris;j++) {
            double [][] temp = new double [baris][baris];
            for (int n=0;n<baris;n++) {
                temp = tempMatriksKonstanta(A,baris);
                for(int i=0;i<baris;i++) {
                    for(j=0;j<baris;j++) {
                        if(j==n) {
                            temp[i][j] = B[i][0];
                        }
                    }
                }
                det[n] = determinantDOUBLE(temp,baris);
            }
        }
        for(int i=0;i<baris;i++) {
            System.out.println("x["+(i+1)+"]=" + (det[i]/determinantDOUBLE(A,baris)));
        }
    }

    public static double [][] Minor(double [][] Matrix, int excluded_baris, int excluded_kolom, int baris) {
        double [][] temp = new double[baris-1][baris-1]; //Matrix minor berukuran baris dan kolom kurang 1 dari aslinya
        int i=0;
        for (int row=0;row<baris;row++) {
        int j = 0;
            for (int col=0;col<baris;col++) {
                if ((row != excluded_baris) && (col != excluded_kolom)) { //Tidak termasuk baris dan kolom tersebut
                    temp[i][j] = Matrix[row][col]; //Isi dengan baris dan kolom matriks
                    j++;
                }
            }
            if (j == baris-1) //Apabila kolom sudah sampai ke N-1, lanjut ke baris selanjutnya
            i++;
            }
        return temp;
    }    
    
    public static double determinantDOUBLE(double Matrix[][], int baris) {
         double D = 0; //Inisialisasi
         if (baris==1) {
             D = Matrix[0][0]; //Elemen satu-satunya
         }
         else if (baris==2) {
             D = Matrix[0][0]*Matrix[1][1] - Matrix[1][0]*Matrix[0][1];
         }
         else {
             int tanda = 1; //Inisialisasi tanda
             for (int i=0; i<baris;i++) {
                 D = D + (tanda*Matrix[i][0]*determinantDOUBLE(Minor(Matrix,i,0,baris),baris-1)); //Matriks dikali determinan minor
                 //Tanda terus berganti
                 tanda = -tanda;
             }
         }
         return D;
       }
}
