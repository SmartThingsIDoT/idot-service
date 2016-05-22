package com.integratingfactor.idot.service.db.devices;

import java.util.List;

public interface DeviceDao {
    void createDevice(DeviceDetail device);

    DeviceDetail getDevice(String deviceId);

    List<DeviceDetail> getAllDevices();
}
