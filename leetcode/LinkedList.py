class Node :
    def __init__(self, value = 0, next = None):
        self.value = value
        self.next = next

class LinkedList(object):
    def __init__(self):
        self.head = None
    def append(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while(current.next):
                current = current.next
            current.next = new_node
    def get(self, idx):
        current = self.head
        for _ in range(idx):
            current = current.next
        return current.value
    def insert(self, idx, value):
        new_node = Node(value)
        if idx == 0:
            new_node.next = self.head
            self.head = new_node
        else:
            current = self.head
            for _ in range(idx-1):
                current = current.next
            new_node.next = current.next
            current.next = new_node
    def remove(self, idx):
        if idx == 0:
            self.head = self.head.next # garbage collector가 알아서 처리해준다.
        else:
            current = self.head
            for _ in range(idx-1):
                current = current.next
            current.next = current.next.next


ll = LinkedList()
ll.append(1)
ll.append(2)
ll.append(3)
ll.get(0)
ll.get(1)
ll.insert(idx = 2, value = 9)
ll.remove(3)