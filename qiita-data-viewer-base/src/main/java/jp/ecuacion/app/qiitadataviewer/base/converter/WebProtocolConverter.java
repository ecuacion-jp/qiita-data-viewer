package jp.ecuacion.app.qiitadataviewer.base.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jp.ecuacion.lib.core.util.EnumUtil;

import jp.ecuacion.app.qiitadataviewer.base.enums.WebProtocolEnum;

@Converter(autoApply = true)
public class WebProtocolConverter implements AttributeConverter<WebProtocolEnum, String> {

  @Override
  public String convertToDatabaseColumn(WebProtocolEnum obj) {
    return (obj == null) ? null : obj.getCode();
  }

  @Override
  public WebProtocolEnum convertToEntityAttribute(String obj) {
    // As long as the DB value is valid no issue will occur, so any problem here is a programming bug and an unchecked exception is appropriate.
    return obj == null ? null : EnumUtil.getEnumFromCode(WebProtocolEnum.class, obj);
  }
}
