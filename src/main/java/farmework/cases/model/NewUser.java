package farmework.cases.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NewUser {
    private String userId;
    private String userName;
    private String address;

    public NewUser(String userId) {
        this.userId = userId;
    }
}
