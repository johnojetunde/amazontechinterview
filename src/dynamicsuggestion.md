**Dynamic Suggestion**<br/>
Amazon is building a "dynamic suggestion" provider when a customer types a minimum of two characters
into the search field. The system will predict at most three products names from Amazon's product repository
 as the customer types each character keyword he/she  wants to search. As the customer continues to type
 these "dynamic suggestions" will update automatically.<br/><br/>
Write algorithm that will output a maximum of three product suggestions after each character
is typed by the customer in the search field.

If there are more than three acceptable products, return the product name that is first in alphabetical order.<br/>
Only return product suggestions after the customer has entered two characters.<br/>
Product suggestions must start with the characters already typed Both the repository
and the customerQuery should be compared  in a ***case-insentive way***.<br/><br/>
**Input**<br/>
The input to the method/function consists three arguments:
_numProducts_, is an integer representing the number of various products in Amazon's product repository;<br/>
_repository_, a list of unique strings representing the various products in Amazon's product repository;<br/>
_customerQuery_, a string representing the full search query of the customer.

**Output**<br/>
Return a list of list of strings, where each list represents the product suggestions made by the system
as the customer types each character of the customerQuery.<br/>
Assume the customer types characters in order without deleting or removing any characters.

**Example**<br/><br/>
Input:<br/>
numProducts = 5<br/>
repository = ["mobile","mouse","moneypot","monitor","mousepad"]<br/>
customerQuery = "mouse"<br/><br/>
Output:<br/>
["mobile","moneypot","monitor"]<br/>
["mouse","mousepad"]<br/>
["mouse","mousepad"]<br/>
["mouse","mousepad"]
<br/><br/>
Explanation:<br/>
The chain of words that will generate in the search box will be mo, mou, mous, mouse<br/>
and each line from the output shows the suggestion of "mo","mou", "mous","mouse",<br/>
respectively in each line.<br/>
For the suggestions that are generated for 'mo', the matches that will be generated are:
["mobile","mouse","moneypot","monitor","mousepad"]<br/><br/>
Alphabetically, they will be reordered to ["mobile","moneypot","monitor","mouse","mousepad"]. <br/><br/>
Thus the suggestions are ["mobile","moneypot","monitor"],
