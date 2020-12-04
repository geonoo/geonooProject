package com.controllers;

import java.util.Locale;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.log4u.log4u;
import com.services.CommonService;
import com.vos.VoTest;
import com.z.utility.WebConfig;

@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	static WebConfig config = new WebConfig();
	static log4u log4u;
	
	@Inject
	CommonService commonService;

	public MainController() {
		logger.info("MainController");
		
		Context context = null;
		try {
			context = (Context)(new InitialContext().lookup("java:/comp/env"));
			config.initalize((String) context.lookup("location"));
		} catch (NamingException e) {
		} finally {
			if (context != null) context = null;
		}
		logger.info("start main...");
	}
	
	@RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest HttpRequest, HttpSession session, Locale locale, ModelMap model) {
		logger.info("index page...");
		
		model.addAttribute("time", commonService.getDBcheck());
		
		return new ModelAndView("index", model);
    }
	
	@RequestMapping(value = "/postTest", method = RequestMethod.POST)
    public @ResponseBody VoTest postTest(HttpServletRequest HttpRequest, HttpSession session, Locale locale, Model model) {
		logger.info("postTest page...");
		
		return new VoTest("test.. 테스트...");
    }
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test(HttpServletRequest HttpRequest, HttpSession session, Locale locale, Model model) {
		logger.info("postTest page...");
		
		logger.info("DB time => " + commonService.getDBcheck());
		
		return "test";
    }
	
}
