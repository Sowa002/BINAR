package msib.binar.challenge.tiga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalPesananTest {

    private TotalPesanan totalPesanan;

    @BeforeEach
    public void setUp() {
        totalPesanan = new TotalPesanan();
    }

    @Test
    public void testTambahItem() {
        MenuMakanan menuMakanan = new MenuMakanan("Nasi Goreng", 25000);
        Pesanan pesanan = new Pesanan(menuMakanan, 2);

        totalPesanan.tambahItem(pesanan);

        List<Pesanan> pesananList = totalPesanan.getItems();

        assertEquals(1, pesananList.size());
    }

    @Test
    public void testHitungTotal() {
        MenuMakanan menuMakanan1 = new MenuMakanan("Nasi Goreng", 25000);
        MenuMakanan menuMakanan2 = new MenuMakanan("Mie Goreng", 22000);
        Pesanan pesanan1 = new Pesanan(menuMakanan1, 2);
        Pesanan pesanan2 = new Pesanan(menuMakanan2, 3);

        totalPesanan.tambahItem(pesanan1);
        totalPesanan.tambahItem(pesanan2);

        int totalHarga = totalPesanan.hitungTotal();

        assertEquals(116000, totalHarga);
    }
}

