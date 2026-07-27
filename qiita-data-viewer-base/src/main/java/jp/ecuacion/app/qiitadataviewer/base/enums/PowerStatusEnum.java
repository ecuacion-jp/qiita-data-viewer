/*
 * Copyright © 2012 ecuacion.jp (info@ecuacion.jp)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
