package jp.ecuacion.app.qiitadataviewer.base.enums;

import java.util.Locale;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;

public enum MailAuthKindEnum {

  ACC_NEW("01"),

  ACC_PASSWORD_RESET("02"),

  ACC_MAIL_ADDRESS_CHANGED("03");

  private String code;

  private MailAuthKindEnum(String code) {
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
