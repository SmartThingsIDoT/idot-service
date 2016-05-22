package com.integratingfactor.idot.service.mvc.onboard;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OnboardingController {
	private static Logger LOG = Logger.getLogger(OnboardingController.class.getName());
	
    // @RequestMapping(value = "/onboard?redirect={callBack}", method =
    // RequestMethod.GET)
    @RequestMapping(value = "/onboard")
    public ModelAndView getOnboardForm(HttpServletRequest request, @RequestParam("redirect") String redirect) {
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
        LOG.fine("Request to onboard device of type " + type + " from " + redirect);
        return "redirect:" + redirect;
    }

}