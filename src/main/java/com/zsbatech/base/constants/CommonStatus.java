package com.zsbatech.base.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
public interface CommonStatus {
    /* 用户状态: 1 (已生效), 0 (新建), -1(已驳回), -2 锁定, -3 注销， -4 删除 */
    short ACCOUNT_STATUS_NORMAL = 1;
    short ACCOUNT_STATUS_NEW = 0;
    short ACCOUNT_STATUS_FAIL_AUDIT = -1;
    short ACCOUNT_STATUS_LOCKED = -2;
    short ACCOUNT_STATUS_DESTROY = -3;
    short ACCOUNT_STATUS_DELETE = -4;

    Map<Short, Boolean> ACCOUNT_STATUS_LOGIN = new HashMap<Short, Boolean>() {
        private static final long serialVersionUID = -1520206071459777279L;

        {
            put(ACCOUNT_STATUS_NORMAL, true);
        }
    };

    Map<Short, Boolean> ACCOUNT_STATUS_ALL = new HashMap<Short, Boolean>() {
        private static final long serialVersionUID = -549596731497972125L;

        {
            put(ACCOUNT_STATUS_NORMAL, true);
            put(ACCOUNT_STATUS_FAIL_AUDIT, true);
            put(ACCOUNT_STATUS_NEW, true);
            put(ACCOUNT_STATUS_LOCKED, true);
            put(ACCOUNT_STATUS_DESTROY, false);
            put(ACCOUNT_STATUS_DELETE, false);
        }
    };

    Map<Short, String> ACCOUNT_STATUS_ALL_MSG = new HashMap<Short, String>() {
        private static final long serialVersionUID = -1520206071459777279L;

        {
            put(ACCOUNT_STATUS_NORMAL, "生效中");
            put(ACCOUNT_STATUS_FAIL_AUDIT, "已驳回");
            put(ACCOUNT_STATUS_NEW, "新建");
            put(ACCOUNT_STATUS_LOCKED, "锁定");
            put(ACCOUNT_STATUS_DESTROY, "注销");
            put(ACCOUNT_STATUS_DELETE, "删除");
        }
    };

    Long DEFAULT_ROLE = 1L;
    Long ADMIN_ROLE = 2L;
}
