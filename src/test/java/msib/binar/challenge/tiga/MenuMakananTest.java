package msib.binar.challenge.tiga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuMakananTest {

    @Test
    public void testGetNama() {
        String namaMakanan = "Nasi Goreng";
        int hargaMakanan = 25000;
        MenuMakanan menuMakanan = new MenuMakanan(namaMakanan, hargaMakanan);

        String namaDariMenu = menuMakanan.getNama();

        assertEquals(namaMakanan, namaDariMenu);
    }

    @Test
    public void testGetHarga() {
        String namaMakanan = "Mie Goreng";
        int hargaMakanan = 22000;
        MenuMakanan menuMakanan = new MenuMakanan(namaMakanan, hargaMakanan);

        int hargaDariMenu = menuMakanan.getHarga();

        assertEquals(hargaMakanan, hargaDariMenu);
    }
}
