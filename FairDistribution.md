#FairDistribution

Fairness is achieved by distributing the prizes so that each winner gets a sum as close to the average as possible.

Criteria:
=============

1.Order the winners
2.For each winner, assign them the current highest available prize; so using your first example, winner one gets 1000, winner two gets 800, winner three gets 500.
3.If there are prizes left to distribute, for all but the first winner, assign them the current highest available prize that doesn't take them above the prize total of the first winner; winner two would get 200 (matching the 1000 of winner one), winner three gets 400 (to make 900).
4.Repeat step three until there are no more prizes or no prizes have been allocated; winner three gets 100 to match the 1000 of the other two.
5.If there are still prizes to allocate,  get the winner with the least prize and add the largest prize
6.Repeat step five until all prizes are allocated.