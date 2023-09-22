package msib.binar.challenge.tiga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PesananTest {

    @Test
    public void testHitungSubtotal() {
        MenuMakanan menuMakanan = new MenuMakanan("Nasi Goreng", 25000);
        Pesanan pesanan = new Pesanan(menuMakanan, 3);

        int subtotal = pesanan.hitungSubtotal();

        assertEquals(75000, subtotal);
    }

    @Test
    public void testGetMenuMakanan() {
        MenuMakanan menuMakanan = new MenuMakanan("Mie Goreng", 22000);
        Pesanan pesanan = new Pesanan(menuMakanan, 2);

        MenuMakanan menuDiambil = pesanan.getMenuMakanan();

        assertEquals(menuMakanan, menuDiambil);
    }

    @Test
    public void testGetJumlah() {
        MenuMakanan menuMakanan = new MenuMakanan("Ayam Goreng", 35000);
        Pesanan pesanan = new Pesanan(menuMakanan, 4);

        int jumlah = pesanan.getJumlah();

        assertEquals(4, jumlah);
    }
}

