package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Imgdao;
import domain.Imgpojo;
//查询图片servlet
/**
 * @author chunchun
 *@date 2020年5月27日
 * @projectname img_up_down
 */
@WebServlet("/select")
public class SelectImgServlet extends HttpServlet{
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		Imgdao imgdao = new Imgdao();
		try {
			ArrayList<Imgpojo> list	= imgdao.select_img();
			//System.out.println(list.toString());
			//System.out.println(list.size());
			//System.out.println(list.get(0).getP_image());
			request.setAttribute("list",list);
			request.getRequestDispatcher("list.jsp").forward(request, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
