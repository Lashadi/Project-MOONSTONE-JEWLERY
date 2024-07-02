package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTm {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String tel;
}
