import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuMakanan {
    private String nama;
    private int harga;

    public MenuMakanan(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
}

class Pesanan {
    private MenuMakanan menuMakanan;
    private int jumlah;

    public Pesanan(MenuMakanan menuMakanan, int jumlah) {
        this.menuMakanan = menuMakanan;
        this.jumlah = jumlah;
    }

    public MenuMakanan getMenuMakanan() {
        return menuMakanan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int hitungSubtotal() {
        return menuMakanan.getHarga() * jumlah;
    }
}

class TotalPesanan {
    private List<Pesanan> items;

    public TotalPesanan() {
        items = new ArrayList<>();
    }

    public void tambahItem(Pesanan item) {
        items.add(item);
    }

    public List<Pesanan> getItems() {
        return items;
    }

    public int hitungTotal() {
        int total = 0;
        for (Pesanan item : items) {
            total += item.hitungSubtotal();
        }
        return total;
    }
}

public class AplikasiKasir {

    private static final String FILE_MENU = "menu.txt";
    private static final String FILE_STRUK = "struk_pembayaran.txt";

    private static List<MenuMakanan> menu;

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

    private static List<MenuMakanan> loadMenu() {
        List<MenuMakanan> daftarMenu = new ArrayList<>();
        try (BufferedReader pembaca = new BufferedReader(new FileReader(FILE_MENU))) {
            String baris;
            while ((baris = pembaca.readLine()) != null) {
                String[] bagian = baris.split("\\|");
                if (bagian.length == 2) {
                    String nama = bagian[0].trim();
                    int harga = Integer.parseInt(bagian[1].trim());
                    daftarMenu.add(new MenuMakanan(nama, harga));
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca menu.");
        }
        return daftarMenu;
    }

    private static void tampilkanMenu() {
        System.out.println("Menu Makanan:");
        for (int i = 0; i < menu.size(); i++) {
            MenuMakanan menuMakanan = menu.get(i);
            System.out.println((i + 1) + ". " + menuMakanan.getNama() + " | Rp " + menuMakanan.getHarga());
        }
    }

    private static void pesanMakanan(TotalPesanan totalPesanan, Scanner scanner) {
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

    private static int getJumlah(Scanner scanner) {
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
        for (int i = 0; i < totalPesanan.getItems().size(); i++) {
            Pesanan pesanan = totalPesanan.getItems().get(i);
            MenuMakanan menuMakanan = pesanan.getMenuMakanan();
            int jumlah = pesanan.getJumlah();
            int subtotal = pesanan.hitungSubtotal();
            System.out.println(menuMakanan.getNama() + " x" + jumlah + " = Rp " + subtotal);
        }
        System.out.println("==================");
        System.out.println("Total Harga: Rp " + totalPesanan.hitungTotal());
    }

    private static void simpanStruk(TotalPesanan totalPesanan) {
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
            System.out.println("Struk pembayaran telah disimpan.");
        } catch (IOException e) {
            System.err.println("Gagal menyimpan struk.");
        }
    }
}
