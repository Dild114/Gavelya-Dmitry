package App.EnrichMethods;

/*
  Обогащение по MSISDN
 */

import App.Messages.Message;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnrichByMsisdn implements Enrich {

  private ConcurrentHashMap<EnrichmentType, Map<String, String>> concurrentHashMap = new ConcurrentHashMap<>();
  private App.Users.dbUsers dbUsers;

  public EnrichByMsisdn(App.Users.dbUsers db) {
    this.dbUsers = db;
  }
  @Override
  public EnrichmentType type() {
    return EnrichmentType.MSISDN;
  }


  @Override
  public Message enrich(Message contentForEnrich) {
    if (contentForEnrich == null) {
      throw new IllegalArgumentException();
    }

    Map<String, String> content = contentForEnrich.getContent();
    String type = String.valueOf(contentForEnrich.type());

    // В базе данных пользователей находит поле first name и second name и обогащает сообщение
    contentForEnrich.put("first name", dbUsers.findByMsisdn(content.get(type)).getContent().get("first name"));
    contentForEnrich.put("second name", dbUsers.findByMsisdn(content.get(type)).getContent().get("second name"));
    return contentForEnrich;
  }
}
