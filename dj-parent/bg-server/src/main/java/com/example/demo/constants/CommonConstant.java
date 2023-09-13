package com.example.demo.constants;

/**
 * 通用常量信息
 *
 *
 */
public class CommonConstant {

    /**
     * 刷新token URL
     */
    public static final String REFRESH_TOKEN_URL = "/auth/refreshToken";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * redis access_token_key
     */
    public static final String ACCESS_TOKEN_KEY = "access_token:";

    /**
     * redis refresh_token_key
     */
    public static final String REFRESH_TOKEN_KEY = "refresh_token:";

    /**
     * 正常状态
     */
    public static final Integer DEL_FLAG_NORMAL = 0;

    /**
     * 删除状态
     */
    public static final Integer DEL_FLAG_DEL = 1;

    /**
     * 页码
     */
    public static final String PAGE_NO = "pageNo";

    /**
     * 分页大小
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序
     */
    public static final String SORT = "sort";

    /**
     * 排序方向
     */
    public static final String ORDER = "order";

    /**
     * 默认页数
     */
    public static final String PAGE_NUM_DEFAULT = "1";

    /**
     * 默认分页大小
     */
    public static final String PAGE_SIZE_DEFAULT = "10";

    /**
     * 默认排序
     */
    public static final String PAGE_SORT_DEFAULT = "create_date";

    /**
     * 默认排序方向
     */
    public static final String PAGE_ORDER_DEFAULT = " desc";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 用户菜单列表key
     */
    public static final String MENU_KEY = "menu:";

    /**
     * 手机号验证码在Redis中的key前缀
     */
    public static final String SMS_CODE_PREFIX = "captcha:sms:";

    /**
     * 手机号验证码条数在Redis中的key前缀
     */
    public static final String SMS_COUNT_PREFIX = "captcha:count:sms:";

    /**
     * 短信验证码条数 失效时间 秒
     * */
    public static final int SMS_COUNT_EXPIRE = 60 * 30;

    /**
     * 船名缓存中的key
     */
    public static final String SHIPS_NAME_KEY = "order:ships_name";

    /**
     * 船公司缓存中的key
     */
    public static final String SHIP_COMPANY_KEY = "order:ship_company";

    /**
     * 箱型缓存中的key
     */
    public static final String BOX_PILE_KEY = "order:box_pile";

    /**
     * 码头缓存中的key
     */
    public static final String DOCK_KEY = "order:dock";

    /**
     * 保存企业 订单编号格式 的前缀
     */
    public static final String ORDER_NUMBER_PREFIX = "order:number:";

    /**
     * 全部叠加 自增数 的前缀
     */
    public static final String ALL_AOTU_PREFIX = "order:aotu:";


    /**
     * 默认表头缓存中的key
     */
    public static final String TABLE_HEADER_DEFAULT_KEY = "order:table_header:default:";

    /**
     * 修改后归属自己的表头key
     */
    public static final String TABLE_HEADER_KEY = "order:table_header:";
}
