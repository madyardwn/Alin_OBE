/*
Achmadya Ridwan Ilyawan (211511001)
Ari Maulana Hardan (211511007)
Meisya Puteri Ghefira (211511021)
Naufal Salman Mulyadi (211511028)
*/

import java.util.Scanner;

public class main extends functions_procedures {
  public static void PrintSubmenuSPL() {
    System.out.println("");
    System.out.println("*******************************");
    System.out.println("             METODE SPL");
    System.out.println("*******************************");
    System.out.println("1. Metode Eliminasi Gauss");
    System.out.println("2. Metode Eliminasi Gauss-Jordan");
    System.out.println("3. Exit");

    System.out.print("Pilih metode: ");
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    while (true) {
      // Clear Screen
      PrintSubmenuSPL();
      int menu = scan.nextInt();
      switch (menu) {
      case 1:
        System.out.println("");
        System.out.println("*******************************");
        System.out.println("MENYELESAIKAN SPL DENGAN METODE GAUSS");
        System.out.println("*******************************");
        mainsplgauss();
        break;
      case 2:
        System.out.println("");
        System.out.println("*******************************");
        System.out.println("MENYELESAIKAN SPL DENGAN METODE GAUSS-JORDAN");
        System.out.println("*******************************");
        mainsplgaussjordan();
        break;
      case 3:
        System.out.println("Terima kasih telah menggunakan program ini");
        return;
      default:
        System.out.println("Pilihan tidak tersedia");
        break;
      }
    }
  }
}
