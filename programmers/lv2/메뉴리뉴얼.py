from collections import Counter
from itertools import combinations

def solution(orders, course):
    answer = []
    
    for c in course:
        menus = []
        for order in orders:
            menus += combinations(sorted(order), c)
        most_order = Counter(menus)
        # 최소 2명 이상의 손님으로 부터 주문된 조합일 때
        if len(most_order) > 0 and max(most_order.values()) > 1: 
            # 가장 많이 주문한 코스 메뉴를 추가
            for i in most_order:
                if most_order[i] == max(most_order.values()):
                    answer.append("".join(i))

    return sorted(answer)