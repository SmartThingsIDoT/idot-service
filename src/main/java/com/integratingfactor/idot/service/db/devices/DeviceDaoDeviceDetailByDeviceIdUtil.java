package com.integratingfactor.idot.service.db.devices;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

public class DeviceDaoDeviceDetailByDeviceIdUtil {
    
    public static final String PK = "IDOT";

    @Entity
    public static class DeviceDaoDeviceDetailByDeviceIdPk {
        @Id
        String bucket;

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }
    }

    @Entity
    public static class DeviceDaoDeviceDetailByDeviceId {
        @Parent
        Key<DeviceDaoDeviceDetailByDeviceIdPk> pk;

        @Id
        String deviceId;

        String token;

        String endpoint;

        String name;

        String location;

        String type;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public Key<DeviceDaoDeviceDetailByDeviceIdPk> getPk() {
            return pk;
        }

        public void setPk(Key<DeviceDaoDeviceDetailByDeviceIdPk> pk) {
            this.pk = pk;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static Key<DeviceDaoDeviceDetailByDeviceIdPk> toPk() {
        DeviceDaoDeviceDetailByDeviceIdPk pk = new DeviceDaoDeviceDetailByDeviceIdPk();
        pk.bucket = PK;
        return Key.create(pk);
    }

    public static Key<DeviceDaoDeviceDetailByDeviceId> toKey(String deviceId) {
        DeviceDaoDeviceDetailByDeviceId key = new DeviceDaoDeviceDetailByDeviceId();
        key.deviceId = deviceId;
        key.pk = toPk();
        return Key.create(key);
    }

    public static DeviceDaoDeviceDetailByDeviceId toEntity(DeviceDetail device) {
        DeviceDaoDeviceDetailByDeviceId entity = new DeviceDaoDeviceDetailByDeviceId();
        entity.deviceId = device.getDeviceId();
        entity.token = device.getToken();
        entity.endpoint = device.getEndpoint();
        entity.name = device.getName();
        entity.location = device.getLocation();
        entity.type = device.getType();
        entity.pk = toPk();
        return entity;
    }

    public static DeviceDetail toModel(DeviceDaoDeviceDetailByDeviceId entity) {
        DeviceDetail model = new DeviceDetail();
        model.setDeviceId(entity.deviceId);
        model.setEndpoint(entity.endpoint);
        model.setToken(entity.token);
        model.setName(entity.name);
        model.setLocation(entity.location);
        model.setType(entity.type);
        return model;
    }

    public static List<DeviceDetail> toDevices(List<Object> objects) {
        List<DeviceDetail> devices = new ArrayList<DeviceDetail>();
        for (Object o : objects) {
            DeviceDaoDeviceDetailByDeviceId entity = (DeviceDaoDeviceDetailByDeviceId) o;
            devices.add(toModel(entity));
        }
        return devices;
    }

}
