package App.EnrichMethods;

import App.Messages.Message;

public interface Enrich {
  EnrichmentType type();

  Message enrich(Message contentForEnrich);
}
