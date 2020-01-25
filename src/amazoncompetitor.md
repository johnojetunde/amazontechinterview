***Amazon Competitor***
<br/>
Amazon wants to develop a more efficient way of evaluating their top N competitors for the latest Echo device (Alexa-enabled voice assistant).
For this analysis, Amazon has developed an automated web crawler that identifies websites where users have written reviews about Amazon's competitors.
To get a sense of the different competitors out there, Amazon wants to review these websites to see how often a competitor is mentioned i.e a competitor is considered to be a strong competitor if its name occurs in more unique reviews.

Write an algorithm to help Amazon identify the top N competitors mentioned online.

**Input**<br/>
The input to the function/method consists of five arguments - <br/>
*numCompetitors*, an integer representing the number of competitors for the Echo device;<br/>
*topNCompetitors*, is an integer representing the maximum number of competitors that Amazon wants to identify;<br/>
*competitors*, a list of strings representing the competitors;<br/>
*numReviews*, an integer representing the number of reviews from different websites that are identified by the automated webcrawler;<br/>
*reviews*, a list of strings where each element is a string that consists of space-separated words representing user reviews.<br/><br/>

***Output***<br/>
Return a list of strings representing Amazon top N competitors in order of most frequently mentioned to least frequent.

***Note***<br/>
If the value of topNCompetitors is more than the number of competitors discussed in the reviews then output the names of only the competitors discussed in the reviews.<br/>
If competitors have the same count (e.g newshop = 2, mymarket = 4), sort alphabetically.
topNCompetitors=2, Output = [mymarket,newshop]
<br/><br/>

***Example***<br/>
Input:
**numCompetitors** = 6<br/>
**topNCompetitors** = 2<br/>
**competitors** = [newshop, shownow, afshion, fashionbeats, mymarket, tcellular]
**numReviews** = 6
reviews = ["newshop is providing good services in the city; everyone should use newshop","best services by newshop",
 "fashionbeats has great services in the city", "I am proud to have fashionbeats","mymarket has awesome services","Thanks Newshop for the quick delivery"]
 
<br/><br/>
Output:
["newshop","fashionbeats"]

Explanation:
"newshop" is occurring in 3 different user reviews<br/>
"fashionbeats" is occurring in 2 different user reviews<br/>
and "mymarket" is occurring in only 1 review

