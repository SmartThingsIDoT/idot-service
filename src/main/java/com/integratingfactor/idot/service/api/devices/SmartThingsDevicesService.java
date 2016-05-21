package com.integratingfactor.idot.service.api.devices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idot.api.devices.resource.SmartThingsDevicesAPIResource;

@Component
@Path("devices")
public class SmartThingsDevicesService implements SmartThingsDevicesAPIResource {

    @Override
    public GetDevicesResponse getDevices(String authorization) throws Exception {
        // TODO Auto-generated method stub
        AccessibleDevice device = new AccessibleDevice();
        device.setDeviceId("1234");
        device.setDeviceName("test device");
        device.setDeviceLocation("home");
        device.setDeviceType("switch");
        List<AccessibleDevice> devices = new ArrayList<AccessibleDevice>();
        devices.add(device);
        return GetDevicesResponse.withJsonOK(devices);
    }

    @Override
    public PostDevicesResponse postDevices(String authorization, DeviceReqistration entity) throws Exception {
        // TODO Auto-generated method stub
        ResourceCreationResponse response = new ResourceCreationResponse();
        response.setId("1234");
        return PostDevicesResponse.withJsonCreated(response);
    }

}
