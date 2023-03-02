**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group 3:      |     |
| -------------- | --- |
| Student Names: |   Aaron Frerichs  |
|                |   Jesse Gerbrandt  |
|                |   Avijot Girn  |
|                |   Ethan Subasic  |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

Text…

# 2 Manual data-flow coverage calculations for X and Y methods

Contains (Range Test Method):

CFG:
<img src="rangeCFG/1.png" alt="rangeCFG/1.png" width="360"/>

Def-Use Pairs:
Definitions: value
Uses:
value&ltthis.lower
value&gtthis.upper
value&gt=this.lower
value&lt=this.upper

Def-Use Sets:
[value&ltthis.lower], [value&ltthis.lower, value&gtthis.upper], [value&ltthis.lower, value&gtthis.upper, value&gt=this.lower, value&lt=this.upper]

Test Cases:
    1. containsValueShouldBeTrue
    Definitions: value
    Uses: value&ltthis.lower, value&gtthis.upper, value&gt=this.lower, value&lt=this.upper
    
    2. containsValueOver
    Definitions: value
    Uses: value&ltthis.lower, value&gtthis.upper

    3. containsValueUnder
    Definitions: value
    Uses: value&ltthis.lower

    4. containsValueUpperEdge
    Definitions: value
    Uses: value &lt this.lower, value &gt this.upper, value &gt = this.lower, value &lt = this.upper

    5. containsValueLowerEdge
    Definitions: value
    Uses: value&ltthis.lower, value&gtthis.upper, value&gt=this.lower, value&lt=this.upper

    6. containsNegValue
    Definitions: value
    Uses: value&ltthis.lower

    7. containtsOnNegRange
    Definitions: value
    Uses: value&ltthis.lower, value&gtthis.upper, value&gt=this.lower, value&lt=this.upper

    DU Pair Coverage:

    100%: All sets of DU Pairs have been tested


# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
