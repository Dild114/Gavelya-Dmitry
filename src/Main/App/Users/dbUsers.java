package App.Users;

import javax.security.auth.login.AccountNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

public class dbUsers implements UserRepository {
  private ConcurrentHashMap<String, User> user = new ConcurrentHashMap<>();

  @Override
  public User findByMsisdn(String msisdn) {
    if (msisdn == null) {
      throw new IllegalArgumentException();
    }
    return user.get(msisdn);
  }

  @Override
  public synchronized void updateUserByMsisdn(String msisdn, User user) throws AccountNotFoundException {
    if (msisdn.isEmpty() || user == null || msisdn == null) {
      throw new AccountNotFoundException();
    }
    this.user.put(msisdn, user);
  }
}
