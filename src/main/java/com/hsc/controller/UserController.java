package com.hsc.controller;

import com.hsc.WebSecurityConfig;
import com.hsc.dao.TravelRepository;
import com.hsc.entity.Travel;
import com.hsc.entity.User;
import com.hsc.dao.UserRepository;

import com.hsc.serviceimpl.TravelServiceimpl;
import com.hsc.serviceimpl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private UserServiceimpl userService;
    @Autowired
    private TravelServiceimpl travelServiceimpl;
    @Autowired
    private UserController userController;

    //用户登录退出模块


    /**|
     * 登录页面控制
     * @param account
     * @return
     */
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account){

        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(String username, String password, HttpSession session ,Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean verify = userService.verifyLogin(user);
        if (verify) {
            User user1= userService.findByUsername(username);
            Integer id=user1.getId();
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            session.setAttribute("userID", id);
            session.setAttribute("key",username);
            model.addAttribute(userController.listTravel(model,session));
            model.addAttribute("key",username);
            System.out.println(123456789);
            if(user.getUsername().equals("admin")||user.getUsername().equals("administrator ")){
                return "adminIndex";
            }
            else{

            return "index";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "login";
    }


//    前台：

    /**
     *获取所有的图文实体类展示到前台
     * @param model
     * @return
     */
    @RequestMapping("/listTravel")
    public String listTravel(Model model,HttpSession session){
        List<Travel> allTravel = travelRepository.findAll();
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        model.addAttribute("travel", allTravel);
        model.addAttribute("key",username);
        return "index";
    }

    /**
     *
     *根据用户名查找用户id再查找相关的所有图文
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/findUsertravel")
    public String findUsertravel(Model model, HttpSession session ){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        model.addAttribute("key",username);
        if(username.equals("guest")) return "portfolio";
        User user=userService.findByUsername(username);
        List<Travel> allusertravel=travelServiceimpl.findAllTravelByuserid(user.getId());
        model.addAttribute("allusertravel",allusertravel);
        System.out.println(user.getUsername());
        System.out.println(user.getId());
        return  "portfolio";
    }

    /**
     * 获取ID为204的图文实体
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/gettravel")
    public String gettravel(Model model, HttpSession session){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        Travel travel=travelRepository.findById(204).get();
        model.addAttribute("travel",travel);
        model.addAttribute("key",username);
        return  "about";

    }


    /**
     * 通过获取输入参数，先查询作者，后查询地名
     * @param input
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/findByInputLike")
    public String findByInputLike(@RequestParam String input,Model model,HttpSession session)
    {
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user=userRepository.findByUsername(input);
        if(user!=null){
            List<Travel> allTravel = travelRepository.findByInputLike(user.getId().toString());
            model.addAttribute("travel",allTravel);
            model.addAttribute("key",username);
            return "index";
        }
        List<Travel> allTravel = travelRepository.findByInputLike(input);
        model.addAttribute("travel",allTravel);
        model.addAttribute("key",username);
        return "index";
    }


    /**
     *  添加一个Travel图文
     * @param addressname
     * @param img
     * @param text
     * @param session
     * @return
     */
    @PostMapping("/addTravel")
    public String addTravel(Model model,@RequestParam("addressname") String addressname,
                            @RequestParam("img") String img,@RequestParam ("text")String text,HttpSession session){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        if(username.equals("guest")) return "login";
        User user=userService.findByUsername(username);
        Travel travel = new Travel();
        travel.setAddressname(addressname);
        travel.setImg(img);
        travel.setText(text);
        travel.setUserid(user.getId());
        travelRepository.save(travel);
        model.addAttribute("key",username);
        return "upload";
    }





//    后台


    /**
     * 后台显示所有的用户
     * @return
     */
    @RequestMapping("/getALLUser")
    public  String  getALLUser(Model model  ,HttpSession session ){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        if(username.equals("admin")) return "user";
        List<User> allUser= userRepository.findAll();
        model.addAttribute("allUser",allUser);
        return "user";
    }




    /**
     * 将所有图文展示到后台图文管理
     * @param model
     * @return
     */
    @RequestMapping("/listadminTravel")
    public String listdminTravel(Model model){
        List<Travel> alladminTravel = travelRepository.findAll();
        model.addAttribute("travel", alladminTravel);
        return "adminTravel";
    }

    /**
     * 将所有用户展示到后台图文预览
     * @param model
     * @return
     */
    @RequestMapping("/listadminTravelView")
    public String listadminTravelView(Model model){
        List <Travel> alladminTravelView = travelRepository.findAll();
        System.out.println("welcome admin");
        model.addAttribute("travel", alladminTravelView);
        return "adminTravelView";
    }


    /**
     * 添加一个user,使用postMapping接收post请求
     * @return
     */

    @PostMapping("/addUser")
    public String addUser(@RequestParam("username") String username,@RequestParam("password") String password,Model model ){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        List<User> allUser= userRepository.findAll();
        model.addAttribute("allUser",allUser);
        return "user";
    }



    /**
     * post更新user
     * @param id
     * @param username
     * @param password
     * @return
     */
    @PostMapping ("/updateUser")
    public User updateUser(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password){
      User user= new User();
      user.setId(id);
      user.setUsername(username);
      user.setPassword(password);
      System.out.println("更新USER");
        return userRepository.save(user);
    }
    /**
     * 根据id删除user
     * @param id
     */
    @RequestMapping("/deleteUser{id}")
    public String deleteUser(@PathVariable("id") Integer id,Model model){

        userRepository.deleteById(id);
        List<User> allUser= userRepository.findAll();
        model.addAttribute("allUser",allUser);
        return "user";
    }






    /**
     * 传入travel的ID 并获取图文travel实体
     * @param model
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/about{id}")
    public String about(Model model,@PathVariable("id") Integer id,HttpSession session){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        Travel travel=travelRepository.findById(id).get();
        User user = userRepository.findById(travel.getUserid()).get();
        model.addAttribute("author",user.getUsername());
        model.addAttribute("travel",travel);
        model.addAttribute("key",username);
        return "about";
    }

    /**
     *更新页面，传递用户名
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/uploadTravel")
    public String uploadTravel(Model model,HttpSession session){
        String username=(String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
//        System.out.println(123456);
        model.addAttribute("key",username);
        return "upload";
    }


    /**
     * 通过id删除图文信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/deleteTravel{id}")
    public String deleteTravel(@PathVariable("id")Integer id,Model model){
        travelRepository.deleteById(id);
        List<Travel> alladminTravel = travelRepository.findAll();
        model.addAttribute("travel", alladminTravel);
        return "adminTravel";
    }


    /**
     * 模糊查找用户并后台显示
     * @param input
     * @param model
     * @return
     */
    @PostMapping("/findUserByInputLike")
    public  String findUserByInputLike(@RequestParam String input,Model model){
        System.out.println("获取值："+input);
        List<User> allUser= userRepository.findByInputLike(input);
        System.out.println("获取值："+input);
        model.addAttribute("allUser",allUser);
        return "user";
    }
    /**
     * 模糊查找图文并后台显示
     * @param input
     * @param model
     * @return
     */
    @PostMapping("/findTravelByInputLike")
    public  String findTravelByInputLike(@RequestParam String input,Model model){


        User user=userRepository.findByUsername(input);
        if(user!=null){
            List<Travel> alladminTravel = travelRepository.findByInputLike(user.getId().toString());
            model.addAttribute("travel",alladminTravel);
            model.addAttribute("count",alladminTravel.size());
            return "adminTravel";
        }
        List<Travel> alladminTravel= travelRepository.findByInputLike(input);
        System.out.println("集合大小："+alladminTravel.size());
        model.addAttribute("travel", alladminTravel);
        model.addAttribute("count",alladminTravel.size());
        return "adminTravel";
    }

}
