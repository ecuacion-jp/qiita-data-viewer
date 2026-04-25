package jp.ecuacion.app.qiitadataviewer.base.util;

import jp.ecuacion.splib.jpa.util.SplibJpaFilterUtil;
import org.springframework.stereotype.Component;

@Component
public class JpaFilterUtil extends SplibJpaFilterUtil {

  public JpaFilterUtil() {
    super(true, true, "accId", true, "groupFilterAcc", "id");
  }
}
