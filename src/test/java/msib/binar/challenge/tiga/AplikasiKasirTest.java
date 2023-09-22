package msib.binar.challenge.tiga;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AplikasiKasirTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private TotalPesanan totalPesanan;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    public void setUp() {
        totalPesanan = new TotalPesanan();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(System.in);
    }

    @Test
    public void testLoadMenu() {
        String menuData = "Nasi Goreng | 25000\nMie Goreng | 22000\nAyam Goreng | 35000\n";
        createMenuFile(menuData);

        List<MenuMakanan> menuList = AplikasiKasir.loadMenu();

        assertEquals(3, menuList.size());
        assertEquals("Nasi Goreng", menuList.get(0).getNama());
        assertEquals(25000, menuList.get(0).getHarga());
        assertEquals("Mie Goreng", menuList.get(1).getNama());
        assertEquals(22000, menuList.get(1).getHarga());
        assertEquals("Ayam Goreng", menuList.get(2).getNama());
        assertEquals(35000, menuList.get(2).getHarga());
    }

    @Test
    public void testPesanMakanan() {
        String menuData = "Nasi Goreng | 25000\nMie Goreng | 22000\nAyam Goreng | 35000\n";
        createMenuFile(menuData);

        AplikasiKasir.menu = AplikasiKasir.loadMenu();

        String input = "1\n2\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        Scanner scanner = new Scanner(in);

        AplikasiKasir.pesanMakanan(totalPesanan, scanner);

        List<Pesanan> pesananList = totalPesanan.getItems();
        assertEquals(1, pesananList.size());
    }



    @Test
    public void testGetJumlahValidInput() {
        String input = "5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int jumlah = AplikasiKasir.getJumlah(new Scanner(System.in));
        assertEquals(5, jumlah);
    }

    @Test
    public void testGetJumlahInvalidInput() {
        String input = "0\n-3\nabc\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int jumlah = AplikasiKasir.getJumlah(new Scanner(System.in));
        assertEquals(5, jumlah); // Jumlah yang valid adalah 5
    }

    @Test
    public void testSimpanStruk() {
        MenuMakanan menuMakanan1 = new MenuMakanan("Nasi Goreng", 25000);
        Pesanan pesanan1 = new Pesanan(menuMakanan1, 2);
        totalPesanan.tambahItem(pesanan1);

        MenuMakanan menuMakanan2 = new MenuMakanan("Mie Goreng", 22000);
        Pesanan pesanan2 = new Pesanan(menuMakanan2, 3);
        totalPesanan.tambahItem(pesanan2);

        AplikasiKasir.simpanStruk(totalPesanan);

        String expectedOutput = "Struk Pembayaran:\r\n" +
                "==================\r\n" +
                "Nasi Goreng x2 = Rp 50000\r\n" +
                "Mie Goreng x3 = Rp 66000\r\n" +
                "==================\r\n" +
                "Total Harga: Rp 116000\r\n" +
                "Struk pembayaran telah disimpan.";
        String actualOutput = outContent.toString().trim();

        assertEquals(expectedOutput, actualOutput);
    }

    private void createMenuFile(String menuData) {
        try (PrintWriter writer = new PrintWriter(AplikasiKasir.FILE_MENU)) {
            writer.write(menuData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
