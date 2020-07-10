/*
Design TinyURL shortener
        How to design a system that takes big URLs like “https://www.geeksforgeeks.org/count-sum-of-digits-in-numbers-from-1-to-n/” and converts them into a short 6 character URL. It is given that URLs are stored in the database and every URL has an associated integer id.

        One important thing to note is, the long URL should also be uniquely identifiable from the short URL. So we need a Bijective Function

        One Simple Solution could be Hashing. Use a hash function to convert long string to short string. In hashing, that may be collisions (2 long URLs map to same short URL) and we need a unique short URL for every long URL so that we can access long URL back.

        A Better Solution is to use the integer id stored in the database and convert the integer to a character string that is at most 6 characters long. This problem can basically seen as a base conversion problem where we have a 10 digit input number and we want to convert it into a 6 character long string.

        Below is one important observation about possible characters in URL.
        A URL character can be one of the following
        1) A lower case alphabet [‘a’ to ‘z’], total 26 characters
        2) An upper case alphabet [‘A’ to ‘Z’], total 26 characters
        3) A digit [‘0’ to ‘9’], total 10 characters

        There are total 26 + 26 + 10 = 62 possible characters.

        So the task is to convert a decimal number to base 62 number.

        To get the original long URL, we need to get URL id in the database. The id can be obtained using base 62 to decimal conversion.

 */

class GFG
{
    // Function to generate a short url from integer ID
    static String idToShortURL(int n)
    {
        // Map to store 62 possible characters
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuffer shorturl = new StringBuffer();

        // Convert given integer id to a base 62 number
        while (n > 0)
        {
            // use above map to store actual character
            // in short url
            shorturl.append(map[n % 62]);
            n = n / 62;
        }

        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }

    // Function to get integer ID back from a short url
    static int shortURLtoID(String shortURL)
    {
        int id = 0; // initialize result

        // A simple base conversion logic
        for (int i = 0; i < shortURL.length(); i++)
        {
            if ('a' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'z')
                id = id * 62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'Z')
                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= '9')
                id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }
}
