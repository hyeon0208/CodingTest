def gcp(a, b):
    if a % b == 0:
        return b
    return gcp(b, a % b)    

def solution(w,h):
    return (w * h) - ((w + h) - gcp(max(w, h), min(w, h)))

