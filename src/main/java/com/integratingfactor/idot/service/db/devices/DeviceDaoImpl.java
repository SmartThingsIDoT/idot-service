package com.integratingfactor.idot.service.db.devices;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;

import com.googlecode.objectify.ObjectifyService;

public class DeviceDaoImpl implements InitializingBean, DeviceDao {
    private static Logger LOG = Logger.getLogger(DeviceDao.class.getName());

    // @Autowired
    // GdsDaoService dao;

    @Override
    public void afterPropertiesSet() throws Exception {
            ObjectifyService.register(DeviceDaoDeviceDetailByDeviceIdUtil.DeviceDaoDeviceDetailByDeviceIdPk.class);
            ObjectifyService.register(DeviceDaoDeviceDetailByDeviceIdUtil.DeviceDaoDeviceDetailByDeviceId.class);
    }

    @Override
    public void createDevice(DeviceDetail device) {
        try {
            ofy().save().entity(DeviceDaoDeviceDetailByDeviceIdUtil.toEntity(device)).now();
        } catch (Exception e) {
            LOG.warning("Failed to save entity : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DeviceDetail getDevice(String deviceId) {
        // TODO Auto-generated method stub
        try {
            return DeviceDaoDeviceDetailByDeviceIdUtil
                    .toModel(ofy().load().key(DeviceDaoDeviceDetailByDeviceIdUtil.toKey(deviceId)).now());
        } catch (Exception e) {
            LOG.warning("Failed to read device details : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<DeviceDetail> getAllDevices() {
        // TODO Auto-generated method stub
        try {
            return DeviceDaoDeviceDetailByDeviceIdUtil
                    .toDevices(ofy().load().ancestor(DeviceDaoDeviceDetailByDeviceIdUtil.toPk()).list());
        } catch (Exception e) {
            LOG.warning("Failed to read device list : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
