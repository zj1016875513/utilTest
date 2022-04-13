import socket
from urllib import parse

import requests
import urllib3

# Python 内置的HTTP 请求库
# urllib.request 请求模块
# urllib.error 异常处理模块
# urllib.parse url解析模块
# urllib.robotparser robots.txt解析模块
from urllib import request, parse
from urllib.parse import urlparse
import urllib.parse
import urllib.request
import urllib.error
import socket

# response0 = urllib.request.urlopen('http://www.baidu.com')
# print(response0.read().decode('utf-8'))

# data = bytes(urllib.parse.urlencode({'word': 'hello'}), encoding='utf-8')
# print(data)
# response1 = urllib.request.urlopen('http://httpbin.org/post', data=data)
# print(response1.read())
# response2 = urllib.request.urlopen('http://httpbin.org/get', timeout=100)
# print(response2.read())

# try:
#     response3 = urllib.request.urlopen('http://httpbin.org/get', timeout=0.1)
# except urllib.error.URLError as e:
#     if isinstance(e.reason, socket.timeout):
#         print("Time_out")

# response4 = urllib.request.urlopen('https://www.python.org')
# print(type(response4))
# print(response4.status)
# print(response4.getheaders())
# print(response4.getheader('Server'))
# print(response4.read())
# print(response4.read().decode('utf-8'))

# request5 = urllib.request.Request('https://www.python.org')
# response1 = urllib.request.urlopen(request5)
# print(response1.read().decode('utf-8'))

# url = 'http://httpbin.org/post'
# headers = {
#     'User-Agent': 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)',
#     'Host': 'httpbin.org'
# }
# dict = {'name': 'Germey'}
# data = bytes(parse.urlencode(dict), encoding='utf-8')
# print(data)
# req = urllib.request.Request(url=url, data=data, headers=headers, method='POST')
# response2 = urllib.request.urlopen(req)
# print(response2.read().decode('utf-8'))


# Handler
# 代理
# proxy_handler = urllib.request.ProxyHandler({'http': 'http://127.0.0.1:9743', 'https': 'https://127.0.0.1:9743'})
# opener = urllib.request.build_opener(proxy_handler)
# response3 = opener.open('http://www.baidu.com')
# print(response3.read())


# import http.cookiejar,urllib.request
#
# cookie = http.cookiejar.CookieJar()
# handler = urllib.request.HTTPCookieProcessor(cookie)
# opener = urllib.request.build_opener(handler)
# response4 = opener.open('http://ww.baidu.com')
# for i in cookie:
#     print(i.name + " = " + i.value)
#
#
# filename = 'cookie.txt'
# cookie = http.cookiejar.MozillaCookieJar(filename)
# handler = urllib.request.HTTPCookieProcessor(cookie)
# opener = urllib.request.build_opener(handler)
# respinse5 = opener.open('http://www.baidu.com')
# cookie.save(ignore_discard=True, ignore_expires=True)
#
# filename = 'cookie.txt'
# cookie = http.cookiejar.LWPCookieJar(filename)
# handler = urllib.request.HTTPCookieProcessor(cookie)
# opener = urllib.request.build_opener(handler)
# response6 = opener.open('http://www.baidu.com')
# cookie.save(ignore_discard=True, ignore_expires=True)
#
# cookie = http.cookiejar.LWPCookieJar()
# cookie.load('cookie.txt', ignore_discard=True, ignore_expires=True)
# handler = urllib.request.HTTPCookieProcessor(cookie)
# opener = urllib.request.build_opener(handler)
# response7 = opener.open('http://www.baidu.com')
# print(response7.read().decode('utf-8'))
#
# #异常处理
# try:
#     response_e1 = request.urlopen('http://cuiqingcai.com/index.html')
# except urllib.error.URLError as e:
#     print(e.reason)
#
# try:
#     request.urlopen('http://cuiqingcai.com/index.html')
# except urllib.error.URLError as e:
#     print(e.reason, e.code, e.headers, sep='\n')
# except urllib.error.URLError as e:
#     print(e.reason)
# else:
#     print('Request Successfully')
#
# try:
#     urlopen = urllib.request.urlopen('https://www.baidu.com')
# except urllib.error.URLError as e:
#     print(type(e.reason))
#     if isinstance(e.reason, socket.timeout):
#         print('Time_out')

# URL解析
res1 = urlparse('http://www.baidu.com/index.html;user?id=5#comment')
print(type(res1), res1)

res2 = urlparse('www.baidu.com/index.html;user?id=5#comment', scheme='https')
print(res2)

res3 = urlparse('http://www.baidu.com/index.html;user?id=5#comment', scheme='https')
print(res3)

res4 = urlparse('http://www.baidu.com/index.html;user?id=5#comment', allow_fragments=False)
# fragment为空 参数拼接到query里面去了
print(res4)

res5 = urlparse('http://www.baidu.com/index.html#comment', allow_fragments=False)
# fragment为空 参数拼接到path里面去了
print(res5)

# urlunparse
from urllib.parse import urlunparse
data = ['http', 'www.baidu.com', 'index.html', 'user', 'a=6', 'comment']
print(urlunparse(data))

#urljoin
from urllib.parse import urljoin

print(urljoin('http://www.baidu.com', 'FAQ.html'))
print(urljoin('http://www.baidu.com', 'https://cuiqingcai.com/FAQ.html')) # 以后面为基准
print(urljoin('http://www.baidu.com/about.html', 'https://cuiqingcai.com/FAQ.html'))
print(urljoin('http://www.baidu.com/about.html', 'https://cuiqingcai.com/FAQ.html?question=2'))
print(urljoin('http://www.baidu.com/?wb=abc', 'https://cuiqingcai.com/index.php'))
print(urljoin('http://www.baidu.com', '?category=2#comment'))
print(urljoin('www.baidu.com', '?category=2#comment'))
print(urljoin('www.baidu.com#comment', '?category=2'))


from urllib.parse import urlencode
params ={
    'name': 'germey',
    'age': 22
}
base_url = 'http://www.baidu.com?'
url = base_url+urlencode(params)
print(url)



