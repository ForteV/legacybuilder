# Uma Musume: Game Mechanics & Strategy Reference

This document serves as the persistent, comprehensive knowledge base for our Legacy Optimization Application. It details the core mechanics behind inheritance, optimization mathematically, and modern training strategies—specifically tailored for the Global *Trackblazer* era while maintaining data on previous scenarios. 

*Note: All data (Characters, Skills, Base Affinities) will be pulled dynamically from the official Global English client files to ensure accurate naming and modular updates.*

---

## Part 1: Run Categorization & Build Philosophies

The application's core optimization algorithm will structure deck and legacy recommendations around three distinct run profiles:

### 1. Team Trials (Stadium / PvP)
*   **Goal:** Score maximization through high-activation-rate skills, position uniqueness, and high raw stats (SS+).
*   **Logic:** The application will track data on the most frequently occurring race courses per distance category in the Stadium. Skill procurement is prioritized by their activation consistency (e.g., cheap White skills or reliable Gold skills) rather than their race-winning viability. 
*   **Legacy Focus:** Reaching SS stats and patching weak aptitudes, rather than stacking specific acceleration uniques.

### 2. Champion's Meeting (CM Aces)
*   **Goal:** Highly-competitive, optimal builds structured around exact, mathematical course layouts based on impending events.
*   **Logic (The Foresight Database):** Global releases follow the Japanese (JP) schedule. The application maintains a schedule of impending CMs, calculating precise "Effective Stamina" thresholds and mapping valid positional accelerations (e.g., *Angling×Scheming*, *Let's Anaconda*).
*   **Legacy Focus:** S-rank distance aptitude is absolutely mandatory. Parent choices are strictly dictated by the needed inherited unique skills for that specific course geometry.

### 3. Parenting & Legacy Creation (Factor Farming)
*   **Goal:** Running specific, massive race rotations to mathematically force a "Double Circle" (◎) affinity on future generations and roll perfect 3★ Blue / required White factors.
*   **Logic:** The app will calculate goals for both the primary legacy and sub-legacies (grandparents), mapping out customized G1/G2/G3 rotation routes based on the Uma's innate aptitudes (e.g., Turf/Mid/Long vs. Dirt/Mile). 

---

## Part 2: Deckbuilding & Scenario Strategy

### 1. Core Deckbuilding Logic (Support Cards, Race Bonus, & Stats)
*   **MLB SRs > Low LB SSRs:** The optimizer strictly evaluates Support Cards based on their Limit Break. A Max Limit Break (MLB) SR card mathematically outperforms almost all Level 30 (0LB) SSR cards.
*   **Support Card Multipliers:** The algorithm calculates training yields using four main variables: *Friendship Bonus* (multiplicative with itself, unlocks at 80/100 bond), *Training Effectiveness* (additive multiplier on any facility), *Mood Effect* (scales the base mood bonus), and *Specialty Priority* (weight added to the base 100 chance to appear on their preferred stat).
*   **Power vs. Wit Logic:** Backline runners (End Closers/Late Surgers) inherently need more Power cards to accelerate effectively and maneuver around slower runners. Frontline runners (Pace Chasers/Front Runners) heavily prioritize Wit to maintain their exact positioning.
*   **Race Bonus (RB) Multipliers:** Because Trackblazer revolves around massive race counts (35-40 races), the total Race Bonus of a deck mathematically controls the final Skill Point (SP) yield. A deck hitting `50%+ Race Bonus` yields effectively ~400+ more SP over a 35-race career compared to 20% RB. The optimizer must hard-target 50%+ Race Bonus for competitive CM builds.

### 2. The Trackblazer Era (Make a New Track / MANT)
Trackblazer abandons static training goals. Players must manually balance Training, Shop Coins, and Fatigue to hit SS ranks. The optimizer will use these strict bounds:
*   **Deck Structures:** 
    *   *Speed + Wit* (2 Speed, 2 Wit, 2 Flex) - The most consistent for capping speed and generating Wit energy recovery.
    *   *Wit + Guts* (3 Guts, 2 Wit, 1 Flex) - High ceiling, allows purchasing mostly Guts Anklets in the shop, but struggles severely with Stamina checks for Long distances.
*   **Consecutive Racing Fatigue:** 1 and 2 consecutive races have a 0% penalty rate. The `3rd` consecutive race jumps to a `60% chance` for Mood Down and `15% chance` for Skin Outbreak. The `4th` consecutive race is devastating: `100% chance` Mood Down, `33%` Skin Outbreak, and `40% chance` for a massive 3-stat penalty. The standard computational rotation is strictly "Race-Race-Shop/Rest".
*   **Summer Camp & The Twinkle Star Climax:** The strategy mathematically spikes twice:
    1.  *Summer Camp:* The shop refreshes on Turn 1 of Summer. The optimizer expects the user to hold ~100+ coins leading into summer, exclusively spending multi-turn boosters (Megaphones) stacked with Ankle Weights and Whistles to abuse the Level 5 Facility bonuses.
    2.  *Twinkle Star Climax (Ending):* The final 3 scenario races grant exactly +10 base to all stats each. The application's resource logic dictates saving exactly **3 Golden Hammers** and ~150 shop coins exclusively for this finale.

### 3. Archival Scenarios: The Unity Cup (Previously Aoharu)
The application must allow users to select older scenarios for sub-legacy creation. For example, returning to the Unity Cup scenario is necessary if an optimal CM Ace requires the *Ignited Spirit: Wit* or *Unity Ignition: Skill* white factors passed down through its grandparents.
*   **Deck Structure:** The defining structure for Unity Cup is **2 Speed, 3 Wit, and Riko Kashimoto (Pal)**. Wit training provides energy-efficient point accumulation.
*   **Spirit Bursts:** The optimizer must account for two phases of Spirit Bursting:
    1.  *Early Game (Pre-Classic Summer):* Bursts have a 40% proc chance and must be popped immediately (even on suboptimal training) to blindly increase Unity team stat bumps before the next Unity Race.
    2.  *Late Game (Post-Summer):* Bursts are saved and only clicked when overlapping with high-value Friendship trainings.
*   **Target Unity Rankings:** C by Classic Summer → B by Senior Year → A by Senior January → S by Senior Summer.
*   **Optional & Secret Races:** Because Unity Cup forces adherence to the Uma's static Career Goals, users must manually slot in "Optional Races" to hit 15-22 total races. The optimizer must dynamically scan the Uma's **Secret Events** (e.g., Daiwa Scarlet's "Number One and the Coolest") and inject those exact races into the schedule via the Agenda.
*   **Senior Thresholds & Finale Spikes:** By the start of Senior Year (Medium/Long tracks), pacing checks demand `~600 Speed` and `~480-550 Stamina/Power/Wit`. The URA Finale + Riko Pal events contribute a final guaranteed burst of `~54-63 to all stats` and `~20-27 Stamina/Guts`.

---

## Part 3: Mathematical Mechanics (The Optimizer's Rules)

Throughout this section, Factors are interchangeably referred to as **Sparks**. They activate three times: at the start of a Career, and during the two April Inspiration Events.

### 1. Affinity (Compatibility / 相性) Calculation
Affinity dictates the probability of all Sparks triggering during the mid-April inheritance bursts.

**Total Affinity = Base Affinity + Parent/Grandparent Synergy + G-Race Overlaps**
*   **Triangle (△):** 0 - 50 points
*   **Circle (◯):** 51 - 150 points
*   **Double Circle (◎):** 151+ points (The Optimizer's baseline target)

**The Bonus Overlap Math:** You gain additive bonus points for every shared race victory between a parent and grandparent. The app will track the impending major Affinity update: G1 wins will grant 3 Affinity points each, while G2/G3 wins will grant 0.

**Spark Inheritance Formula:** 
Odds are calculated as: `Base Rate * (1 + (Affinity / 100))`. 
*Example for a 3★ Green Spark at 200 Affinity:* `15% * (1 + 2) = 45% chance`.

### 2. Aptitude & Pink Sparks Calculation
Pink Sparks affect distance, surface, or running style letter grades. A parent's Pink spark is randomly chosen with equal odds from any of their A or S aptitudes.

**Turn 1 Inheritance:** Can upgrade an aptitude up to a maximum of 4 steps (e.g., E → A). You cannot hit 'S' at Turn 1.
*   **1 Step:** 1 Point (Star)
*   **2 Steps:** 4 Points
*   **3 Steps:** 7 Points
*   **4 Steps:** 10 Points

**April Inspiration Events (Mid-Run):** This is the **only way** to reach 'S' rank. Pink Sparks have a hidden innate value when obtained during Inspirations; because of this, it is possible to jump 2 aptitude steps with a single randomly-firing Pink Spark.

### 3. Stat & Blue Sparks Generation
*   **Requirement:** To roll a 3★ Blue Spark, the target stat must be at least 600 (B Rank), but reaching **SS rank (1100+)** dramatically increases the drop probability.
*   **Turn 1 Math:** Blue factors grant flat stats Turn 1.
*   **April Inspiration Checks:** When they trigger mid-run, they increase stats by a random amount based on stars:
    *   **1★:** Rolls between +1 to +10 stats.
    *   **2★:** Rolls between +1 to +16 stats.
    *   **3★:** Rolls between +1 to +28 stats (and triggers "Gold" visual events on proc).

### 4. Green Sparks (Inherited Uniques)
Unlocked when an Uma reaches Star Unlock Level 3.
*   A **Parent’s** Inherited Unique is guaranteed to be acquired at the Turn 1 start of a Career.
*   A **Grandparent’s** Inherited Unique must randomly trigger during mid-April Inspiration Events.

### 5. Skill & White Sparks
There are 3 specific types of White Sparks:
1.  **Skill Sparks:** Provide a hint for the corresponding skill. Mathematically cannot roll if the skill wasn't purchased during the run.
2.  **Race Sparks:** Generated from winning G1 races (winning the same race multiple times doesn't increase generation chance). They provide either two stat bonuses or a stat bonus and a skill.
3.  **Scenario Sparks:** Generated by winning scenario finals (like URA Finals) or playing Unity Cup. Provide two stat bonuses. Strangely, Unity Cup Sparks can theoretically generate even without winning the Cup.

**Firing & Generation:** Base generation chance is independently rolled for each spark (no maximum limits). Higher star counts provide universally better odds to trigger, and they are further multiplied by the Total Affinity calculation. A "Gold" visual event during Inspirations designates that a 3★ spark successfully activated that otherwise would have failed to trigger at lower star values.

---

## Part 4: Skill Selection & Optimization Logic

The application will algorithmically determine "which skills are good" by comparing skill data arrays against specific course geometry or run goals. Instead of hardcoding generic "good skills," the program evaluates them dynamically using the following mathematical principles:

### 1. Skill Activation Logic & Reliability
*   **Activation Chance:** The probability of a skill triggering is calculated as `max(100 - 9000/BaseWiz, 20)%`. This ensures a 20% absolute minimum activation rate, and makes Wisdom highly valuable for maximizing Skill Points value.
*   **Multiple Activations (30s Cooldowns):** Most skills have a 500s cooldown (triggering only once). However, skills like *Professor of Curvature* and *Beeline Burst* have a 30s base cooldown. Because Skill cooldowns physically scale by race length (`30s * (CourseDistance[m] / 1000)`), these skills can actually activate *twice* on longer tracks (e.g., Nakayama 2500m), effectively doubling their SP efficiency.

### 2. Velocity, Duration & Value Scaling
*   **Target Speed (Velocity) Limitations:** Velocity skills that activate *during* the Accel Zone (Last Spurt) are mathematically useless. During acceleration, an Uma is below Target Speed anyway, turning the skill's Target Speed increase into a completely wasted effect.
*   **Distance Scaling:** All skill durations scale based on `BaseDuration * (CourseDistance[m] / 1000)`. Thus, duration-based speeds are more impactful at 2500m than 1200m.
*   **Hint Discounts & Pool Shrinking:** The optimizer calculates SP efficiency based on hint levels. Levels 1-3 provide a 10% discount each, and Levels 4-5 provide a 5% discount each (Max 40% discount). Buying skills early from cards with massive hint pools (e.g., SSR Gold Ship, 14 skills) mathematically 'shrinks the pool', increasing the probability of extracting the desired hints later in the run.
*   **Native vs Inherited Uniques:** A runner's *Native* Unique Skill is the only skill that physically levels up in power (from Level 1 up to Level 6). This is strictly gated behind hitting Fan Count requirements during specific Senior Year events (Valentine's, Spring Festival, Christmas). *Inherited* Uniques from parents remain static and mathematically cannot level up.
*   **Skill Upgrades (Circles & Gold):** Normal skills do not have "levels" that increase their effect values. They are instead categorically upgraded (e.g., single circle `◯` to double circle `◎`, or White skill to Gold skill). The program handles these as entirely distinct skills with higher base values.
*   **SP Efficiency Rating:** Skills are algorithmically ranked by their return on investment: `(Base Value × Duration Multiplier) / SP Cost`. The optimizer treats "Hint Levels" purely as a discount to the denominator (`SP Cost`), not an increase to the numerator.

### 3. Course Geometry & "Valid" Accelerations (For CM Aces)
A skill's value in a Champion's Meeting is heavily dictated by exactly *when* it triggers.
*   **The Final Spurt (Phase 3):** Accelerations are only mathematically valuable if they activate exactly at, or slightly before, the moment an Uma begins their "Final Spurt." 
*   **Trigger Mapping:** The application maps course shapes (e.g., "Final Corner starts at X meters, Final Straight starts at Y meters"). It then matches the trigger condition of Green Sparks (Uniques) or general skills against that geometry.
    *   *Example:* If the Final Spurt begins on a corner, *Angling×Scheming* is considered a top-tier **Valid Acceleration**.
    *   *Example:* If the Final Spurt begins on a straight, that same corner skill becomes mathematically useless ("Invalid"), and a straight-conditional acceleration is prioritized.

### 4. Running Style Specific Dependencies
Certain running styles absolutely demand specific skill packages to function competitively. 
*   **Front Runners (逃げ):** Early positional dominance is mandatory. They heavily require the **Groundwork** skill to instantly accelerate out of the gates. Groundwork has a strict activation requirement: the Uma must have at least 3 active Green (Passive) skills during the early phase of the race to trigger. Therefore, the optimizer must forcibly draft at least 3 valid Green skills whenever a Front Runner build is assigned.
*   **Backliners (Pace Chaser/Late Surger/End Closer):** High-priority positioning skills like **Uma Stan** or specific mid-leg pushing speeds are required to close the gap before the Final Spurt begins. Without these, even mathematically perfect final accelerations will fail.

### 5. Goal-Based Skill Filtering
*   **Team Trials (PvP Format):** The pure speed/acceleration impact is secondary. The highest priority variable is **Activation Rate**. A cheap skill that triggers 95% of the time provides more Team Points over a 100-race simulation than an expensive, powerful skill that only triggers 30% of the time.
*   **CM Aces Format:** High-impact, long-duration Gold speeds dominate the priority list, regardless of their high SP cost, provided they meet the strict valid trigger geometry.

---

## Part 5: Core Race & Training Mechanics

The optimizer evaluates overall builds based on strict stat logic and optimal event choices:

### 1. The 5 Core Stats & Priorities
*   **Mandatory:** `Speed` (Top final spurt velocity) and `Stamina` (HP required to finish the race without slowing down).
*   **Important:** `Power` (Acceleration and hill climbing capability; target ~800-1000 min) and `Wit` (Skill activation rate and mid-race positioning).
*   **Optional:** `Guts` (Spot struggle, dueling, and HP reduction). Highly deprioritized unless Speed, Stamina, Power, and Wit easily hit their caps.

### 2. Aptitude Scaling
*   **Distance (S-Rank Mandatory):** Acts as a direct multiplier to Top Speed during the Last Spurt. The optimizer considers an S-Rank Distance aptitude an absolute mathematical requirement for CM Aces.
*   **Track:** Increases Acceleration. Important, but heavily overshadowed by valid acceleration skills.
*   **Style:** Alters the Wit stat used *only* for mid-race overtakes/positioning. It does **not** affect Skill Activation rates and is rarely targeted.

### 3. Universal Global Career Events
The optimizer assumes the player executes strict scheduling around universal scenario events:
*   **Summer Camp (July-Aug, Classic/Senior):** All training facilities temporarily become Level 5. The optimizer expects the user to enter Early July with high energy. If negative statuses exist or energy is empty, the user must use the exclusive `Rest & Recreation` command (heals conditions, +40 energy, +1 Mood).
*   **Late August (Classic):** A universal event offers +10 Power or +10 Guts. The algorithm assumes the user always selects **Power**.
*   **New Years (Jan):** In Classic Year, prioritize `Energy` (unless overflowing). In Senior Year, prioritize `Skill Points` (since the Senior Raffle event immediately follows and restores energy).
*   **Acupuncturist Sasanami (RNG):** If this rare event occurs, the optimizer assumes the user selects the *Winning Chakra* for longer distances/stats, or the *Health Chakra* for safe energy. The *Strength Chakra* is statistically flagged as a failure risk and avoided entirely.
