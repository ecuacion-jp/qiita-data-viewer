package jp.ecuacion.app.qiitadataviewer.base.bl;

import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.splib.jpa.bl.*;

public abstract class SystemCommonBaseBl<E extends SystemCommon, I> extends SplibJpaBl<E, I, Long> {

  @Override
  public Long getVersionForOptimisticLocking(SystemCommon e) {
    return e.getVersion();
  }

}
