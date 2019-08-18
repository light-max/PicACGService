package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/{path}")
    public String view(
            @PathVariable(value = "path") String path
    ) {
        return path;
    }

    @RequestMapping(value = "template/{path}")
    public String template(
            @PathVariable(value = "path") String path
    ) {
        return "template/" + path;
    }
}
