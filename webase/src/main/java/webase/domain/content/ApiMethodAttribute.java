package webase.domain.content;

/**
 * Created by yanbin on 2017/7/1.
 */

/**
 * 表示 API 方法的属性。
 */
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
public @interface ApiMethodAttribute {
    /**
     * 不作会话验证。
     */
    boolean nonSessionValidation() default false;

    /**
     * 访问控制
     */
    String businessType() default "";
}
