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
    // DBの値が正しい限り問題は発生しないので、もし問題が起こればプログラムの問題であることから非チェック例外とする
    return obj == null ? null : EnumUtil.getEnumFromCode(WebProtocolEnum.class, obj);
  }
}
