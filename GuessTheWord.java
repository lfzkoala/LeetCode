/*
Guess The Word

        This problem is an interactive problem new to the LeetCode platform.
        We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
        You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
        This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
        For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
        Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
        Example 1:
        Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

        Explanation:

        master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
        master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
        master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
        master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
        master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

        We made 5 calls to master.guess and one of them was the secret, so we pass the test case.

        Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
        答案：给一系列字符串 每个字符串长度都是6，猜不超过十次，看是否猜中 猜中的条件是所猜的secret word有和wordlist中相同的字符串 matches=6 然后update最后的wordlist

 */

class Solution {
    public void findSecretWord(String[] wordlist, Master master) {

        Random random = new Random();

        for(int i=0, matches = 0; i<10 && matches != 6; i++){
            String guess = wordlist[random.nextInt(wordlist.length)]; //注意random.nextInt和Integer.parseInt()的区别，Integer.parseInt() is converting string to integer
            matches = master.guess(guess);
            List<String> list = new ArrayList<>();

            for(String word: wordlist){
                if(matches == getMatches(guess, word)){
                    list.add(word);
                }
            }
            wordlist = list.toArray(new String[0]);
        }
    }

    private int getMatches(String word1, String word2){
        int matches = 0;
        for(int i=0; i<word1.length(); i++){
            if(word1.charAt(i) == word2.charAt(i)){
                matches++;
            }
        }

        return matches;
    }


}
