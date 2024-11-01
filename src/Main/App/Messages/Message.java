package App.Messages;

import App.EnrichMethods.EnrichmentType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Message {

  private ConcurrentHashMap<String, String> content;
  private EnrichmentType enrichmentType;

  public Message(ConcurrentHashMap<String, String> content, EnrichmentType type) {
    this.content = content;
    this.enrichmentType = type;
  }

  public Map<String, String> put(String key, String value) {
    this.content.put(key, value);
    return content;
  }

  public Map<String, String> getContent() {
    return this.content;
  }

  public EnrichmentType type() {
    return this.enrichmentType;
  }
}