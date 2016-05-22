package com.integratingfactor.idot.service.api.devices;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.resource.SmartThingsDevicesAPIResource;
import com.integratingfactor.idot.service.core.devices.DeviceService;
import com.integratingfactor.idp.lib.client.filter.IdpApiAuthFilter;
import com.integratingfactor.idp.lib.client.rbac.IdpRbacAccessDeniedException;

@Component
@Path("devices")
public class SmartThingsDevicesService implements SmartThingsDevicesAPIResource {

    @Autowired
    DeviceService deviceService;

    @Override
    public GetDevicesResponse getDevices(String authorization) throws Exception {
        return GetDevicesResponse.withJsonOK(deviceService.getAllDevices(IdpApiAuthFilter.getRbacDetails()));
    }

    @Override
    public PostDevicesResponse postDevices(String authorization, DeviceReqistration entity) throws Exception {
        throw new IdpRbacAccessDeniedException("method not implemented");
    }

}
