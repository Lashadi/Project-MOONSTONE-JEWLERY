package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private  String id;
    private  String name;
    private String address;
    private String tel;
    private String email;
    private String userId;
}

