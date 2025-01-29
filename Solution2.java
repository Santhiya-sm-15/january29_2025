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