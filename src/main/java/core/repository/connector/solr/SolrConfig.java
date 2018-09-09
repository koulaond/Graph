package core.repository.connector.solr;

public class SolrConfig {
  private String rootUrl;
  private String core;
  private int connectionTimeout;
  private int socketTimeout;

  public SolrConfig(String rootUrl, String core, int connectionTimeout, int socketTimeout) {
    this.rootUrl = rootUrl;
    this.core = core;
    this.connectionTimeout = connectionTimeout;
    this.socketTimeout = socketTimeout;
  }

  public String getRootUrl() {
    return rootUrl;
  }

  public String getCore() {
    return core;
  }

  public int getConnectionTimeout() {
    return connectionTimeout;
  }

  public int getSocketTimeout() {
    return socketTimeout;
  }
}
