package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/3 9:53
 * @Discreption <> 判断一个点是否在三角形内部
 */
public class TriangleInside {

    /**
     * 计算（x1， x2）和 (x2, y2) 之间的距离
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double getSideLength(double x1, double y1,
                                       double x2, double y2){
        try {
            // (x1-x2) ^ 2 + (y1-y2) ^ 2 开方
            return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 计算三角形面积：海伦公式
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @return
     */
    public static double getArea(double x1, double y1,
                                 double x2, double y2,
                                 double x3, double y3){
        // 计算三边长
        double side1Length = getSideLength(x1, y1, x2, y2);
        double side2Length = getSideLength(x1, y1, x3, y3);
        double side3Length = getSideLength(x2, y2, x3, y3);
        double p = (side1Length+side2Length+side3Length)/2;
        return Math.sqrt(p * (p-side1Length) * (p-side2Length) * (p -side3Length));
    }

    /**
     * 第一种方式：用面积判断，如果点在外面，则o点为中心的三角形会超过原三角形面积
     * 缺点：由于double的精度问题，可能会出现误判的情况
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x
     * @param y
     * @return
     */
    public static boolean isInside1(double x1, double y1,
                                    double x2, double y2,
                                    double x3, double y3,
                                    double x, double y){
        double allArea = getArea(x1, y1, x2, y2, x3, y3);
        double area1 = getArea(x1, y1, x2, y2, x, y);
        double area2 = getArea(x1, y1, x3, y3, x, y);
        double area3 = getArea(x2, y2, x3, y3, x, y);
        return area1+area2+area3 <= allArea;
    }

    /**
     * 计算向量的叉乘
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double crossProduct(double x1, double y1, double x2, double y2){
        return x1*y2 - x2*y1;
    }

    /**
     * 第二种判断方式：判断xy点在三条边的同一边即可
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x
     * @param y
     * @return
     */
    public static boolean isInside2(double x1, double y1,
                                   double x2, double y2,
                                   double x3, double y3,
                                   double x, double y){
        // 首先调整三条边顺序为逆时针
        // 假设1，2 点为1->2
        if (crossProduct(x3 - x1, y3 - y1, x2 - x1, y2 - y1) > 0){
            // 在左边，调换1,3两个坐标即可
            double tem = x1;
            x1 = x3;
            x3 = tem;
            tem = y1;
            y1 = y3;
            y3 = tem;
        }
        // 在逆时针的情况下，三角形里的点应该在三边向量的左边,向量的叉乘应该要大于0
        if (crossProduct(x1 - x3, y1 -y3, x-x3, y-y3) <= 0){
            return false;
        }
        if (crossProduct(x2 - x1, y2 -y1, x-x1, y-y1) <= 0){
            return false;
        }
        if (crossProduct(x3 - x2, y3 -y2, x-x2, y-y2) <= 0){
            return false;
        }
        return true;
    }



}
