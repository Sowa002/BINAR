package msib.binar.challenge.tiga;

import java.util.ArrayList;
import java.util.List;

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
