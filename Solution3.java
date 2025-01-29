class Solution {
    ArrayList<int[]>[] adj;
    public void dfs(int src,boolean[] visited,Stack<Integer> st)
    {
        visited[src]=true;
        for(int[] a:adj[src])
        {
            if(!visited[a[0]])
                dfs(a[0],visited,st);
        }
        st.push(src);
    }
    public int[] shortestPath(int V, int E, int[][] edges) {
        adj=new ArrayList[V];
        int i;
        for(i=0;i<V;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:edges)
            adj[x[0]].add(new int[]{x[1],x[2]});
        Stack<Integer> st=new Stack<>();
        boolean[] visited=new boolean[V];
        dfs(0,visited,st);
        int[] dist=new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        boolean flag=false;
        while(!st.isEmpty())
        {
            int x=st.pop();
            if(!flag)
            {
                dist[x]=0;
                flag=true;
            }
            for(int[] a:adj[x])
            {
                if(dist[x]+a[1]<dist[a[0]])
                {
                    dist[a[0]]=dist[x]+a[1];
                }
            }
        }
        for(i=0;i<V;i++)
        {
            if(dist[i]==Integer.MAX_VALUE)
                dist[i]=-1;
        }
        return dist;
    }
}