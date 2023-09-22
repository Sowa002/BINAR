package msib.binar.challenge.tiga;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AplikasiKasir {

    static final String FILE_MENU = "menu.txt";
    static final String FILE_STRUK = "struk_pembayaran.txt";

    static List<MenuMakanan> menu;

    public static void main(String[] args) {
        menu = loadMenu();

        Scanner scanner = new Scanner(System.in);
        TotalPesanan totalPesanan = new TotalPesanan();

        while (true) {
            tampilkanMenuUtama();

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tampilkanMenu();
                    break;
                case "2":
                    pesanMakanan(totalPesanan, scanner);
                    break;
                case "3":
                    konfirmasiPesanan(totalPesanan, scanner);
                    break;
                case "4":
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("============================");
        System.out.println("Selamat datang di BINAR FOOD");
        System.out.println("============================");
        System.out.println("\nSilahkan pilih menu di bawah ini");
        System.out.println("\n1. Lihat Menu Makanan");
        System.out.println("2. Pesan Makanan");
        System.out.println("3. Konfirmasi Pesanan");
        System.out.println("4. Keluar");
        System.out.print("\nPilih menu (1-4): ");
    }

    private static Optional<MenuMakanan> bacaMenu(String baris) {
        String[] bagian = baris.split("\\|");
        if (bagian.length == 2) {
            String nama = bagian[0].trim();
            int harga = Integer.parseInt(bagian[1].trim());
            return Optional.of(new MenuMakanan(nama, harga));
        } else {
            return Optional.empty();
        }
    }

    static List<MenuMakanan> loadMenu() {
        List<MenuMakanan> daftarMenu = new ArrayList<>();
        try (BufferedReader pembaca = new BufferedReader(new FileReader(FILE_MENU))) {
            String baris;
            while ((baris = pembaca.readLine()) != null) {
                bacaMenu(baris).ifPresent(daftarMenu::add);
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca menu.");
        }
        return daftarMenu;
    }

    private static void tampilkanMenu() {
        System.out.println("Menu Makanan:");
        IntStream.range(0, menu.size())
                .mapToObj(index -> {
                    MenuMakanan menuMakanan = menu.get(index);
                    return (index + 1) + ". " + menuMakanan.getNama() + " | Rp " + menuMakanan.getHarga();
                })
                .forEach(System.out::println);
    }




    static void pesanMakanan(TotalPesanan totalPesanan, Scanner scanner) {
        tampilkanMenu();
        System.out.print("Pilih nomor makanan (1-" + menu.size() + ") atau 0 untuk selesai: ");
        String pilihan = scanner.nextLine();
        if (pilihan.equals("0")) {
            return;
        }
        try {
            int nomorItem = Integer.parseInt(pilihan);
            if (nomorItem >= 1 && nomorItem <= menu.size()) {
                int jumlah = getJumlah(scanner);
                MenuMakanan menuMakanan = menu.get(nomorItem - 1);
                Pesanan pesanan = new Pesanan(menuMakanan, jumlah);
                totalPesanan.tambahItem(pesanan);
                System.out.println("Pesanan berhasil ditambahkan.");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Pilihan tidak valid.");
        }
    }

    static int getJumlah(Scanner scanner) {
        int jumlah;
        while (true) {
            System.out.print("Masukkan jumlah pesanan: ");
            try {
                jumlah = Integer.parseInt(scanner.nextLine());
                if (jumlah > 0) {
                    return jumlah;
                } else {
                    System.out.println("Jumlah pesanan harus lebih dari 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan jumlah pesanan yang valid.");
            }
        }
    }

    private static void konfirmasiPesanan(TotalPesanan totalPesanan, Scanner scanner) {
        tampilkanStruk(totalPesanan);
        System.out.print("Apakah Anda ingin mengkonfirmasi pesanan? (y/n): ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("y")) {
            simpanStruk(totalPesanan);
            System.out.println("Pesanan Anda telah dikonfirmasi. Terima kasih!");
            totalPesanan.getItems().clear();
        } else {
            System.out.println("Pesanan Anda telah dibatalkan.");
        }
    }

    private static void tampilkanStruk(TotalPesanan totalPesanan) {
        System.out.println("\nStruk Pembayaran:");
        System.out.println("==================");
        totalPesanan.getItems().stream()
                .map((Pesanan pesanan) -> {
                    MenuMakanan menuMakanan = pesanan.getMenuMakanan();
                    int jumlah = pesanan.getJumlah();
                    int subtotal = pesanan.hitungSubtotal();
                    return menuMakanan.getNama() + " x" + jumlah + " = Rp " + subtotal;
                })
                .forEach(System.out::println);
        System.out.println("==================");
        System.out.println("Total Harga: Rp " + totalPesanan.hitungTotal());
    }


    static void simpanStruk(TotalPesanan totalPesanan) {
        try (FileWriter penulis = new FileWriter(FILE_STRUK)) {
            penulis.write("Struk Pembayaran:\n");
            penulis.write("==================\n");
            for (Pesanan pesanan : totalPesanan.getItems()) {
                MenuMakanan menuMakanan = pesanan.getMenuMakanan();
                int jumlah = pesanan.getJumlah();
                int subtotal = pesanan.hitungSubtotal();
                penulis.write(menuMakanan.getNama() + " x" + jumlah + " = Rp " + subtotal + "\n");
            }
            penulis.write("==================\n");
            penulis.write("Total Harga: Rp " + totalPesanan.hitungTotal() + "\n");
            tampilkanStruk(totalPesanan);
            System.out.println("Struk pembayaran telah disimpan.");
        } catch (IOException e) {
            System.err.println("Gagal menyimpan struk.");
        }
    }
}
