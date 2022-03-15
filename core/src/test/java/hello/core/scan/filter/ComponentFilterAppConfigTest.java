package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        // beanB는 찾을 수 없음
        // why? Filter에서 excludeFilters에 해당하기 때문
        BeanB beanB = ac.getBean("beanB", BeanB.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            // FilterType.ANNOTATION이 기본값이므로, 안적어도 괜찮
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            },
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)  // A도 제거하겠다면
            }
    )
    static class ComponentFilterAppConfig {

    }
}
