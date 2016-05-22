package com.integratingfactor.idot.service.api.devices;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.devices.model.DeviceCommands;
import com.integratingfactor.idot.api.devices.resource.DeviceControlResource;
import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idp.lib.client.filter.IdpApiAuthFilter;

@Component
@Path("devices/{deviceId}/commands")
public class DeviceControlService implements DeviceControlResource {

    @Autowired
    DeviceService deviceService;

    @Override
    public PutDevicesByDeviceIdCommandsResponse putDevicesByDeviceIdCommands(String deviceId, String authorization,
            DeviceCommands entity) throws Exception {
        deviceService.sendCommand(IdpApiAuthFilter.getRbacDetails(), entity, deviceId);
        return PutDevicesByDeviceIdCommandsResponse.withOK();
    }

}
