package com.integratingfactor.idot.service.mvc.onboard;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.integratingfactor.idot.api.devices.model.DeviceReqistration;
import com.integratingfactor.idot.service.core.devices.DeviceService;

@Controller
public class OnboardingController {
	private static Logger LOG = Logger.getLogger(OnboardingController.class.getName());
	
    @Autowired
    DeviceService deviceService;

    @RequestMapping(value = "/onboard")
    public ModelAndView getOnboardForm(HttpServletRequest request, @RequestParam("redirect") String redirect) {
        LOG.info("Request to onboard device at " + request.getRequestURL() + " from " + redirect);
        request.getSession(true).setAttribute("redirect", redirect);
        request.getSession(true).setAttribute("myself", request.getRequestURL());
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("onboard");
        retVal.addObject("redirect", redirect);
        retVal.addObject("actionUrl", "/onboard");
        return retVal;
	}

    @RequestMapping(value = "/onboard", method = RequestMethod.POST)
    public String submitOnboardForm(HttpServletRequest request, @RequestParam("redirect") String redirect,
            @RequestParam("type") String type, @RequestParam("clientId") String clientId,
            @RequestParam("clientSecret") String clientSecret) {
        LOG.info("Request to onboard device of type " + type + " from " + redirect);
        request.getSession().setAttribute("clientId", clientId);
        request.getSession().setAttribute("clientSecret", clientSecret);
        request.getSession().setAttribute("type", type);
        return "redirect:" + "https://graph.api.smartthings.com/oauth/authorize?" + "response_type=code&" + "client_id="
                + clientId + "&scope=app&redirect_uri=" + request.getRequestURL() + "/callback";
    }

    @RequestMapping(value = "/onboard/callback")
    public String callback(HttpServletRequest request, @RequestParam("code") String code) {
        LOG.info("got auth code " + code);
        DeviceReqistration reg = new DeviceReqistration();
        reg.setAuthCode(code);
        reg.setClientId(getIfExists(request.getSession().getAttribute("clientId")));
        reg.setClientSecret(getIfExists(request.getSession().getAttribute("clientSecret")));
        LOG.info("Onboarded device ID: "
                + deviceService.onboardDevice(null, reg, request.getRequestURL().toString()).getId());
        return "redirect:" + request.getSession().getAttribute("redirect").toString();
    }
    
    private String getIfExists(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    RestTemplate restClient = new RestTemplate();
    
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
            return restClient.postForEntity("https://graph.api.smartthings.com/oauth/token",
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