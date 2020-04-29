package shoe.store.payload.response;

import lombok.Data;
import shoe.store.entity.User;
import shoe.store.payload.Meta;

import java.util.List;

@Data
public class ListCustomersResponse {
    private Meta meta;
    private List<User> dataCustomer;
}
