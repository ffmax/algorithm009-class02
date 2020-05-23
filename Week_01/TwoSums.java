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