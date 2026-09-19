package jp.ecuacion.app.qiitadataviewer.base.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jp.ecuacion.lib.core.util.EnumUtil;

import jp.ecuacion.app.qiitadataviewer.base.enums.MailAuthKindEnum;

@Converter(autoApply = true)
public class MailAuthKindConverter implements AttributeConverter<MailAuthKindEnum, String> {

  @Override
  public String convertToDatabaseColumn(MailAuthKindEnum obj) {
    return (obj == null) ? null : obj.getCode();
  }

  @Override
  public MailAuthKindEnum convertToEntityAttribute(String obj) {
    // As long as the DB value is valid no issue will occur, so any problem here is a programming bug and an unchecked exception is appropriate.
    return obj == null ? null : EnumUtil.getEnumFromCode(MailAuthKindEnum.class, obj);
  }
}
