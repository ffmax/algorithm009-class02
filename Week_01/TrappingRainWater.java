class Solution {
    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while(right >= left){
            if(leftMax < rightMax){
                if(height[left] > leftMax){
                   leftMax = height[left]; 
                }else{
                    res += (leftMax - height[left]);
                }
                left++;
            }else{
                if(height[right] > rightMax){
                    rightMax = height[right];
                }else{
                    res += (rightMax - height[right]);
                }
                right--;
            }
        }
        return res;
    }
}