class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null) return 0;
        int curIndex = 1;
        int n=1;
        while(n < nums.length){
            if(nums[n] != nums[curIndex - 1]){
                nums[curIndex] = nums[n];
                curIndex++;
            }
            n++;
        }
        return curIndex;
    }
}