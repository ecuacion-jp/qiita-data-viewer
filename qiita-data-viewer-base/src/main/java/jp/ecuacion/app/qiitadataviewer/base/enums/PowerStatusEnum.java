package jp.ecuacion.app.qiitadataviewer.base.enums;

import java.util.Locale;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;

public enum PowerStatusEnum {

  GETTING_READY_TO_START("11"),

  START_IN_PROGRESS("12"),

  START_FAILED("18"),

  STARTED_SUCCESSFULLY("19"),

  BACKUP_BEFORE_STOP_GETTING_READY("71"),

  BACKUP_BEFORE_STOP_IN_PROGRESS("73"),

  BACKUP_BEFORE_STOP_FAILED("78"),

  BACKUP_BEFORE_STOP_SUCCESSFULLY_FINISHED("79"),

  SHUT_DOWN_STARTED("91"),

  SHUT_DOWN_SUCCESS_ACCOMPANYING_TASKS_IN_PROGRESS("92"),

  SHUT_DOWN_FAILURE("96"),

  SHUT_DOWN_SUCCESS_BUT_RELATED_OPERATION_FAILED("97"),

  SHUT_DOWN_MANUALLY("98"),

  SHUT_DOWN_SUCCESSFULLY("99");

  private String code;

  private PowerStatusEnum(String code) {
    this.code = code;
  }

  /**
   * codeを返す。
   * codeがnull, 空文字の場合は、Enum生成時にチェックエラーとなるため考慮不要
   */
  public String getCode() {
    return code;
  }

  /**
   * 画面で表示するための名称を返す。
   * この名称は、getはできるがそれをもとにenumを取得することはできない。
   * localizeされた言語で返す。
   */
  public String getDisplayName(Locale locale) {
    return PropertiesFileUtil.getEnumName(locale, this.getClass().getSimpleName() + "." + this.toString());
  }

  /**
   * defaultのLocaleを使用。
   */
  public String getDisplayName() {
    return PropertiesFileUtil.getEnumName(Locale.getDefault(), this.getClass().getSimpleName() + "." + this.toString());
  }

}
