package com.integratingfactor.idot.service.core.devices;

import java.util.List;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceCommand;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idp.lib.client.rbac.IdpApiRbacDetails;

public interface DeviceService {
    ResourceCreationResponse onboardDevice(IdpApiRbacDetails auth, DeviceReqistration request, String url);

    List<AccessibleDevice> getAllDevices(IdpApiRbacDetails auth);

    DeviceCommand getDeviceStatus(IdpApiRbacDetails auth, String deviceId);

    void sendCommand(IdpApiRbacDetails auth, DeviceCommand commands, String deviceId);
}
