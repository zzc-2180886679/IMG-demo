package dao;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author chunchun
 *@date 2020��6��7��
 * @projectname img_up_down
 */
//��������ɾ���������ϸ��˴���ͼƬ
public class Util {
	
	private static boolean running = false;
	public static void main(String[] args) {
//		System.out.println("ɾ��");
//		if (!running ) {
//            System.out.println("ɾ��ͼƬ��ʼ");
//            System.out.println("��ʼִ�У�ͼƬ·��Ϊ"+IMG_PATH);
//            File file = new File(IMG_PATH);
//            //�ж��ļ��Ƿ����
//            if (file.exists() == true){
//                System.out.println("ͼƬ���ڣ���ִ��ɾ������");
//                Boolean flag = false;
//                flag = file.delete();
//                if (flag){
//                    System.out.println("�ɹ�ɾ��ͼƬ"+file.getName());
//                }else {
//                    System.out.println("ɾ��ʧ��");
//                }
//            }else {
//                System.out.println("ͼƬ�����ڣ���ֹ����");
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
