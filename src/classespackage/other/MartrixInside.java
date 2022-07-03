package classespackage.other;

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


}
