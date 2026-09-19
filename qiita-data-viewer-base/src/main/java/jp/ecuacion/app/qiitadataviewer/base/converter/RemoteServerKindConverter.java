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
