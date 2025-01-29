# january29_2025
The problems that I solved today

1.In this problem, a tree is an undirected graph that is connected and has no cycles. You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph. Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

Code:
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

2.Given a sorted dictionary of an alien language having some words dict and k starting alphabets of a standard dictionary. Find the order of characters in the alien language. If no valid ordering of letters is possible, then return an empty string. Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be "true" if the order of string returned by the function is correct else "false" denotes incorrect string returned.

Code:
class Solution {
    ArrayList<Integer>[] adj;
    public String findOrder(String[] dict, int k) {
        adj=new ArrayList[k];
        int i,j;
        for(i=0;i<k;i++)
            adj[i]=new ArrayList<>();
        int[] indegree=new int[k];
        for(i=0;i<dict.length-1;i++)
        {
            String x=dict[i];
            String y=dict[i+1];
            int len=Math.min(x.length(),y.length());
            for(j=0;j<len;j++)
            {
                if(x.charAt(j)!=y.charAt(j))
                {
                    adj[x.charAt(j)-'a'].add(y.charAt(j)-'a');
                    indegree[y.charAt(j)-'a']++;
                    break;
                }
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(i=0;i<k;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        StringBuilder s=new StringBuilder();
        while(!q.isEmpty())
        {
            int x=q.poll();
            char c=(char)(x+97);
            s.append(c);
            for(int a:adj[x])
            {
                indegree[a]--;
                if(indegree[a]==0)
                    q.add(a);
            }
        }
        if(s.length()!=k)
            return "";
        return s.toString();
    }
}

3.Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i. Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Code:
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
