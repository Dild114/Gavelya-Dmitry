package App.Users;

import javax.security.auth.login.AccountNotFoundException;

public interface UserRepository {

  User findByMsisdn(String msisdn);

  void updateUserByMsisdn(String msisdn, User user) throws AccountNotFoundException;
}