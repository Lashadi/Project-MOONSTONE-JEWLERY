package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String password;

    public UserDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
