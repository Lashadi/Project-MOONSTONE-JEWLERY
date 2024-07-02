package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemTm {
    private String code;
    private String itemName;
    private String category;
    private int qtyOnHand;
    private double price;
}
