package knowledge.myselfLamda;

/**
 * 使用归并排序法对int类型数组由小到大排序：sort是静态方法
 * 且该方法属于最终方法，不允许修改！
 */
public class IntesSort {
    public final static void sort(int[] ints, MyselfInterface myselfInterface) {
        merge(ints, 0, ints.length - 1, myselfInterface);
    }

    private  static void merge(int[] arrays, int min, int max, MyselfInterface myselfInterface) {
        int[] temp;
        int index, left, right;

        /**递归基本结束条件*/
        if (min == max) {
            return;
        }

        int size = max - min + 1;
        int pivot = (min + max) / 2;
        temp = new int[size];

        merge(arrays, min, pivot,myselfInterface);
        merge(arrays, pivot + 1, max,myselfInterface);

        for (index = 0; index < size; index++) {
            temp[index] = arrays[min + index];
        }
        left = 0;
        right = pivot - min + 1;

        for (index = 0; index < size; index++) {
            if (right <= max - min) {
                if (left <= pivot - min) {
                    if (myselfInterface.bigVaules(temp[left],temp[right]) > 0) {
                        arrays[index + min] = temp[right++];
                    }else{
                        arrays[index + min] = temp[left++];
                    }
                }else {
                    arrays[index + min] = temp[right++];
                }
            }else {
                arrays[index + min] = temp[left++];
            }
        }
    }
}
