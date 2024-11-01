package test;

import App.EnrichMethods.EnrichByMsisdn;
import App.EnrichMethods.EnrichmentType;
import App.Messages.Message;
import App.Users.User;
import App.Users.dbUsers;
import org.junit.Test;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EnrichByMsisdnTest {

  @Test
  public void testEnrichByMsisdn() throws AccountNotFoundException {
    User testContent = new User();
    testContent.putContent("action", "button_click");
    testContent.putContent("page", "book_card");
    testContent.putContent("MSISDN", "666666666");
    testContent.putContent("first name", "Balba");
    testContent.putContent("second name", "Blablaka");


    ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
    result.put("MSISDN", "666666666");
    result.put("first name", "Balba");
    result.put("second name", "Blablaka");


    dbUsers dbUsers = new dbUsers();
    dbUsers.updateUserByMsisdn("666666666", testContent);

    ConcurrentHashMap<String, String> inval = new ConcurrentHashMap<>();
    inval.put("MSISDN", "666666666");
    Message message = new Message(inval, EnrichmentType.MSISDN);
    EnrichByMsisdn enrichByMsisdn = new EnrichByMsisdn(dbUsers);

    assertEquals(result, enrichByMsisdn.enrich(message).getContent());
  }

  // Если не найдется данных для обогащения, то должно вернуть исходное сообщение
  @Test
  public void testEnrichByMsisdnNotFoundData() throws AccountNotFoundException {
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

  @Test
  public void TestWithException() {
    dbUsers dbUsers = new dbUsers();
    EnrichByMsisdn msisdn = new EnrichByMsisdn(dbUsers);
    assertThrows(IllegalArgumentException.class, () -> msisdn.enrich(null));
  }

}
