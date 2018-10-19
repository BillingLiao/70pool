
package com.ant.admin.common.annotation;

import java.lang.annotation.*;

/**
 * 数据过滤
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2017-09-17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**  表的别名 */
    String tableAlias() default "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;

    /**  用户ID */
    String userId() default "user_id";
}

