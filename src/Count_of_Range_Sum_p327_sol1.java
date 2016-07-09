
public class Count_of_Range_Sum_p327_sol1 {
    int[] sums;
    int lower;
    int upper;
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        sums = new int[nums.length + 1];
        this.lower = lower;
        this.upper = upper;
        
        for(int i = 0; i < nums.length; i++){
            sums[i + 1] = sums[i] + nums[i];
        }
        
        return mergeSort(1, nums.length);
    }
    
    private int mergeSort(int left, int right){
        if( left > right) return 0;
        
        int mid = left + (right - left)/2;
        
        int count = mergeSort(left, mid) + mergeSort(mid, right + 1);
        
        int a = mid, b = mid, c = mid;
        int[] temp = new int[right - left];
        int index = 0;
        
        for(int i = left; i < mid; i++){
            while(a < right && sums[a] - sums[i] < lower  ) a++;
            while(b < right && sums[b] - sums[i] <= upper ) b++;
            while(c < right && sums[c] < sums[i] )
            temp[index++] = sums[c++];
            
            temp[index++] = sums[i];
            count += b - a;
        }
        
        for(int i = 0; i < right - left; i++){
            sums[left + i] = temp[i];
        }
        
        return count;
    }
}
