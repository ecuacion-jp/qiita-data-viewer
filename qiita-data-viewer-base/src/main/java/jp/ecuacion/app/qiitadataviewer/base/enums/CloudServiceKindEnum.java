package jp.ecuacion.app.qiitadataviewer.base.enums;

import java.util.Locale;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;

public enum CloudServiceKindEnum {

  AWS("01"),

  WEB_ARENA_INDIGO("11"),

  SAKURA_CLOUD("21");

  private String code;

  private CloudServiceKindEnum(String code) {
    this.code = code;
  }

  /**
   * Returns the code.
   * No need to handle null or empty code as a validation error is raised when the Enum is generated.
   */
  public String getCode() {
    return code;
  }

  /**
   * Returns the display name for use in the UI.
   * This name can be retrieved but cannot be used to look up the enum.
   * Returns in the localized language.
   */
  public String getDisplayName(Locale locale) {
    return PropertiesFileUtil.getEnumName(locale, this.getClass().getSimpleName() + "." + this.toString());
  }

  /**
   * Uses the default Locale.
   */
  public String getDisplayName() {
    return PropertiesFileUtil.getEnumName(Locale.getDefault(), this.getClass().getSimpleName() + "." + this.toString());
  }

}
