Assumptions
===========

Given the speed of EndToEnd(ish) tests I decided to drop UseCase tests. Quite typically in these kind of exercises the problem is simple so many design decisions are just fine. In a more complex project, especially with a slower UI (web), I would have wanted my acceptance tests more at the Use Case level.

I was wondering whether I should apply Domain Model or Transaction Script (following Fowler's naming). For such a simple application Transaction Script would have done. In real-world, if we were pairing, and there were no other requirements coming in the future I probably would have insisted on doing Transaction Script. For the purpose of this exercise, I assumed though, a Domain Model is more useful for a reviewer.

I created lot of structure (layers, packages). It is not only to provide a nice separation of concerns. This is because when working with other people I find it helpful to have some patterns in the code that are easy to follow. I appreciate though, some of the classes are more of middlemen only and could be eliminated to minimise the codebase.

Generally, the code, naming, etc. would have been different if pair programming. Any code, when written solo, is harder to understand to a future team member. For example, my understanding of the word 'Timeline' might be different than others'. I see it as a 'line of time', not bound to a particular social networker, just a line of with messages on it. I will differentiate between a 'personal timeline' (messages by just one user) and a 'wall timeline' (messages from a user and their followees). Again, if we were pairing I would have insisted on creating a common understanding of these words.

