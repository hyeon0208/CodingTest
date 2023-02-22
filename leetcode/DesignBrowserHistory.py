class Node(object):
    def __init__(self, val = 0, next = None, prev = None):
        self.val = val
        self.next = next
        self.prev = prev

class BrowserHistory(object):
    def __init__(self, homepage):
        self.current = Node(val=homepage)
        self.head = self.current
    def visit(self, url):
        self.current.next = Node(val=url, prev=self.current)
        self.current = self.current.next
        return
    def back(self, steps):
        while steps > 0 and self.current.prev != None:
            steps -= 1
            self.current = self.current.prev
        return self.current.val
    def forward(self, steps):
        while steps > 0 and self.current.next != None:
            steps -= 1
            self.current = self.current.next
        return self.current.val
        
browserHistory = BrowserHistory("leetcode.com")
browserHistory.visit("google.com")
print(browserHistory)