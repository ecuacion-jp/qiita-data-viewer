package jp.ecuacion.app.qiitadataviewer.base.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jp.ecuacion.lib.core.util.EnumUtil;

import jp.ecuacion.app.qiitadataviewer.base.enums.AccRoleEnum;

@Converter(autoApply = true)
public class AccRoleConverter implements AttributeConverter<AccRoleEnum, String> {

  @Override
  public String convertToDatabaseColumn(AccRoleEnum obj) {
    return (obj == null) ? null : obj.getCode();
  }

  @Override
  public AccRoleEnum convertToEntityAttribute(String obj) {
    // As long as the DB value is valid no issue will occur, so any problem here is a programming bug and an unchecked exception is appropriate.
    return obj == null ? null : EnumUtil.getEnumFromCode(AccRoleEnum.class, obj);
  }
}
