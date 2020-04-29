package shoe.store.service;

import java.util.Optional;

import shoe.store.entity.User;

public interface UserService extends BaseService<User, Long> {

	Optional<User> loginByEmail(String email);

}
