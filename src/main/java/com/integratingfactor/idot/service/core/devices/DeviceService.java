package com.integratingfactor.idot.service.core.devices;

import java.util.List;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceCommands;
import com.integratingfactor.idot.api.devices.model.DeviceDetail;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.DeviceStatu;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idp.lib.client.rbac.IdpApiRbacDetails;

public interface DeviceService {
    ResourceCreationResponse onboardDevice(IdpApiRbacDetails auth, DeviceReqistration request);

    List<AccessibleDevice> getAllDevices(IdpApiRbacDetails auth);

    List<DeviceDetail> getDeviceDetails(IdpApiRbacDetails auth, String deviceId);

    List<DeviceStatu> getDeviceStatus(IdpApiRbacDetails auth, String deviceId);

    void sendCommand(IdpApiRbacDetails auth, DeviceCommands commands, String deviceId);
}
