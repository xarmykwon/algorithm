package 다익스트라;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
5 6
1
5 1 1
1 2 2 
1 3 3
2 3 4
2 4 5
3 4 6


0
2
3
7
INF

 * @author sungkwonkim
 *
 */
public class 다익스트라2 {

	static int V;
	static ArrayList<ArrayList<Line2>> ad;
	static int[] dist;
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		int E = sc.nextInt();
		int K = sc.nextInt();
		ad = new ArrayList<>();
		dist = new int[V+1];
		for(int i=0; i<dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			ad.add(new ArrayList<Line2>());
		}
		for(int i=0; i<E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();			
			ad.get(u).add(new Line2(v,w));
		}
		dijkstra(K);
		for(int i=1; i<dist.length; i++) {
			if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	
	public static void dijkstra(int K) {
		PriorityQueue<Line2> q = new PriorityQueue<>();		
		dist[K] = 0;
		q.offer(new Line2(K,dist[K]));
		
		while(!q.isEmpty()) {
			int nowV = q.peek().getV();
			int nowW = q.peek().getW();
			q.poll();
			
			if(nowW > dist[nowV]) continue;
			
			for(Line2 line : ad.get(nowV)) {
				int nextV = line.getV();
				int nextW = line.getW();
				if(dist[nextV] > dist[nowV] + nextW) {
					dist[nextV] = dist[nowV] + nextW;
					q.offer(new Line2(nextV, dist[nextV]));
				}
			}
		}
	}
}
 
class Line2 implements Comparable<Line2> {
	private int v;
	private int w;
	
	public Line2(int v, int w) {
		this.v = v;
		this.w = w;
	}
	public int getV() {
		return v;
	}
	public int getW() {
		return w;
	}
	@Override
	public int compareTo(Line2 ano) {
		return this.w - ano.w;		
	}	

}
