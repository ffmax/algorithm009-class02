class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums,0,nums.length -1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length - 1);
    }
    private void reverse(int nums[],int start,int end){
        for(int n=0;n< (end - start +1) /2;n++){
            swap(nums,start+n,end -n);
        }
    }
    private void swap(int[] nums,int a,int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}