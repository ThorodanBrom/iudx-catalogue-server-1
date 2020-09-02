package iudx.catalogue.server.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Constants {
  public static final String REQUEST_GET = "GET";
  public static final String REQUEST_POST = "POST";
  public static final String CAT_TEST_SEARCH_INDEX = "testindex";
  public static final String CAT_INDEX_NAME = CAT_TEST_SEARCH_INDEX;
  public static final String SOURCE = "_source";
  public static final String QUERY_KEY = "query";
  public static final String TERM = "term";
  public static final String ID_KEYWORD = "id.keyword";
  public static final String HITS = "hits";
  static final String TOTAL_HITS = "totalHits";
  public static final String TOTAL = "total";
  public static final String VALUE = "value";
  public static final String CAT_DOC = "cat/_doc";

  /** Item types */
  public static final String ITEM_TYPE_RESOURCE = "iudx:Resource";
  public static final String ITEM_TYPE_RESOURCE_GROUP = "iudx:ResourceGroup";
  public static final String ITEM_TYPE_RESOURCE_SERVER = "iudx:ResourceServer";
  public static final String ITEM_TYPE_PROVIDER = "iudx:Provider";
  public static final ArrayList<String> ITEM_TYPES 
    = new ArrayList<String>(Arrays.asList(ITEM_TYPE_RESOURCE, ITEM_TYPE_RESOURCE_GROUP,
                                          ITEM_TYPE_RESOURCE_SERVER, ITEM_TYPE_PROVIDER));

  public static final String RESOURCE_GROUP_KEY = "resourceGroup";
  public static final String RESOURCE_SERVER_KEY = "resourceServer";
  public static final String PROVIDER_KEY = "provider";
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String TYPE = "type";
  public static final String STATUS = "status";
  public static final String SUCCESS = "success";
  public static final String FAILED = "failed";
  public static final String ITEM_STATUS = "itemStatus";
  public static final String ACTIVE = "ACTIVE";
  public static final String ITEM_CREATED_AT = "itemCreatedAt";
  public static final String CONFIG_FILE = "config.properties";
  public static final String DATABASE_IP = "databaseIP";
  public static final String DATABASE_PORT = "databasePort";

  static final String FILTER_PATH = "?filter_path=took,hits.total.value,hits.hits._source";
  public static final String NON_EXISTING_LINK_MSG = "No such cat item exists: ";
  public static final String VALIDATION_FAILURE_MSG = "Validation failed";
  public static final String INVALID_SCHEMA_MSG = "Invalid Schema";
}
