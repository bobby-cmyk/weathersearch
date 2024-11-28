package vttp.batch5.ssf.weatherapi_workshop.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {

    // Define the logger
    private final Logger logger = Logger.getLogger(IndexController.class.getName());
    
    @GetMapping(path={"/", "/index"})
    public ModelAndView getIndex() {
        
        ModelAndView mav = new ModelAndView();

        logger.info("A user has arrived at the index.");

        mav.setViewName("index");
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
}
