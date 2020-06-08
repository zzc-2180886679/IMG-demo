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
 *@date 2020��5��27��
 * @projectname img_up_down
 */
//�ϴ�ͼƬservlet
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
				// �����ϴ�����Ҫ����������
			    DiskFileItemFactory factory = new DiskFileItemFactory();  // �����ļ�����
			    ServletFileUpload sfu = new ServletFileUpload(factory);   // �ļ��ϴ�����
			    // ���ý����ļ��ϴ��е��ļ����ı����ʽ
			    sfu.setHeaderEncoding("utf-8");
			    // ���� list������������ ���е�����������Ϣ
			    List<FileItem> items = new ArrayList<FileItem>();
			    // �����е�����������Ϣ���� list������
			    try {
					items = sfu.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
			    // ���� list���������� ÿ�������� �е���Ϣ
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
//��ͼƬ���浽��Ŀ·���µ�images�ļ���
private String handleFileField(FileItem item,String repath) {
	    // ��ȡ �ļ��������е� �ļ���
	    String fileName = item.getName();
	    // �ж� ���ļ����ļ����Ƿ�Ϸ�
	    if (fileName==null || "".equals(fileName)) {
	        return null;
	    }
	    // ����ֻ���ϴ�ͼƬ
	    if (!item.getContentType().startsWith("image")) {
	        return null;
	    }
	    // ��ȡ ��ǰ��Ŀ�µ� /files Ŀ¼�ľ���λ��
	    String path = "D:\\Users\\asus.DESKTOP-9D7DVCG\\eclipse-workspace2\\img_up_down\\src\\main\\webapp\\images";
	    File file = new File(path);   // ���� file����
	    // ���� /files Ŀ¼
	    if (!file.exists()) {
	        file.mkdir();
	    }
	    // ���ļ����浽�������ϣ�UUID��ͨ��Ψһ��ʶ�룬���õ��Ļ����ظ������ֳ��֣�
	    String imgname = UUID.randomUUID()+"_"+fileName;
	    try {
	        item.write(new File(file.toString(), imgname));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return repath+imgname;
	}
}