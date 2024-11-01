package App.Users;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {

  Map<String, String> content = new ConcurrentHashMap<>();

  public void putContent(String key, String value) {
    this.content.put(key, value);
  }

  public Map<String, String> getContent() {
    return content;
  }
}
