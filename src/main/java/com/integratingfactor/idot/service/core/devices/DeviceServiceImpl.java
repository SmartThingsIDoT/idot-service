package com.integratingfactor.idot.service.core.devices;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceCommands;
import com.integratingfactor.idot.api.devices.model.DeviceDetail;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.DeviceStatu;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idp.lib.client.rbac.IdpApiRbacDetails;

public class DeviceServiceImpl implements DeviceService {
    private static Logger LOG = Logger.getLogger(DeviceService.class.getName());

    @Override
    public ResourceCreationResponse onboardDevice(IdpApiRbacDetails auth, DeviceReqistration request) {
        // TODO Auto-generated method stub
        ResourceCreationResponse response = new ResourceCreationResponse();
        response.setId("1234");
        return response;
    }

    @Override
    public List<AccessibleDevice> getAllDevices(IdpApiRbacDetails auth) {
        AccessibleDevice device = new AccessibleDevice();
        device.setDeviceId("1234");
        device.setDeviceName("test device");
        device.setDeviceLocation("home");
        device.setDeviceType("switch");
        List<AccessibleDevice> devices = new ArrayList<AccessibleDevice>();
        devices.add(device);
        return devices;
    }

    @Override
    public List<DeviceDetail> getDeviceDetails(IdpApiRbacDetails auth, String deviceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DeviceStatu> getDeviceStatus(IdpApiRbacDetails auth, String deviceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendCommand(IdpApiRbacDetails auth, DeviceCommands commands, String deviceId) {
        // TODO Auto-generated method stub

    }

}
