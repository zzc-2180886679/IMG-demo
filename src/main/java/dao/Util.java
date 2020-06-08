package dao;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author chunchun
 *@date 2020年6月7日
 * @projectname img_up_down
 */
//此类用俩删除服务器上该伤处的图片
public class Util {
	
	private static boolean running = false;
	public static void main(String[] args) {
//		System.out.println("删除");
//		if (!running ) {
//            System.out.println("删除图片开始");
//            System.out.println("开始执行，图片路径为"+IMG_PATH);
//            File file = new File(IMG_PATH);
//            //判断文件是否存在
//            if (file.exists() == true){
//                System.out.println("图片存在，可执行删除操作");
//                Boolean flag = false;
//                flag = file.delete();
//                if (flag){
//                    System.out.println("成功删除图片"+file.getName());
//                }else {
//                    System.out.println("删除失败");
//                }
//            }else {
//                System.out.println("图片不存在，终止操作");
//            }
 //       }
		Map m = System.getenv();
		String tomcat = (String) m.get("CATALINA_HOME");
		System.out.println(m.get("CATALINA_HOME"));
		String [] str ="/img_up_down/images/9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg".split("/", 3);
			System.out.println(str[2]);
			System.out.println(str[2].replaceAll("/", "\\\\"));
		//String imgpath = tomcat+"wtpwebapps\img_up_down"+"/img_up_down/images/21a5d4ca-e118-4693-8eb8-6690c94848d4_ManWei04.jpg";
	}
//	public Void deleteimg(String imgname) {
//		///img_up_down/images/9087b5fc-c867-4bdc-85ca-6e48ee7da9a8_ManWei01.jpg
//		return null;
//		
//	}

}
