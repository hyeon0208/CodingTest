def solution(board, moves):
    answer = 0
    stack = []
    for m in moves:
        print("m = ", m)
        for i in range(len(board)):
            if board[i][m - 1] != 0:
                if stack[-1:] != [board[i][m - 1]]:
                    stack.append(board[i][m - 1])
                    board[i][m - 1] = 0
                    break
                else:
                    stack.pop()
                    board[i][m - 1] = 0
                    answer += 2
                    break        
    return answer