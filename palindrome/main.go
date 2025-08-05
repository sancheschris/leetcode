package main

import (
	"fmt"
	"strings"
	"unicode"
)

func main() {

	fmt.Println("Is Palindrome")
	fmt.Println(isPalindrome("A man, a plan, a canal: Panama"))

}

// A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
//  and removing all non-alphanumeric characters, it reads the same forward and backward.
//  Alphanumeric characters include letters and numbers.
func isPalindrome(s string) bool {
	left := 0
	right := len(s) - 1

	for left < right {
		for left < right && !isAlphaNum(rune(s[left])) {
			left++
		}
		for left < right && !isAlphaNum(rune(s[right])) {
			right--;
		}

		if strings.ToLower(string(s[left])) != strings.ToLower(string(s[right])) {
			return false
		}

		left++
		right--
	}
	return true
}

func isAlphaNum(c rune) bool {
	return unicode.IsLetter(c) || unicode.IsDigit(c)
}