package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.Imgdao;
import domain.Imgpojo;

/**
 * @author chunchun
 *@date 2020年5月27日
 * @projectname img_up_down
 */
//上传图片servlet
@WebServlet("/upload")
public class UploadServlet extends HttpServlet{
			
			Imgdao imgdao = new Imgdao();
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
			}
			@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
				String photepath= request.getContextPath()+"/images/";
				// 创建上传所需要的两个对象
			    DiskFileItemFactory factory = new DiskFileItemFactory();  // 磁盘文件对象
			    ServletFileUpload sfu = new ServletFileUpload(factory);   // 文件上传对象
			    // 设置解析文件上传中的文件名的编码格式
			    sfu.setHeaderEncoding("utf-8");
			    // 创建 list容器用来保存 表单中的所有数据信息
			    List<FileItem> items = new ArrayList<FileItem>();
			    // 将表单中的所有数据信息放入 list容器中
			    try {
					items = sfu.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
			    // 遍历 list容器，处理 每个数据项 中的信息
			    for (FileItem item : items) {
			         String name = handleFileField(item,photepath);
			         if(!(name==null)) {
			        	 try {
							imgdao.insert_img(name);
						} catch (SQLException e) {
							e.printStackTrace();
						}
			         }
			    }
			    Imgdao imgdao = new Imgdao();
				try {
					ArrayList<Imgpojo> list	= imgdao.select_img();
					request.setAttribute("list",list);
					request.getRequestDispatcher("list.jsp").forward(request, responce);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
//把图片保存到项目路径下的images文件夹
private String handleFileField(FileItem item,String repath) {
	    // 获取 文件数据项中的 文件名
	    String fileName = item.getName();
	    // 判断 此文件的文件名是否合法
	    if (fileName==null || "".equals(fileName)) {
	        return null;
	    }
	    // 控制只能上传图片
	    if (!item.getContentType().startsWith("image")) {
	        return null;
	    }
	    // 获取 当前项目下的 /files 目录的绝对位置
	    String path = "D:\\Users\\asus.DESKTOP-9D7DVCG\\eclipse-workspace2\\img_up_down\\src\\main\\webapp\\images";
	    File file = new File(path);   // 创建 file对象
	    // 创建 /files 目录
	    if (!file.exists()) {
	        file.mkdir();
	    }
	    // 将文件保存到服务器上（UUID是通用唯一标识码，不用担心会有重复的名字出现）
	    String imgname = UUID.randomUUID()+"_"+fileName;
	    try {
	        item.write(new File(file.toString(), imgname));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return repath+imgname;
	}
}