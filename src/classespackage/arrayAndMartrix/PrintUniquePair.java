package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/15 21:17
 * @Discreption <> 不重复打印排序数组中相加和为给定值的所有二元组和三元组
 */
public class PrintUniquePair {


    /**
     * 打印二元组
     * @param array
     * @param k
     */
    public static void printPairForTwo(int[] array, int k){
        try {
            int left = 0, right = array.length-1;
            while (left <= right){
                int sum = array[left] + array[right];
                if (sum == k){
                    if(left!=0 && array[left] != array[left-1]){
                        System.out.println(String.format("%d, %d\n", array[left], array[right]));
                    }
                    left++;
                    right--;
                }else if(sum < k){
                    left++;
                }else{
                    right--;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 打印三元组：通过固定一个元素，剩下的元素求二元组和即可
     * @param array
     * @param k
     */
    public static void printPairForThird(int[] array, int k){
        try {
            int left = 0;
            int right = array.length-1;
            for (int i = 0; i < array.length; i++){
                left = i;
                int target = k - array[i];
                while (left <= right){
                    int sum = array[left] + array[right];
                    if (sum == target){
                        if(left!=0 && array[left] != array[left-1]){
                            System.out.println(String.format("%d, %d, %d\n", array[i] ,array[left], array[right]));
                        }
                        left++;
                        right--;
                    }else if(sum < k){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
