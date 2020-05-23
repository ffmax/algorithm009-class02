class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null) return;
       int  index = 0;
       boolean isStart= false;
       int boundIndex = 0;
        while(index < nums.length){
            if(!isStart){
                if(nums[index] == 0){
                    boundIndex = index;
                    isStart = true;
                }
            }else{
                if(nums[index] != 0){
                    nums[boundIndex] =nums[index];
                    nums[index] =0;
                    boundIndex++;
                }
                
            }
            index++;
        }
    }
}