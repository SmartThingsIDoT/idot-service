package com.integratingfactor.idot.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idot.service.core.devices.DeviceServiceImpl;
import com.integratingfactor.idot.service.db.devices.DeviceDao;
import com.integratingfactor.idot.service.db.devices.DeviceDaoImpl;

@Configuration
@PropertySource("classpath:app.properties")
public class CoreConfig {
    @Bean
    public DeviceService deviceService() {
        return new DeviceServiceImpl();
    }

    @Bean
    public DeviceDao deviceDao() {
        return new DeviceDaoImpl();
    }
    //
    // @Bean
    // public GdsDaoService gdsDaoService() {
    // // return new GdsDaoService(env);
    // return new GdsDaoService();
    // }
}
