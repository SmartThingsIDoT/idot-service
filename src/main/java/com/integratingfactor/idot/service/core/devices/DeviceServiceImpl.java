package com.integratingfactor.idot.service.core.devices;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceCommand;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idp.lib.client.rbac.IdpApiRbacDetails;

public class DeviceServiceImpl implements DeviceService {
    private static Logger LOG = Logger.getLogger(DeviceService.class.getName());

    @Override
    public ResourceCreationResponse onboardDevice(IdpApiRbacDetails auth, DeviceReqistration request) {
        // TODO Auto-generated method stub
        ResourceCreationResponse response = new ResourceCreationResponse();
        response.setId("1234");
        LOG.info("sending back fake device id");
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
        LOG.info("sending back fake list of devices");
        return devices;
    }

    @Override
    public DeviceCommand getDeviceStatus(IdpApiRbacDetails auth, String deviceId) {
        DeviceCommand status = new DeviceCommand();
        status.setCmdName("Light Level");
        status.setCmdType("RANGE");
        status.setCmdValue("2");
        status.setMinRange(new Double(0));
        status.setMaxRange(new Double(3));
        LOG.info("sending back fake Light Level");
        return status;
    }

    @Override
    public void sendCommand(IdpApiRbacDetails auth, DeviceCommand commands, String deviceId) {
        // TODO Auto-generated method stub
        LOG.info("fake handler for device command -- NO OP");
    }

}
