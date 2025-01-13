# [Silver I] Tic Tac Toe - 4347 

[문제 링크](https://www.acmicpc.net/problem/4347) 

### 성능 요약

메모리: 14124 KB, 시간: 104 ms

### 분류

브루트포스 알고리즘, 구현

### 제출 일자

2025년 1월 13일 16:54:20

### 문제 설명

<p>Tic Tac Toe is a child's game played on a 3 by 3 grid. One player, X, starts by placing an X at an unoccupied grid position. Then the other player, O, places an O at an unoccupied grid position. Play alternates between X and O until the grid is filled or one player's symbols occupy an entire line (vertical, horizontal, or diagonal) in the grid.</p>

<p>We will denote the initial empty Tic Tac Toe grid with nine dots. Whenever X or O plays we fill in an X or an O in the appropriate position. The example below illustrates each grid configuration from the beginning to the end of a game in which X wins.</p>

<pre>...  X..  X.O  X.O  X.O  X.O  X.O  X.O
...  ...  ...  ...  .O.  .O.  OO.  OO.
...  ...  ...  ..X  ..X  X.X  X.X  XXX
</pre>

<p>Your job is to read a grid and to determine whether or not it could possibly be part of a valid Tic Tac Toe game. That is, is there a series of plays that can yield this grid somewhere between the start and end of the game?</p>

### 입력 

 <p>The first line of input contains N, the number of test cases. 4N-1 lines follow, specifying N grid configurations separated by empty lines. </p>

### 출력 

 <p>For each case print "yes" or "no" on a line by itself, indicating whether or not the configuration could be part of a Tic Tac Toe game.</p>

