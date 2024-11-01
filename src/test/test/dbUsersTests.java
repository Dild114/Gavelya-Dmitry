package test;

import App.Users.User;
import App.Users.dbUsers;
import org.junit.Test;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class dbUsersTests {

  @Test
  public void testDbUsers() throws AccountNotFoundException {
    User testContent = new User();
    testContent.putContent("action", "button_click");
    testContent.putContent("page", "book_card");
    testContent.putContent("MSISDN", "666666666");
    testContent.putContent("first name", "Balba");
    testContent.putContent("second name", "Blablaka");

    dbUsers dbUsers = new dbUsers();
    dbUsers.updateUserByMsisdn("666666666", testContent);

    Map<String, User> content = new ConcurrentHashMap<>();
    User content2 = new User();
    content2.putContent("action", "button_click");
    content2.putContent("page", "book_card");
    content2.putContent("MSISDN", "666666666");
    content2.putContent("first name", "Balba");
    content2.putContent("second name", "Blablaka");
    content.put("666666666", content2);

    assertEquals(content2.getContent(), dbUsers.findByMsisdn("666666666").getContent());
  }

  // Если не найдется данных для обогащения, то должно вернуть исходное сообщение
  @Test
  public void testEnrichByMsisdn2() throws AccountNotFoundException {
    User testContent = new User();
    testContent.putContent("action", "button_click");
    testContent.putContent("page", "book_card");
    testContent.putContent("MSISDN", "666666666");


    dbUsers dbUsers = new dbUsers();
    dbUsers.updateUserByMsisdn("666666666", testContent);

    Map<String, User> content = new ConcurrentHashMap<>();
    User content2 = new User();
    content2.putContent("action", "button_click");
    content2.putContent("page", "book_card");
    content2.putContent("MSISDN", "666666666");
    content.put("666666666", content2);

    assertEquals(content2.getContent(), dbUsers.findByMsisdn("666666666").getContent());
  }
}
