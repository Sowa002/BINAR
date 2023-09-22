package msib.binar.challenge.tiga;

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
