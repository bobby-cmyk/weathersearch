package vttp.batch5.ssf.weatherapi_workshop.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {
    
    @GetMapping(path={"/", "/index"})
    public ModelAndView getIndex() {
        
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
}
