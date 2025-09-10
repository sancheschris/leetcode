public class Palindrome {

    public static void main(String[] args) {

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        String cleaned = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);

            if (isAlphaNum(c)) {
                cleaned += Character.toLowerCase(c);
            }
        }

        // reverse the cleaned string
        String reversed = "";
        for (int i = cleaned.length() - 1; i >= 0; i--) {
            reversed += cleaned.charAt(i);
        }

        return cleaned.equals(reversed);
    }

    public static boolean isAlphaNum(char c) {
        return (c >= 'A'&& c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
