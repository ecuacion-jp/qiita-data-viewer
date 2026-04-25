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
    // DBの値が正しい限り問題は発生しないので、もし問題が起こればプログラムの問題であることから非チェック例外とする
    return obj == null ? null : EnumUtil.getEnumFromCode(AccRoleEnum.class, obj);
  }
}
