package com.cpprac.leetcode;

/**
 * @author priyesh.mishra
 */
public class WordDictionary {
    class Trie {
        Trie ch[];
        boolean end;
        Trie (){
            ch = new Trie[27];
            end = false;
        }
    }
    Trie root;

    public WordDictionary() {
        root = new Trie();
    }
    public void addWord(String word) {
        Trie currTrie = root;
        for(int i=0;i<word.length();i++){
            if(currTrie.ch[word.charAt(i)-'a']==null){
                currTrie.ch[word.charAt(i)-'a']=new Trie();
            }
            currTrie = currTrie.ch[word.charAt(i)-'a'];
        }
        currTrie.end = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int pos, Trie trie){
        char currCh = word.charAt(pos);
        // if(pos==word.length()-1){
        //     if(currCh=='.'){
        //         for(int i=0;i<26;i++){
        //             if(trie.ch[i]!=null && trie.ch[i].end)
        //                return true;
        //         }
        //         return false;
        //     }else{
        //         return trie.ch[currCh-'a']!=null && trie.ch[currCh-'a'].end;
        //     }
        //     return false;
        // }
        if(currCh=='.'){
            for(int i=0;i<26;i++){
                if(pos==word.length()-1){
                    if(trie.ch[i]!=null && trie.ch[i].end)
                        return true;
                }
                else if(trie.ch[i]!=null){
                    boolean exists = search(word, pos+1, trie.ch[i]);
                    if(exists) return true;
                }
            }
            return false;
        }
        else{
            if(pos==word.length()-1){
                return trie.ch[currCh-'a']!=null && trie.ch[currCh-'a'].end;
            }
            return trie.ch[currCh-'a']!=null && search(word, pos+1, trie.ch[currCh-'a']);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search("b.."));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("pad"));
    }
}
