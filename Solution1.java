class Solution {
    ArrayList<Integer>[] adj;
    public int bfs(int src,boolean[] visited,int x,int y)
    {
        int cnt=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        visited[src]=true;
        while(!q.isEmpty())
        {
            int n=q.poll();
            cnt++;
            for(int a:adj[n])
            {
                if((n==x && a==y)||(n==y && a==x))
                    continue;
                if(!visited[a])
                {
                    visited[a]=true;
                    q.add(a);
                }
            }
        }
        return cnt;
    }
    public int[] findRedundantConnection(int[][] edges) {
        int i,n=edges.length;
        adj=new ArrayList[n+1];
        for(i=0;i<=n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:edges)
        {
            adj[x[0]].add(x[1]);
            adj[x[1]].add(x[0]);
        }
        int[] res=new int[2];
        for(i=0;i<n;i++)
        {
            boolean[] visited=new boolean[n+1];
            int cnt=bfs(1,visited,edges[i][0],edges[i][1]);
            if(cnt==n)
                res=edges[i];
        }
        return res;
    }
}