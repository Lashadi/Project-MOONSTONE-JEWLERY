package lk.ijse.pos.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String code;
    private String itemName;
    private String category;
    private int qty;
    private double price;
    private Date date;
}


