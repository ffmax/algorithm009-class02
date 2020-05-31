## 映射
映射无外乎就是KV, 要根据K找V，其实都是把K和V放一起，让后怎么找K就是关键，可以用hash的方式，维护K.hashcode - > K 的关系，也可以直接把K进行排序，形成一个sortMap。
### hash表
主要三个问题
1. hash冲突的解决方式
2. hash值的计算
3. 动态扩展
第一点，常用的方法无外乎是链地址法，或者开放地址法。链地址法，通过用链表的方式（大了变红黑树）组织冲突的kv对。开发寻址则采用二次hash，或者线性寻址的方式，优势在于简单，只需要一个数组，容量很好分配，不适合经常delete等操作的。
第二点， hash函数的主要的设计目的在于把输入空间一对一的映射到整数集合中去，所以这其实强相关与输入数据的数据分布具体情况，但由于hash表本身是一个有限的长度的数组，那怎么把无限的hashcode，映射到有限的数组index上呢，java hashmap  (n - 1) & （(hash)^(hash >>> 16))），n为size，& n-1 相当于对n取模。除此之外，考虑hash值的高位参与计算，毕竟一般而言hashmap容量比较小，所以高位不和低位中和下，那么很容易高位基本不起作用。
第三点，动态扩展，选择了数组容量扩大两倍，这一方面是上面&n-1等价于mod n的原因，而且数据存进去的时候存了hash值，这就直接根据hash & oldCap == 0

### treeMap
通过二叉排序结构的树来组织kv。

## 树
树的核心在于递归的定义，树由根节点，多个孩子树构成，每个子树都是一个递归的定义，这也是树种各种递归算法的原因。还有一点是
树的平衡性怎么使得各个子树的高度尽量一致，从而效率不至于下降到链表一样，所以出来一堆平衡树的结构。
### 堆
堆是一种可以快速找到最大值最小值的结构，斐波那契堆深入掌握下。


有效的字母异位词
```
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s==null || t == null) return false;
        if(s.length() != t.length()) return false;
        int[] counter = new int[26];
        
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        
        for(char c : sCharArray){
            counter[c -'a']++;
        }
        for(char c : tCharArray){
            counter[c - 'a']--;
        }
        for(int value : counter){
            if(value != 0) {
                return false;
            }
        }
        return true;
    }
}
```

两数之和
```
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n=0;n<nums.length;n++){
            if(map.containsKey(target - nums[n])){
                res[0] = map.get(target - nums[n]);
                res[1] = n;
                break;
            }else{
                map.put(nums[n],n);
            }
        }
        return res;
    }
}

```

N叉树的前序遍历
```
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if(root!= null){
            solve(root);
        }
            return res;
    }
    public void solve(Node root){
        res.add(root.val);
        if(root.children != null){
            for(Node node: root.children){
                preorder(node);
            }
        }
    }
}

```

中序遍历
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        TreeNode node = root;
        boolean hasReadLeft = true;
        
        while(node != null){
            
        if(hasReadLeft &&  node.left != null){
            stack.push(node);
            node = node.left;
        }else{
            results.add(node.val);
            node = node.right;
        }
        if(node == null && stack.size()!= 0){
            node = stack.pop();
            hasReadLeft = false;
        }else{
            hasReadLeft = true;
        }
        
        }
        
        
        return results;
        
        
    }
}

```
字母异位词分组
```
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        int groupCount = 0;
        for(String str:strs){
            String sortStr = sort(str);
            if(map.containsKey(sortStr)){
                res.get(map.get(sortStr)).add(str);
            }else{
                List<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                res.add(newGroup);
                map.put(sortStr,groupCount++);
            }
        }
        return res;
    }
    private String sort(String str){
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);

    }
}
```