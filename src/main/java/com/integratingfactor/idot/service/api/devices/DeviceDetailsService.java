package com.integratingfactor.idot.service.api.devices;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.devices.resource.DeviceDetailsResource;
import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idp.lib.client.filter.IdpApiAuthFilter;

@Component
@Path("devices/{deviceId}")
public class DeviceDetailsService implements DeviceDetailsResource {

    @Autowired
    DeviceService deviceService;

    @Override
    public GetDevicesByDeviceIdResponse getDevicesByDeviceId(String deviceId, String authorization) throws Exception {
        return GetDevicesByDeviceIdResponse
                .withJsonOK(deviceService.getDeviceDetails(IdpApiAuthFilter.getRbacDetails(), deviceId));
    }

}
