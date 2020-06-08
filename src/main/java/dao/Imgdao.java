package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Imgpojo;

/**
 * @author chunchun
 *@date 2020��5��27��
 * @projectname img_up_down
 */
//ҵ��ʵ��
public class Imgdao {
	private C3P0conn c3p0pool = new C3P0conn();
	//����ͼƬ
	//����ͼƬ�ĵ�ַ���ѵ�ַ�洢�����ݿ�
	public void insert_img(String imgPath) throws SQLException {
		String sql = "INSERT INTO image_test(id,p_image) VALUES(null,?)";
		Connection conn = c3p0pool.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, imgPath);
		ps.executeUpdate();
//		System.out.println(temp);
		c3p0pool.realse(conn);
	}
	//ɾ��ͼƬ,����ͼƬid,����idɾ��ͼƬ
	public void delete_img(Integer id) throws SQLException {
		String sql = "delete from image_test where id=?";
		Connection conn = c3p0pool.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int temp = ps.executeUpdate();
		System.out.println(temp);
		c3p0pool.realse(conn);
	}
	//�鿴ͼƬ
	public ArrayList<Imgpojo> select_img() throws SQLException{
		ArrayList<Imgpojo> list= new ArrayList<Imgpojo>(); 
		String sql = "select * from image_test";
		Connection conn = c3p0pool.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		while(rs.next()) {
			Imgpojo img = new Imgpojo();
			img.setId(rs.getInt("id"));
			img.setP_image(rs.getString("p_image"));
			list.add(img);
	}
		return list;
}
}
