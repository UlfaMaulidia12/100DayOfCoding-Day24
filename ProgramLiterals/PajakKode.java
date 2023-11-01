package ProgramLiterals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class PajakKode {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char ulangi;

        do {
            System.out.println("=== Kalkulator Kode Pajak ===");
            
            // Meminta pengguna untuk memasukkan jumlah pajak yang ingin dihitung (1 hingga 10)
            int jumlahPajak = getInput(input, "Masukkan jumlah pajak\t\t\t\t: ", 1, 10);
            Pajak[] pajakList = new Pajak[jumlahPajak];

            // Meminta pengguna untuk memasukkan data pajak dan membuat objek Pajak
            for (int i = 0; i < jumlahPajak; i++) {
                pajakList[i] = getInputAndCreatePajak(input, "Masukkan Pajak " + (i + 1));
            }

            System.out.println("\n=== Hasil Perhitungan Pajak ===");

            // Menampilkan data dan hasil perhitungan pajak untuk setiap objek Pajak
            for (Pajak pajak : pajakList) {
                displayPajak(pajak);
                calculateAndDisplayTax(pajak);
            }

            ulangi = getYesNoInput(input, "Hitung pajak lagi? (Y/N)\t: ");
        } while (ulangi == 'Y' || ulangi == 'y');

        input.close();
        System.out.println("Terima kasih telah menggunakan kalkulator pajak!");
    }

    // Fungsi untuk meminta input angka dari pengguna dengan rentang tertentu
    private static int getInput(Scanner input, String message, int min, int max) {
        int value;

        while (true) {
            try {
                System.out.print(message);
                value = input.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Masukkan angka antara " + min + " dan " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka yang valid.");
                input.next(); // Hapus input yang tidak valid.
            }
        }
    }

    // Fungsi untuk meminta input dan membuat objek Pajak
    private static Pajak getInputAndCreatePajak(Scanner input, String message) {
        int kodePajak = getInput(input, message + " - Masukkan Kode Pajak (1-999)\t: ", 1, 999);
        int persentasePajak = getInput(input, message + " - Masukkan Persentase Pajak (%)\t: ", 0, 100);
        return new Pajak(kodePajak, persentasePajak);
    }

    // Fungsi untuk menampilkan data Pajak
    private static void displayPajak(Pajak pajak) {
        System.out.println("Kode Pajak\t\t: " + pajak.getKodePajak());
        System.out.println("Persentase Pajak\t\t: " + pajak.getPersentasePajak() + "%");
    }

    // Fungsi untuk menghitung dan menampilkan pajak yang harus dibayar
    private static void calculateAndDisplayTax(Pajak pajak) {
        double calculatedTax = pajak.calculatePajak();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println("Pajak yang harus dibayar\t: $" + decimalFormat.format(calculatedTax));
    }

    // Fungsi untuk meminta input 'Y' atau 'N' dari pengguna
    private static char getYesNoInput(Scanner input, String message) {
        char response;

        while (true) {
            System.out.print(message);
            response = input.next().charAt(0);
            if (response == 'Y' || response == 'y' || response == 'N' || response == 'n') {
                return response;
            } else {
                System.out.println("Pilihan tidak valid. Harap masukkan 'Y' atau 'N'.");
            }
        }
    }
}

// Kelas Pajak untuk merepresentasikan data pajak
class Pajak {
    private int kodePajak;
    private int persentasePajak;

    public Pajak(int kodePajak, int persentasePajak) {
        this.kodePajak = kodePajak;
        this.persentasePajak = persentasePajak;
    }

    public int getKodePajak() {
        return kodePajak;
    }

    public int getPersentasePajak() {
        return persentasePajak;
    }

    // Fungsi untuk menghitung jumlah pajak berdasarkan persentase pajak
    public double calculatePajak() {
        return (double) kodePajak * persentasePajak / 100;
    }
}
