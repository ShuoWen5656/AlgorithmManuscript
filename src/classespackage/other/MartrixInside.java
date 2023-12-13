package classespackage.other;

import org.omg.CORBA.MARSHAL;

/**
 * @author swzhao
 * @data 2022/7/2 11:08
 * @Discreption <> 判断一个点是否在矩形内部
 */
public class MartrixInside {

    /**
     * 四个点表示矩形坐标
     * 判断xy是否再矩形内部
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @param x
     * @param y
     * @return
     */
    public static boolean isInside(double x1 ,double y1,
                                   double x2, double y2,
                                   double x3, double y3,
                                   double x4, double y4,
                                   double x, double y){
        try {
            if (y1 == y2){
                return isPInside(x1, y1, x4, y4, x, y);
            }
            double l = Math.abs(y3 - y4);
            double k = Math.abs(x3 - x4);
            double s = Math.sqrt(l * l + k * k);
            double sin = l / s;
            double cos = k / s;
            double xR1 = x1 * cos + y1 * sin;
            double yR1 = -x1 * sin + y1 * cos;
            double xR4 = x4 * cos + y4 * sin;
            double yR4 = -x4 * sin + y4 * cos;
            double xR = x * cos + y * sin;
            double yR = -x * sin + y * cos;
            return isPInside(xR1, yR1, xR4, yR4, xR, yR);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 平行的情况下，是否xy在矩形中
     * @param x1
     * @param y1
     * @param x4
     * @param y4
     * @param x
     * @param y
     * @return
     */
    private static boolean isPInside(double x1, double y1,
                                     double x4, double y4,
                                     double x, double y) {
        try {
            if (x < x1){
                return false;
            }
            if (x > x4){
                return false;
            }
            if (y < y4){
                return false;
            }
            if (y > y1){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 二轮测试：求点（x, y ）是否在四个点组成的矩阵中
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @param x
     * @param y
     * @return
     */
    public static boolean isInsideCp1(double x1, double y1,
                                      double x2, double y2,
                                      double x3, double y3,
                                      double x4, double y4, double x, double y) {
        if (y1 == y2) {
            //说明是平行的，无序旋转，直接计算即可
            return isPInsideCp1(x1, y1, x2, y2, x3, y3, x4, y4, x, y);
        }
        // 需要旋转，沿着（x1, y1）旋转成和轴平行即可
        double sin = Math.abs(y1-y2)/Math.sqrt(Math.abs(x1-x2) * Math.abs(x1 - x2) + Math.abs(y1-y2) * Math.abs(y1-y2));
        double cos = Math.abs(x1-x2)/Math.sqrt(Math.abs(x1-x2) * Math.abs(x1 - x2) + Math.abs(y1-y2) * Math.abs(y1-y2));
        // 只有x1，y1不变，其他的全部从新计算
        double x2r = x1 + Math.abs(y1-y2)/sin;
        double y2r = y1;
        double x4r = x1;
        double y4r = x1 - Math.abs(x1-x4)/sin;
        double y3r = y4;
        double x3r = x4 + Math.abs(y1-y2)/sin;
        // 在计算该点旋转后的坐标
        double l = Math.sqrt(Math.abs(x1-x) * Math.abs(x1 - x) + Math.abs(y1-y) * Math.abs(y1-y));
        double sin1 = Math.abs(x1 - x) / l;
        double cos1 = Math.abs(y1 - y) / l;
        double xr = x + l * (sin1 * cos + cos1 * sin);
        double yr = y - l * (cos*cos1 - sin*sin1);
        return isPInsideCp1(x1, y1, x2r, y2r, x3r, y3r, x4r, y4r, xr, yr);
    }

    private static boolean isPInsideCp1(double x1, double y1,
                                        double x2, double y2,
                                        double x3, double y3,
                                        double x4, double y4, double x, double y) {
        if (x > x1 && x < x2 && y < y1 && y > y4) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isInsideCp1(0, 1, 1, 0, 0, -1, -1, 0, 1 , 1));
    }

}
