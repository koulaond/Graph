package repository.api.definitions;

import repository.api.users.User;

import java.util.Date;

public class GraphMetadata {
  private Date created;
  private Date lastModified;
  private User createdBy;

  public GraphMetadata(Date created, Date lastModified, User createdBy) {
    this.created = created;
    this.lastModified = lastModified;
    this.createdBy = createdBy;
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public User getCreatedBy() {
    return createdBy;
  }
}
