package test;

import App.EnrichMethods.EnrichByMsisdn;
import App.EnrichMethods.EnrichmentService;
import App.EnrichMethods.EnrichmentType;
import App.Messages.Message;
import App.Users.User;
import App.Users.dbUsers;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

  @Test
  public void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws Exception {
    User content = new User();
    content.putContent("action", "button_click");
    content.putContent("page", "book_card");
    content.putContent("MSISDN", "666666666");
    content.putContent("first name", "Dmitry");
    content.putContent("second name", "Gavelya");

    dbUsers dbUsers = new dbUsers();
    dbUsers.updateUserByMsisdn("666666666", content);

    ConcurrentHashMap<String, String> inval = new ConcurrentHashMap<>();
    inval.put("MSISDN", "666666666");
    Message message = new Message(inval, EnrichmentType.MSISDN);
    EnrichmentService enrichmentService = new EnrichmentService(List.of(new EnrichByMsisdn(dbUsers)));
    //Message result = enrichmentService.enrich(message);

    List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);
    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
          enrichmentResults.add(
              enrichmentService.enrich(message) // message где-то создается
          );
        latch.countDown();     // уменьшаем значение latch на 1
      });
    }
    latch.await(); // ждем, пока latch не станет равным 0, то есть пока не закончат работу все джобы в цикле

    // проверяем валидность полученных сообщений в enrichmentResult
    for (var i : enrichmentResults) {
      assertEquals("Dmitry", i.getContent().get("first name"));
      assertEquals("Gavelya", i.getContent().get("second name"));
      assertEquals("666666666", i.getContent().get("MSISDN"));
    }
    executorService.shutdown();
  }
}