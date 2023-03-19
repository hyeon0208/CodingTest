from collections import deque 

def solution(board):
    answer = board[0][0]

    for x in range(1, len(board)):
        for y in range(1, len(board[0])):
            if board[x][y] == 1:
                board[x][y] = min(board[x-1][y], board[x-1][y-1], board[x][y-1]) + 1
                answer = max(answer, board[x][y])

    return answer ** 2