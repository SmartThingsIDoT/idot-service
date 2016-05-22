package com.integratingfactor.idot.service.core.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.integratingfactor.idot.api.devices.model.AccessibleDevice;
import com.integratingfactor.idot.api.devices.model.DeviceCommand;
import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.api.devices.model.ResourceCreationResponse;
import com.integratingfactor.idot.service.db.devices.DeviceDao;
import com.integratingfactor.idot.service.db.devices.DeviceDetail;
import com.integratingfactor.idp.lib.client.rbac.IdpApiRbacDetails;

public class DeviceServiceImpl implements DeviceService {
    private static Logger LOG = Logger.getLogger(DeviceService.class.getName());

    @Autowired
    DeviceDao deviceDao;

    @Override
    public ResourceCreationResponse onboardDevice(IdpApiRbacDetails auth, DeviceReqistration request, String url) {
        // TODO Auto-generated method stub
        OAuth2AccessToken token = getToken(request.getAuthCode(), request.getClientId(), request.getClientSecret(),
                url);
        LOG.info("got token :" + (token != null ? token.getValue() : null));
        ResourceCreationResponse response = new ResourceCreationResponse();
        if (token == null)
            return response;
        // // generate a new device registration ID
        // response.setId(UUID.randomUUID().toString());
        // re use client ID as device ID, since one app, one device
        response.setId(request.getClientId());
        // get device endpoint url
        DeviceEndpoints[] endpoints = getDeviceEndpoint(token);
        LOG.info("device registered at endpoint: " + endpoints[0].get("uri").toString());
        // get device info
        DeviceEndpoints info = getDeviceInfo(token.getValue(), endpoints[0].get("uri").toString() + "/info");
        LOG.info("got device: " + info.get("name").toString());
        DeviceDetail detail = new DeviceDetail();
        detail.setDeviceId(response.getId());
        detail.setToken(token.getValue());
        detail.setEndpoint(endpoints[0].get("uri").toString());
        detail.setName(info.get("name").toString());
        detail.setLocation(info.get("location").toString());
        detail.setType(info.get("type").toString());

        deviceDao.createDevice(detail);
        return response;
    }

    @Override
    public List<AccessibleDevice> getAllDevices(IdpApiRbacDetails auth) {
        List<AccessibleDevice> devices = new ArrayList<AccessibleDevice>();
        LOG.info("sending back fake list of devices");
        for (DeviceDetail d : deviceDao.getAllDevices()) {
            AccessibleDevice device = new AccessibleDevice();
            device.setDeviceId(d.getDeviceId());
            device.setDeviceName("test device");
            device.setDeviceLocation("home");
            device.setDeviceType("switch");
            devices.add(device);
        }

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

    RestTemplate restClient = new RestTemplate();

    public static class DeviceEndpoints extends HashMap<String, Object> {

        /**
         * 
         */
        private static final long serialVersionUID = 8064507850103794224L;
        
    }
    
    private DeviceEndpoints getDeviceInfo(String token, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            LOG.info("calling endoints API");
            return restClient.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), DeviceEndpoints.class)
                    .getBody();

        } catch (HttpClientErrorException e) {
            LOG.warning("Error in IDP request: " + e.getMessage() + " : " + e.getResponseBodyAsString());
        } catch (RestClientException e) {
            LOG.warning("Error in IDP request: " + e.getMessage());
        }
        return null;
    }

    private DeviceEndpoints[] getDeviceEndpoint(OAuth2AccessToken token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token.getValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            LOG.info("calling endoints API");
            return restClient.exchange("https://graph.api.smartthings.com/api/smartapps/endpoints", HttpMethod.GET,
                    new HttpEntity<Object>(headers), DeviceEndpoints[].class).getBody();

        } catch (HttpClientErrorException e) {
            LOG.warning("Error in IDP request: " + e.getMessage() + " : " + e.getResponseBodyAsString());
        } catch (RestClientException e) {
            LOG.warning("Error in IDP request: " + e.getMessage());
        }
        return null;
    }

    private OAuth2AccessToken getToken(String code, String clientId, String clientSecret, String url) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("redirect_uri", url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        try {
            LOG.info("calling token API");
            return restClient
                    .postForEntity("https://graph.api.smartthings.com/oauth/token",
                            new HttpEntity<MultiValueMap<String, String>>(map, headers), OAuth2AccessToken.class)
                    .getBody();

        } catch (HttpClientErrorException e) {
            LOG.warning("Error in IDP request: " + e.getMessage() + " : " + e.getResponseBodyAsString());
        } catch (RestClientException e) {
            LOG.warning("Error in IDP request: " + e.getMessage());
        }
        return null;
    }

}
