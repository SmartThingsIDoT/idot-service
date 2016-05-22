package com.integratingfactor.idot.service.api.devices;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.devices.resource.DeviceStatusResource;
import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idp.lib.client.filter.IdpApiAuthFilter;

@Component
@Path("devices/{deviceId}/status")
public class DeviceStatusService implements DeviceStatusResource {

    @Autowired
    DeviceService deviceService;

    @Override
    public GetDevicesByDeviceIdStatusResponse getDevicesByDeviceIdStatus(String deviceId, String authorization)
            throws Exception {
        return GetDevicesByDeviceIdStatusResponse
                .withJsonOK(deviceService.getDeviceStatus(IdpApiAuthFilter.getRbacDetails(), deviceId));
    }

}
