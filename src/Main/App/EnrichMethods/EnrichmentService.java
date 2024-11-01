package App.EnrichMethods;

import App.Messages.Message;
import java.util.List;

public class EnrichmentService {
  private List<Enrich> enrichments;
  public EnrichmentService(List<Enrich> enrichs) {
    this.enrichments = enrichs;
  }

  // возвращается обогащенный (или необогащенный content сообщения)
  public Message enrich(Message message) {
    if (message == null) {
      throw new NullPointerException("message is null");
    }
    try {
      for (Enrich enr : enrichments) {
        if (message.type().equals(enr.type())) {
          enr.enrich(message);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return message;
  }
}
