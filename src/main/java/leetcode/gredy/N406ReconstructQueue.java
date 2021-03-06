package leetcode.gredy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
 * k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * <p>
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * <p>
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 来源：力扣（LeetCode）406
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class N406ReconstructQueue {

    public static void main(String[] args) {
        int[][] lists = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        new N406ReconstructQueue().reconstructQueue(lists);
    }

    /**
     * 解题思路：先排序再插入
     *      * 1.排序规则：按照先H高度降序，K个数升序排序
     *      * 2.遍历排序后的数组，根据K插入到K的位置上
     *      *
     *      * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
     // 再一个一个插入。
     // [7,0]
     // [7,0], [7,1]
     // [7,0], [6,1], [7,1]
     // [5,0], [7,0], [6,1], [7,1]
     // [5,0], [7,0], [5,2], [6,1], [7,1]
     // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return people;
        }
       // 1.排序规则：按照先H高度降序，K个数升序排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
            }
        });
        int row = people.length;
        int col = people[0].length;
        List<int[]> list = new LinkedList<>();
        //2.遍历排序后的数组，根据K插入到K的位置上
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[row][col]);
    }
}
