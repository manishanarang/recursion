class Solution {

    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode(){
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }

    TrieNode root;
    Set<String> result;
    private  char board[][];
    private int m,n;

    public List<String> findWords(char[][] board, String[] words) {
        this.root = new TrieNode();
        this.board = board;
        this.result = new HashSet<>();

        for(String word: words){
            addWord(word);
        }
        this.m = board.length;
        this.n = board[0].length;

        boolean visited[][] = new boolean[m][n];

        for(int i = 0; i<m;i++){
            for(int j = 0; j<n;j++){
                if(root.children.containsKey(board[i][j])){
                    dfs(i,j,"", root, visited);
                }
            }
        }

        return new ArrayList<>(result);
    }

    public void dfs(int i, int j, String word, TrieNode node, boolean[][] visited){
        if(i<0 || j<0 || i>=this.m || j>=this.n || 
        !node.children.containsKey(this.board[i][j]) || //not in our trie so not one of the words
         visited[i][j]){
            return;
        }

        visited[i][j] = true;
        node = node.children.get(board[i][j]);
        word+=board[i][j];

        if(node.endOfWord){
            result.add(word);
        }
        dfs(i+1,j,word,node,visited);
        dfs(i,j+1,word,node,visited);
        dfs(i-1,j,word,node,visited);
        dfs(i,j-1,word,node,visited);
        visited[i][j]=false;
        return;
    }

    public void addWord(String word) {
        TrieNode curr = this.root;

        for(int i = 0; i<word.length();i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.endOfWord=true;
    }
}