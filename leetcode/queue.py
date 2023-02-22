# -*- coding: utf-8 -*-

from collections import deque

queue = deque()

# enqueue() O(1)
queue.append(1)
queue.append(3)
queue.append(6)
queue.append(2)
print(queue)

# dequeue() O(1)
queue.popleft()
queue.popleft()
queue.popleft()
print(queue)

queue.append(6)
queue.append(9)
print(queue)

queue.pop()
print(queue)