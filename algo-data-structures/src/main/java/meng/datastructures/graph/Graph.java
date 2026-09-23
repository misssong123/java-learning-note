package meng.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图的简单使用，深度优先遍历和广度优先遍历
 */
public class Graph {
    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //构造器
    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<String>(n);
        this.numOfEdges = 0;
    }
    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    //显示图对应的矩阵
    public void showGraph() {
        for(int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }
    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    /**
     *
     * @param v1 表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;
    //深度优先遍历, 遍历我们所有的结点
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 访问初始结点v，并标记结点v为已访问。
     * 查找结点v的第一个邻接结点w。
     * 若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
     * 若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
     * 查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
     * @param isVisited
     * @param i
     */
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(vertexList.get(i) + "=>");
        isVisited[i] = true;
        //获取当前节点下一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){
            //该节点未被访问
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //获取当前节点，w节点后面的下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 获取index节点中w节点的后一个节点
     * @param index
     * @param pre
     * @return
     */
    private int getNextNeighbor(int index, int pre) {
        for (int i = pre+1 ; i < edges[index].length ; i++){
            if (edges[index][i] > 0 )
                return i;
        }
        return -1;
    }

    /**
     * 获取当前节点的邻接节点
     * @param index
     * @return
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0 ; i < edges[index].length ; i++){
            if (edges[index][i] > 0 )
                return i;
        }
        return -1;
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u ; // 表示队列的头结点对应下标
        int w ; // 邻接结点w
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while( !queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            while(w != -1) {//找到
                //是否访问过
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先
            }
        }

    }


    public static void main(String[] args) {
        //测试图是否创建
        int n = 8;  //结点的个数
        //String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }
        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        //显示邻结矩阵
        graph.showGraph();
        //测试dfs遍历
        System.out.println("深度遍历");
        graph.dfs(); // A->B->C->D->E [1->2->4->8->5->3->6->7]
		System.out.println();
        System.out.println("广度优先!");
        graph.bfs(); // A->B->C->D-E [1->2->3->4->5->6->7->8]
    }

}
