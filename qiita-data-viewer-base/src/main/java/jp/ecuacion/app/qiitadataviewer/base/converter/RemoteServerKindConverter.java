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
package jp.ecuacion.app.qiitadataviewer.base.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jp.ecuacion.lib.core.util.EnumUtil;

import jp.ecuacion.app.qiitadataviewer.base.enums.RemoteServerKindEnum;

@Converter(autoApply = true)
public class RemoteServerKindConverter implements AttributeConverter<RemoteServerKindEnum, String> {

  @Override
  public String convertToDatabaseColumn(RemoteServerKindEnum obj) {
    return (obj == null) ? null : obj.getCode();
  }

  @Override
  public RemoteServerKindEnum convertToEntityAttribute(String obj) {
    // As long as the DB value is valid no issue will occur, so any problem here is a programming bug and an unchecked exception is appropriate.
    return obj == null ? null : EnumUtil.getEnumFromCode(RemoteServerKindEnum.class, obj);
  }
}
