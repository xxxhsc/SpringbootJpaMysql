package com.hsc.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @RequestMapping("/portfolio")
    public String portfolio(){
        return "portfolio";
    }
    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }
    @RequestMapping("/adminIndex")
    public String adminIndex(){
        return "adminIndex";
    }

    @RequestMapping("/adminTravelView")
    public String adminTravelView(){return "adminTravelView";}
    @RequestMapping("/adminTravel")
    public String adminTravel(){return "adminTravel";}

    @RequestMapping("/user")
    public String user(){return  "user";}
    @RequestMapping("/err")
    public String err(){return  "404";}
    @RequestMapping("/uploadUser")
    public String uploadUser(){return "updata";}
    @RequestMapping ("/updata")
    public String  updata(){return "updata";}



}