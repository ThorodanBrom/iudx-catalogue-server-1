package iudx.catalogue.server.database;

import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.reactivex.core.Vertx;
import iudx.catalogue.server.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static iudx.catalogue.server.Constants.*;

@ExtendWith(VertxExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ElasticClientTest {
  private static final Logger LOGGER = LogManager.getLogger(ElasticClientTest.class);
  private static ElasticClient client;
  private static String databaseIP;
  private static int databasePort;
  private static String databaseUser;
  private static String databasePassword;
  private static Configuration config;

  @BeforeAll
  @DisplayName("")
  static void initClient(Vertx vertx, VertxTestContext testContext) {
    /* Read the configuration and set the rabbitMQ server properties. */

    config = new Configuration();
    JsonObject elasticConfig = config.configLoader(0, vertx);

    databaseIP = elasticConfig.getString(DATABASE_IP);
    databasePort = elasticConfig.getInteger(DATABASE_PORT);
    databaseUser = elasticConfig.getString(DATABASE_UNAME);
    databasePassword = elasticConfig.getString(DATABASE_PASSWD);

    client = new ElasticClient(databaseIP, databasePort, databaseUser, databasePassword);

    LOGGER.info("Read config file");
    LOGGER.info("IP is " + databaseIP);

    testContext.completeNow();
  }

  @Test
  @Order(1)
  @DisplayName("Test Get all")
  void TestGetAll(VertxTestContext testContext) {
    JsonObject query = new JsonObject().put("query", new JsonObject()
                                        .put("match_all", new JsonObject()));
    client.searchAsync("cat", query.toString(), res -> {
      if (res.succeeded()) {
        LOGGER.info("Succeeded");
        LOGGER.info(res.result());
        LOGGER.info("Computed size = " + res.result().getJsonArray("results").size());
        testContext.completeNow();
      } else {
        LOGGER.info("Failed");
        LOGGER.info(res.cause());
        testContext.failed();
      }
    });
  }

  @Test
  @Order(2)
  @DisplayName("Test Get aggregations")
  void TestGetAggregations(VertxTestContext testContext) {

    LOGGER.info("Reached get aggregations");

    String req = "{\"query\":{\"bool\":{\"filter\":[{\"match\":{\"type\":\"iudx:ResourceGroup\"}}]}},\"aggs\":{\"results\":{\"terms\":{\"field\":\"id.keyword\",\"size\":10000}}}}";
    LOGGER.info("Aggregation query is " + req);
    client.listAggregationAsync("testindex", req, res -> {
      if (res.succeeded()) {
        LOGGER.info("Succeeded");
        LOGGER.info(res.result());
        LOGGER.info("Computed size = " + res.result().getJsonArray("results").size());
        testContext.completeNow();
      } else {
        LOGGER.info("Failed");
        LOGGER.info(res.cause());
        testContext.failed();
      }
    });
  }

}
