
package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * @author chunchun
 *@date 2020Äê5ÔÂ27ÈÕ
 * @projectname img_up_down
 */
//shift+alt+j
public class C3P0conn {
	static ComboPooledDataSource datasource = new ComboPooledDataSource("mysql");
	//@Test
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}
	public void realse(Connection conn) {
		 try {
	            if(conn!=null && conn.isClosed()){
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
