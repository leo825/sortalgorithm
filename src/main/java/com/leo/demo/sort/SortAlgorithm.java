package com.leo.demo.sort;

//import java.util.Arrays;

/**
 *　插入排序：直接插入排序、二分法插入排序、希尔排序。
 * 
 *　选择排序：简单选择排序、堆排序。
 * 
 *　交换排序：冒泡排序、快速排序。
 * 
 *　归并排序
 * 
 *　基数排序
 * 
 * */

// 此处排序都是从小到大
public class SortAlgorithm {

	/**
	 * 插入排序算法：直接插入排序(从前向后找合适位置插入)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] straightInsertionSort(int[] array) {
		
		int temp;//带插入的元素
		int i,j;//循环变量
		for (i = 1; i < array.length; i++) {
			temp = array[i];// 待插入的元素
			for (j = i-1; j >= 0 && temp < array[j]; j--) {//从后面到前面比较，第一个元素为当前元素前一个，如果比temp大就向后移动一位
				array[j+1] = array[j];
			}
			array[j+1] = temp;//由于j--因此j+1才是要插入的第j个元素
			
//			for(j = i-1; j >= 0; j--){
//				if(temp <= array[j]){
//					array[j+1] = array[j];
//				}else{
//					break;
//				}
//			}
//			array[j+1] = temp;
			
		}
		return array;
	}
	
	/**
	 * 插入排序算法：二分排序算法(按二分法找到合适位置插入)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] binaryInsertionSort(int[] array){
		
		int i,j;//循环变量
		int low,mid,heigh;//插入位置变量
		int temp;//将要插入的元素
		for(i = 1; i < array.length; i++){
			temp = array[i];//将要插入的数字
			low = 0;
			heigh = i-1;
			while(low <= heigh){
				mid = (low+heigh)/2;
				if(temp<array[mid]){
					heigh = mid-1;					
				}else {
					low = mid+1;
				}
			}
			for(j = i-1; j >=low; j--){
				array[j+1] = array[j];
			}
			if(low != i){//如果是自己就不用替换
				array[low] = temp;			
			}

		}
		return array;
	}
	
	/**
	 * 插入排序算法：希尔排序(基本思想：先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量dt=1(dt<dt-l<…<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。该方法实质上是一种分组插入方法。)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] shellSort(int[] array){
		
		int i,j,k;//循环变量
		int temp;//待插入元素
		int dt = array.length/2;//初始化变量
		while(dt != 1){
			System.out.println("现在dt="+dt);
			dt = dt/2;
			for(i = 0; i < dt; i++){//对进行的分组进行循环访问
				for(j = i+dt; j < array.length; j = j+dt){//对组内数据进行插入排序
					temp = array[j];
					for(k = j-dt; k >= 0; k = k-dt){
						if(temp < array[k]){
							array[k+dt] = array[k];
						}else{
							break;
						}
					}
					array[k+dt] = temp;//在对第k个位置赋值
				}
			}
		}
		return array;
	}
	
	/**
	 * 选择排序：简单选择排序(每趟从待排序的记录序列中选择关键字最大的记录放置到已排序表的最前位置，直到全部排完)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] simpleSelectionSort(int[] array) {

		int i, j, k;// 循环的变量
		int temp;// 待替换的元素
		for (i = 0; i < array.length; i++) {
			temp = array[i];
			k = i;
			for (j = i + 1; j < array.length; j++) {
				if (temp > array[j]) {
					temp = array[j];
					k = j;
				}
			}
			array[k] = array[i];
			array[i] = temp;
		}
		return array;
	}
	
	/**
	 * 选择排序：堆排序(1.构建最大堆。2.选择顶，并与第0位置元素交换。3.由于步骤2的的交换可能破环了最大堆的性质，第0不再是最大元素，需要调用maxHeap调整堆(沉降法)，如果需要重复步骤2)
	 * @param array 一个int型数组
	 * @return int类型的排序后的数组
	 * */

    public static int[] heapSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            createMaxdHeap(array, array.length - 1 - i);
            swap(array, 0, array.length - 1 - i);
        }
        return array;
    }
    
	public static void swap(int[] array, int i, int j) {  
        if (i == j) {
            return;
        }
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

    public static void createMaxdHeap(int[] array, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // 保存当前正在判断的节点
            int k = i;
            // 若当前节点的子节点存在  
            while (2 * k + 1 <= lastIndex) {
                // biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
                int biggerIndex = 2 * k + 1;
                if (biggerIndex < lastIndex) {
                    // 若右子节点存在，否则此时biggerIndex应该等于 lastIndex
                    if (array[biggerIndex] < array[biggerIndex + 1]) {
                        // 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的值
                        biggerIndex++;
                    }
                }
                if (array[k] < array[biggerIndex]) {
                    // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k
                    swap(array, k, biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

	/**
	 * 交换排序：冒泡排序(在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] bubbingSort(int[] array){
		
		int i,j;//循环变量
		int temp;//中间元素
		for(i = 0; i < array.length; i++){//控制数字个数
			for(j = 0; j < array.length-i-1; j++){//控制比较次数
				if(array[j] > array[j+1]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		return array;
	}
	
	/**
	 * 交换排序：快速排序(通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。)
	 * @param array 一个int型的数组
	 * @return int类型的排序后的数组
	 * */
	public static int[] quicksort(int array[], int left, int right) {
        int mid;
        if (left < right) {
            mid = partition(array, left, right);
            quicksort(array, left, mid - 1);
            quicksort(array, mid + 1, right);
        }
        return array;
    }
	//寻找数组中间的位置
    public static int partition(int array[], int left, int right) {
        int pivot = array[left];
        while (left < right) {
            while (left < right && array[right] >= pivot)
                right--;
            if (left < right)
                array[left++] = array[right];
            while (left < right && array[left] <= pivot)
                left++;
            if (left < right)
                array[right--] = array[left];
        }
        array[left] = pivot;
        return left;
    }
	
	
	/**
	 * 归并排序
	 * @param array 一个int型数组(归并排序也是一个递归的实现，其关键在于合并两个有序集。数组array从min到mid有序，从mid+1到max有序，合并这两部分，使得从min到max有序，且这个有序序列仍然放在array的从min到max下标里。)
	 * @return int类型的排序后的数组
	 * */
    public static int[] mergeSort(int[] array, int left, int right) {
        if (left >= right)
            return array;
        int center = (left + right) / 2;// 找出中间索引
        mergeSort(array, left, center);// 对左边数组进行递归
        mergeSort(array, center + 1, right);// 对右边数组进行递归
        merge(array, left, center, right);//合并,其关键在于合并两个有序集
        return array;
    }  

    public static void merge(int[] array, int left, int center, int right) {
        int[] tmpArr = new int[array.length];// 临时数组
        int mid = center + 1;                // 右数组第一个元素索引
        int third = left;                    // third 记录临时数组的索引
        int tmp = left;                      // 缓存左数组第一个元素的索引
        while (left <= center && mid <= right) {// 从两个数组中取出最小的放入临时数组
            if (array[left] <= array[mid]) {
                tmpArr[third++] = array[left++];
            } else {
                tmpArr[third++] = array[mid++];
            }
        }

        while (mid <= right) {// 剩余部分依次放入临时数组(实际上两个while只会执行其中一个)
            tmpArr[third++] = array[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = array[left++];
        }
        while (tmp <= right) {// 将临时数组中的内容拷贝回原数组中(原left-right范围的内容被复制回原数组)
            array[tmp] = tmpArr[tmp++];
        }  
    }
    
	/**
	 * 基数排序(基数排序法又称"桶子法",顾名思义,它是透过键值的部份资讯，将要排序的元素分配至某些"桶"中，藉以达到排序的作用)
	 * @param array 一个int型数组
	 * @return int类型的排序后的数组
	 * */
//    public static int[] radixSort(int[] array, int radix, int d) {
//        // 缓存数组
//        int[] tmp = new int[array.length];
//        int[] buckets = new int[radix];//buckets用于记录待排序元素的信息, buckets数组定义了max-min个桶  
//        for (int i = 0, rate = 1; i < d; i++) {  
//            Arrays.fill(buckets, 0);// 重置count数组，开始统计下一个关键字
//            System.arraycopy(array, 0, tmp, 0, array.length);// 将array中的元素完全复制到tmp数组中
//            for (int j = 0; j < array.length; j++) {// 计算每个待排序数据的子关键字
//                int subKey = (tmp[j] / rate) % radix;  
//                buckets[subKey]++;  
//            }
//            for (int j = 1; j < radix; j++) {
//                buckets[j] = buckets[j] + buckets[j - 1];
//            }
//            for (int m = array.length - 1; m >= 0; m--) {// 按子关键字对指定的数据进行排序
//                int subKey = (tmp[m] / rate) % radix;
//                array[--buckets[subKey]] = tmp[m];
//            }
//            rate *= radix;
//        }
//        return array;
//    }

	/**
	 * 打印数组
	 * @param array int类型数组
	 * */
    public static void printArray(int[] array) {  
        for (int i = 0; i < array.length; i++) {  
            System.out.print(array[i] + "\t");  
        }  
        System.out.println();  
    }
	
	//测试main方法
	public static void main(String[] agrs) {
		int[] array = { 2, 7, 8, 21, 23, 23, 1, 65, 12, 33, 112 };
		System.out.println("排序前的数组：");
		printArray(array);
		System.out.println();
		array = mergeSort(array, 0, array.length - 1);
		System.out.println("排序后的数组：");
		printArray(array);
	}

}
