package by.bsu.automobile.configuration;

import by.bsu.automobile.converters.*;
import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;


@Configuration
@EnableWebMvc
@PropertySource("classpath:messages.properties")
@Import(HibernateConfiguration.class)
public class RestApiConfiguration {

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(getConverters());
        return conversionServiceFactoryBean;
    }

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new AutoDealerDTOToAutoDealerConverter());
        converters.add(new AutoDealerToAutoDealerDTOConverter());
        converters.add(new AutoDTOToAutoConverter());
        converters.add(new AutoToAutoDTOConverter());
        converters.add(new DealerDTOToDealerConverter());
        converters.add(new DealerToDealerDTOConverter());
        converters.add(new ShoppingCartDTOToShoppingCartConverter());
        converters.add(new ShoppingCartToShoppingCartDTOConverter());
        converters.add(new UserDataDTOToUserDataConverter());
        converters.add(new UserDataToUserDataDTOConverter());
        converters.add(new UserDTOToUserConverter());
        converters.add(new UserToUserDTOConverter());

        return converters;
    }
}
