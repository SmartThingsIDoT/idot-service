package com.integratingfactor.idot.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idot.service.core.devices.DeviceServiceImpl;

@Configuration
public class CoreConfig {
    @Bean
    public DeviceService deviceService() {
        return new DeviceServiceImpl();
    }
}
