package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Imgdao;
//删除图片servlet
import domain.Imgpojo;

/**
 * @author chunchun
 *@date 2020年5月27日
 * @projectname img_up_down
 */
@WebServlet("/deleteimg")
public class DeleteingServlet extends HttpServlet{
	Imgdao imgdao = new  Imgdao();
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("pid");//图片id
		System.out.println(id);
		String pname = req.getParameter("imgname");//图片路径
		System.out.println(pname);
//		15
//		/img_up_down/images/9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg
		Integer pid = Integer.parseInt(id);
		try {
			//删除数据库路径记录
			imgdao.delete_img(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//组装要删除图片的路径
		String path = req.getSession().getServletContext().getRealPath("");
		System.out.println(path);//D:\apache-tomcat-9.0.16\wtpwebapps\img_up_down\
		String [] str =pname.split("/",3);
		//System.out.println(str[2]);//   images/9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg
		String imgpath = str[2].replaceAll("/", "\\\\");
		//System.out.println(str[2].replaceAll("/", "\\\\"));//  images\9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg
       String deletepath = path +imgpath;//D:\apache-tomcat-9.0.16\wtpwebapps\img_up_down\images\9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg
       File file = new File(deletepath);
     //判断文件是否存在
     if (file.exists() == true){
         System.out.println("图片存在，可执行删除操作");
         Boolean flag = false;
         flag = file.delete();
         if (flag){
             System.out.println("成功删除图片"+file.getName());
         }else {
             System.out.println("删除失败");
         }
     }else {
         System.out.println("图片不存在，终止操作");
     }
     
     Imgdao imgdao = new Imgdao();
		try {
			ArrayList<Imgpojo> list	= imgdao.select_img();
			req.setAttribute("list",list);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
