package send.it.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    static final Logger logger = LoggerFactory.getLogger(LandingPageController.class);


    @GetMapping("/")
    public String home() {
        return "index";
    }
}