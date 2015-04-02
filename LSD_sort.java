import java.util.Arrays;
import java.util.Random;

public class LSD_sort {
	private static final int MAX = 1000000;

	public static void main(String[] args) {
		
		int[] lsd_arr, def_arr;
		long lsd_time=0, def_time=0; 
		boolean flag = true;
		
		for (int j=0; j<100; j++) {
			 lsd_arr = generate();
			 def_arr = lsd_arr.clone();
			
			//default
			long start = System.currentTimeMillis();
			Arrays.sort(def_arr);
			long stop = System.currentTimeMillis();
			def_time += stop - start;
				
			//lsd
			start = System.currentTimeMillis();
			lsdSort(lsd_arr);
			stop = System.currentTimeMillis();
			lsd_time += stop - start;
			
			if (!Arrays.equals(lsd_arr, def_arr)) flag=false;
		}
		if (flag) System.out.println("All Right!");
		else System.out.println("Smth wrong!");
		System.out.println("Default time - " + def_time/100);
		System.out.println("LSD time - " + lsd_time/100);
	}

	private static int[] generate() {
		int[] srt_arr = new int[MAX];
		Random random = new Random();
		
		for (int i = 0; i < srt_arr.length; i++) {
			srt_arr[i] = random.nextInt(MAX); // 0 - 999999
		}
		
		return srt_arr;
	}
	

	private static void lsdSort(int[] data) {
		int[] temp = new int[data.length];
		final int d = 2;
				
		boolean fl = false; 
		for (int p = 0; p < 3; p++) {
			int bits = 8*p;
			int mask = 0xFF<<bits;
			int[] count = new int[257];			
			
			for (int i = 0; i < data.length; i++)
				count[((data[i] & mask) >> bits)+1]++;
			
			for (int i = 0;  i < 256; i++)
				count[i+1] += count[i];
			
			for (int i = 0; i < data.length; i++){
				temp[count[((data[i] & mask) >> bits)]++] = data[i];
			}
			for (int i = 0; i < data.length; i++)
				data[i] = temp[i];
			
		}
			
	}
}
