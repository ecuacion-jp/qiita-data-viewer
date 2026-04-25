package jp.ecuacion.app.qiitadataviewer.core.model;

import java.util.List;

public class QiitaTag {

  private String name;

  private List<String> versions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getVersions() {
    return versions;
  }

  public void setVersions(List<String> versions) {
    this.versions = versions;
  }
}
