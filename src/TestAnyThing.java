import java.io.File;
import java.util.Stack;

/**
 * @author swzhao
 * @data 2022/8/12 19:35
 * @Discreption <> 所有算法的测试点，测试通过后发表csdn
 */
public class TestAnyThing {




    public static void main(String[] args) {

        File file = new File("/Volumes/SWZHAOU/tingshu");
//        System.out.println(file.listFiles());
//
        String[] fileNames = file.listFiles()[0].list();
        for (String fileName : fileNames) {
            String[] split = fileName.split("\\.");
            StringBuilder firstStr = new StringBuilder(split[0]);
            int add0 = 4 - firstStr.length();
            for (int i = 0; i < add0; i++) {
                firstStr.insert(0, "0");
            }
            File fileold = new File("/Volumes/SWZHAOU/tingshu/斗罗大陆_亿万点击玄幻经典（多人小说剧）_有声的紫襟播讲/" + fileName);
            File filenew = new File("/Volumes/SWZHAOU/tingshu/斗罗大陆_亿万点击玄幻经典（多人小说剧）_有声的紫襟播讲/" + firstStr + "-" + fileName);
            fileold.renameTo(filenew);
        }

    }



}
