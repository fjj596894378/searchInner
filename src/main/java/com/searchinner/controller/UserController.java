package com.searchinner.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.searchinner.model.User;
import com.searchinner.service.dao.ICatalogServiceDAO;
import com.searchinner.service.dao.IUserServiceDAO;
import com.searchinner.util.Util;

@Controller
@RequestMapping("/user")
public class UserController {
	org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);
	private IUserServiceDAO userServiceDAOImp;
	private ICatalogServiceDAO catalogServiceDAOImp;
	/**
	 *  注册方法。通过ajax进行转发
	 * @param user		用户注册信息
	 * @param response	返回页面封装
	 * @param httpSession session
	 * @throws IOException
	 */
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	@ResponseBody
	public void regUser(@RequestBody User user,
			HttpServletResponse response,HttpSession httpSession) throws IOException {
		JsonObject json = new JsonObject();
		User u = userServiceDAOImp.getUser(user.getUserName());
		if(u == null){
			User userRet = null;
			try{
				userRet = userServiceDAOImp.addUser(user);
			}catch(Exception e){
				json.addProperty("message", "注册失败。服务器异常");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			httpSession.setAttribute("currentUser", userRet);  //session
			json.addProperty("message", "注册成功");
			json.addProperty("userName", userRet.getUserName());
		}else{
			json.addProperty("message", "注册失败，账号已存在");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 登录方法。通过ajax进行转发
	 * @param user		用户登录信息。
	 * @param response	返回页面封装
	 * @param session	session
	 * @throws IOException
	 */
	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	@ResponseBody
	public void logon(@RequestBody User user,
			HttpServletResponse response,HttpSession session) throws IOException {
		JsonObject json = new JsonObject();
		try{
			User userRet = userServiceDAOImp.logon(user );
			if(userRet != null){
				session.setAttribute("currentUser", userRet);  //session
				json.addProperty("message", "登录成功");
				json.addProperty("userName", userRet.getUserName());
			}else{
				json.addProperty("message", "登录失败，请检查账号密码");
			}
		}catch(Exception e){
			json.addProperty("message", "登录失败。请联系管理员");
		}
		
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 用户注销方法。通过ajax转发
	 * @param session	注销的session
	 * @param response	封装信息返回页面
	 * @throws IOException
	 */
	@RequestMapping(value = "/logonout", method = RequestMethod.POST)
	@ResponseBody
    public void logonout(HttpSession session,HttpServletResponse response) throws IOException{
		JsonObject json = new JsonObject();
        session.invalidate();
        json.addProperty("message", "注销成功");
        response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
    }
	
	
	/**
	 * 获得所有用户
	 * @return
	 */
	@RequestMapping("/getUsers")
	public ModelAndView getUsers() throws Exception {
		ModelAndView modelView = new ModelAndView();
		List<User> lists = userServiceDAOImp.getUsers();
		modelView.addObject("userList", lists); // 所有的分类
		modelView.setViewName("/user/user_allList");
		return modelView;
	}
	
	/**
	 * 更新用户
	 * @return
	 */
	@RequestMapping("/updateUsers")
	public ModelAndView updateUsers(User user,HttpServletRequest request,HttpSession httpSession) throws Exception {
		ModelAndView modelView = new ModelAndView();
		String dirPath = request.getSession().getServletContext().getRealPath("/") + "userImage";
		//转型为MultipartHttpRequest(重点的所在)  
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        //  获得第1张图片（根据前台的name名称得到上传的文件）   
        MultipartFile imgFile1  =  multipartRequest.getFile("imgFile");  
        User currentuser = (User) httpSession.getAttribute("currentUser");
        if( currentuser != null){
	        if(user.getPassWord() != null && !user.getPassWord().equals("")){
	        	String password = currentuser.getUserName() + user.getPassWord();
	    		password = Util.encrypt_MD5(password);
	    		user.setPassWord(password);
	        }else{
	        	user.setPassWord(currentuser.getPassWord());
	        }
	        if(user.getRealName() != null && !user.getRealName().equals("")){
	        }else{
	        	user.setRealName(currentuser.getRealName());
	        }
        
        
	        user.setImagePath(currentuser.getImagePath());
	        user.setUserName(currentuser.getUserName());
	        //保存第一张图片  
	        if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	 //定义一个数组，用于保存可上传的文件类型  
	            List<String> fileTypes = new ArrayList<String>();  
	            fileTypes.add("jpg");  
	            fileTypes.add("jpeg");  
	            fileTypes.add("bmp");  
	            fileTypes.add("gif");  
	        	String fileName = imgFile1.getOriginalFilename();  
	            //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
	        	fileName = this.getFile(imgFile1, dirPath,"",fileTypes);  
	        	currentuser.setImagePath( "userImage/" + fileName);
	        	user.setImagePath( "userImage/" + fileName);
	        }  
	        userServiceDAOImp.updateUser(user);
	        User userRet= userServiceDAOImp.getUser(currentuser.getUserName());
	    	httpSession.setAttribute("currentUser", userRet);  
	    	modelView.addObject("opearteRet", "操作成功"); // 所有的分类
	    	modelView.setViewName("redirect:/search/index.action");
	    	return modelView;
        }
        modelView.addObject("opearteRet", "操作失败"); // 所有的分类
        modelView.setViewName("redirect:/search/index.action");
		return modelView;
	}
	
	
	public IUserServiceDAO getUserServiceDAOImp() {
		return userServiceDAOImp;
	}
	@Resource(name = "userServiceDAOImp")
	public void setUserServiceDAOImp(IUserServiceDAO userServiceDAOImp) {
		this.userServiceDAOImp = userServiceDAOImp;
	}
	public ICatalogServiceDAO getCatalogServiceDAOImp() {
		return catalogServiceDAOImp;
	}
	
	@Resource(name = "catalogServiceDAOImp")
	public void setCatalogServiceDAOImp(ICatalogServiceDAO catalogServiceDAOImp) {
		this.catalogServiceDAOImp = catalogServiceDAOImp;
	}
	
	
   private String getFile(MultipartFile imgFile,String dirPath,String dirSubPath,List fileTypes) {  
       String fileName = imgFile.getOriginalFilename();  
       //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
      
        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
        fileName = fileName.substring(0,fileName.lastIndexOf(".")) + new Random().nextInt(10000) + "." + ext;  
        //对扩展名进行小写转换  
        ext = ext.toLowerCase();  
      
        File file = null;  
        if(fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件  
            file = this.creatFolder(dirPath, dirSubPath, fileName);  
            try {  
               imgFile.transferTo(file);                   //保存上传的文件  
           } catch (IllegalStateException e) {  
               e.printStackTrace();  
           } catch (IOException e) {  
               e.printStackTrace();  
           }  
        }  
        return fileName;  
   }  
   
   /** 
    * 检测与创建一级、二级文件夹、文件名 
           这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称 
          通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下 
           
    */  
   private File creatFolder(String dirPath,String dirSubPath,String fileName) {  
        File file = null;  
        //dirPath = dirPath.replaceAll("/", "");               //去掉"/"  
        //dirPath = dirPath.replaceAll(" ", "");               //替换半角空格  
        //dirPath = dirPath.replaceAll(" ", "");               //替换全角空格  
         
        //dirSubPath = dirSubPath.replaceAll("/", "");             //去掉"/"  
        //dirSubPath = dirSubPath.replaceAll(" ", "");             //替换半角空格  
        //dirSubPath = dirSubPath.replaceAll(" ", "");             //替换全角空格  
         
        File firstFolder = new File(dirPath);         //一级文件夹  
        LOGGER.debug(firstFolder.getAbsolutePath());
        LOGGER.debug(firstFolder.getPath());
        
        System.out.println("firstFolder.getPath()" + firstFolder.getPath() + "firstFolder.getAbsolutePath()" + firstFolder.getAbsolutePath());
        if(firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹  
            File secondFolder = new File(firstFolder,dirSubPath);  
            if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                file = new File(secondFolder,fileName);  
            }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                secondFolder.mkdir();  
                file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
            }  
        }else {                                                //如果一级不存在，则创建一级文件夹  
            firstFolder.mkdir();  
            File secondFolder = new File(firstFolder,dirSubPath);  
            if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                file = new File(secondFolder,fileName);  
            }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                secondFolder.mkdir();  
                file = new File(secondFolder,fileName);  
            }  
        }  
        return file;  
   }
	
}
