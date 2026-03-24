# Uma Musume: Game Mechanics & Strategy Reference

This document serves as a persistent, comprehensive reference guide for the core mechanics behind inheritance, legacy factors, skill acquisition, and modern training strategies in *Uma Musume: Pretty Derby* (mapped against Japanese inheritance math to future-proof Global/English Trackblazer era builds).

---

## Part 1: Modern Factor Farming Strategies

The traditional "Legacy Loop" (training a closed circle of characters continuously to maximize compatibility) is considered entirely outdated. It is incredibly time-consuming and does not mathematically improve RNG rates for good factors. Instead, the modern meta for Factor Farming (因子厳選) relies on maximizing per-run efficiency through specific strategies:

1.  **Renting a "God Parent":** Instead of building a lineage from scratch, players rent a perfectly rolled friend character (a "God Parent") that has the exact 9-star Blue factors and desirable Pink/White factors needed.
2.  **Auto-Training (おまかせ育成):** Modern setups heavily utilize the Auto-Training feature to AFK farm runs. While stats aren't perfect for competitive racing, they are high enough to roll decent factors with zero manual effort.
3.  **The SS-Rank Threshold (1100+ Stats):** To roll a 3-star Blue (Stat) Factor, the target stat must be at least rank B (600+). However, reaching **SS rank (1100+)** dramatically increases the probability of it becoming a 3-star Blue Factor. Modern scenarios like Trackblazer/MANT are used specifically because they easily cap stats (1100+) and grant massive skill points.
4.  **Targeted Purchasing:** For White (Skill) factors, players buy the **Gold-tier version** of a skill, which doubles the probability of its related White factor dropping. For Pink (Aptitude) factors, players intentionally start with characters exhibiting narrow aptitudes to reduce the pool of potential Pink factor drops.
5.  **Re-roll Items:** The strategic use of Factor Re-roll Passes (因子再獲得パス) on runs that hit all G1 requirements and 1100+ stats but failed the final factor RNG.

---

## Part 2: Mathematical Mechanics

### 1. Affinity (Compatibility / 相性) Calculation
Affinity dictates the probability of factors triggering during the mid-April inheritance events. 

**Affinity Thresholds & Visual Indicators:**
*   **Triangle (△):** 0 - 50 points
*   **Circle (◯):** 51 - 150 points
*   **Double Circle (◎):** 151+ points

**Point Calculation:**
Total Affinity is the sum of:
1.  **Base Affinity:** Hardcoded historical/canonical synergy values between the Ace and the Parents/Grandparents.
2.  **Parent-Grandparent Synergy:** The affinity between the Parent and its two Grandparents.
3.  **G-Race/Title Overlaps (The Bonus):** You gain additive bonus points for every shared race victory between a parent and grandparent. Overlapping 15-20 identical G1 wins across the entire lineage tree is the standard method for forcing a 151+ Double Circle score on pairings with poor innate Base Affinity.

### 2. Factor Inheritance Mechanics
Inheritance occurs at Turn 1, mid-April Classic (Year 2), and mid-April Senior (Year 3). During these events, the game rolls RNG checks against the parent/grandparent cards.

#### Blue Factors (Stats)
*   Guaranteed to trigger at Turn 1.
*   Have an RNG chance to trigger again during both April events, providing massive bursts of raw stats based on their Star count.

#### Pink Factors (Aptitude Upgrades)
Pink Factors upgrade distance, surface, or running style letter grades.

**Turn 1 Inheritance (Initial Start):**
At the start of a run, the total Stars from the relevant Pink Factor across all 6 lineage slots (2 Parents, 4 Grandparents) are tallied into Points (1 Star = 1 Point). You can upgrade an aptitude a **maximum of 4 steps** at the start of a run. You **cannot** reach 'S' rank at Turn 1.

| Target Upgrade | Total Points (Stars) Required |
| :--- | :--- |
| **B → A (1 Step)** | 1 Point |
| **C → A (2 Steps)** | 4 Points (1 + 3) |
| **D → A (3 Steps)** | 7 Points (1 + 3 + 3) |
| **E → A or G → C (4 Steps)** | 10 Points (1 + 3 + 3 + 3) |

*Application: If an Ace starts with a 'D' in Dirt, the parents/grandparents must possess a collective total of at least 7 Stars in Dirt (Pink Factor) to push the Ace to an 'A' rank on Turn 1.*

**April Events (Mid-Run) & Reaching 'S' Rank:**
During the April events, Pink factors have an RNG chance to trigger. 
*   This is the **only way** to upgrade an 'A' rank to an 'S' rank.
*   Having just 1 Star gives a *chance* to hit 'S', but higher total star counts and high Affinity (◎) mathematically amplify the selection success rate.

#### White Factors (Skills & Passives)
When a White Factor successfully triggers in April, it grants a **Hint** (and a hint level) for that skill, partially reducing its Skill Point (SP) purchase cost. It does not grant the skill outright.

**Factor Generation Rates (End of Run):**
When a character finishes their run, they roll to generate factors to pass on. The base math for generating a White skill factor is:
*   **Base Chance (Learned Normal Skill):** ~20%
*   **Double-Circle Skill (◎):** ~25%
*   **Gold Skill Variant:** ~40% (Having the Gold variant effectively doubles the drop rate of its underlying White factor).
*(Note: A skill has absolutely zero chance of rolling as a White factor unless it was explicitly purchased during the run).*

**Lineage Stacking (Increasing Drop Rates):**
To ensure a specific White factor passes down, the formula factors in the number of ancestors already holding it:
*   **Multiplier:** `Base Chance × 1.1^(Number of Parents/Grandparents holding the factor)`
*   This roughly equates to a flat **+2% additive chance per ancestor** holding the exact same White factor. Filling all 6 slots in a lineage with the same 3-Star White factor mathematically maximizes its generation probability for the next generation.

**April Firing Rates per Star:**
During the April events, if a parent possesses a White block, the base probability of it firing and granting the skill hint is tied to its Star rating:
*   **1★:** ~3% chance to fire
*   **2★:** ~6% chance to fire
*   **3★:** ~9% chance to fire 
*(Note: These base rates are heavily multiplied when achieving 151+ Double Circle Affinity).*
