package App;

import App.EnrichMethods.EnrichByMsisdn;
import App.EnrichMethods.EnrichmentService;
import App.EnrichMethods.EnrichmentType;
import App.Users.User;
import App.Users.dbUsers;
import App.Messages.Message;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
  public static void main(String[] args) throws Exception {
    User content = new User();
    content.putContent("action", "button_click");
    content.putContent("page", "book_card");
    content.putContent("MSISDN", "666666666");
    content.putContent("first name", "Balba");
    content.putContent("second name", "Blablaka");

    dbUsers dbUsers = new dbUsers();
    dbUsers.updateUserByMsisdn("666666666", content);

    ConcurrentHashMap<String, String> inval = new ConcurrentHashMap<>();
    inval.put("MSISDN", "666666666");
    Message message = new Message(inval, EnrichmentType.MSISDN);
    EnrichmentService enrichmentService = new EnrichmentService(List.of(new EnrichByMsisdn(dbUsers)));
    Message enrichMessage = enrichmentService.enrich(message);
    for (var i : enrichMessage.getContent().entrySet()) {
      System.out.println(i.getKey() + " " + i.getValue());
    }
  }
}
