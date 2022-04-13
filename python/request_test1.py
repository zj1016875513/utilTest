import requests

# response = requests.get('https://www.baidu.com/')
# print(type(response))
# print(response.status_code)
# print(type(response.text))
# print(response.text)
# print(response.cookies)
#
# requests.post('http://httpbin.org/post')
# requests.put('http://httpbin.org/put')
# requests.delete('http://httpbin.org/delete')
# requests.head('http://httpbin.org/get')
# requests.options('http://httpbin.org/get')
#
# response = requests.get('http://httpbin.org/get')
# print(response.text)
# response = requests.get('http://httpbin.org/get?name=germey&age=22')
# print(response.text)
#
# data = {
#     'name': 'germey',
#     'age': 22
# }
# response = requests.get('http://httpbin.org/get',params=data)
# print(response.text)
#
# # 解析json
# response = requests.get('http://httpbin.org/get')
# print(response.text)
# print(response.json())
# print(type(response.json()))
#
# # 获取二进制数据
# response = requests.get("https://github.com/favicon.ico")
# print(type(response.text), type(response.content))
# print(response.text)
# print(response.content)

# response = requests.get('https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png')
# with open('baidu.png', 'wb')as f:
#     f.write(response.content)
#     f.close()

# 添加headers
# response = requests.get('https://www.zhihu.com/explore')
# print(response.text)

# headers = {
#     'User-Agent': 'Mozilla/5.0 (Macintosh; Inerl Mac OS X10_11_4) '
# }
# response = requests.get("https://www.zhihu.com/explore", headers=headers)
# print(response.text)


# 基本POST 请求
# data = {'name': 'germey', 'age': 22}
# response = requests.post("http://httpbin.org/post", data=data)
# print(response.text)

# data = {'name': 'germey', 'age': 22}
# headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Inerl Mac OS X10_11_4) '}
# response = requests.post("http://httpbin.org/post", data=data, headers=headers)
# print(response.json())


# 响应
# response属性
response = requests.get('http://www.jianshu.com')
print(type(response.status_code), response.status_code)
print(type(response.headers), response.headers)
print(type(response.cookies), response.cookies)
print(type(response.url), response.url)
print(type(response.history), response.history)

# 状态码判断
response = requests.get('http://www.jianshu.com/hello.html')
exit() if not response.status_code == requests.codes.ok else print('Request Successfully')
print(response.status_code == requests.codes.ok)

response = requests.get('http://www.jianshu.com')
exit() if not response.status_code == 200 else print('Request Successfully')


# 高级操作
# 文件上传
files = {'file': open('baidu.png', 'rb')}
response = requests.post('http://httpbin.org/post', files=files)
print(response.text)

# 获取cookie
response = requests.get('https://www.baidu.com')
print(response.cookies)
for key, value in response.cookies.items():
    print(key + '=' +value)

# 会话维持
requests.get('http://httpbin.org/cookies/set/number/123456789')
response = requests.get('http://httpbin.org/cookies')
print(response.text)

s = requests.Session()
s.get('http://httpbin.org/cookies/set/number/123456789')
response = s.get('http://httpbin.org/cookies')
print(response.text)


# 证书验证
response = requests.get('https://www.12306.cn')
print(response.status_code)


response = requests.get('https://www.12306.cn', verify=False)
print(response.status_code)

response = requests.get('https://www.12306.cn', cert=('/path/server.crt', '/path/key'))
print(response.status_code)

# 代理设置
proxies = {"http": "http://user:password@127.0.0.1:9743/"}
requests.get('https://www.taobao.com',proxies=proxies)
print(response.status_code)

proxies = {"http": "socks5://127.0.0.1:9742/",
           "https": "socks5://127.0.0.1:9742"}
requests.get('https://www.taobao.com',proxies=proxies)
print(response.status_code)


# 超时设置
response = requests.get('http://httpbin.org/get', timeout=1)
print(response.status_code)

# 认证设置
from requests.auth import HTTPBasicAuth

r = requests.get("http://120.27.34.24:9001", auth=HTTPBasicAuth('user', '123'))
print(r.status_code)

r = requests.get("http://120.27.34.24:9001", auth=('user', '123'))
print(r.status_code)


# 异常处理
from requests.exceptions import ReadTimeout, HTTPError, RequestException
try:
    response = requests.get("http://httpbin.org/get", timeout=0.5)
    print(response.status_code)
except ReadTimeout:
    print('Timeout')
except HTTPError:
    print('Http error')
except RequestException:
    print('Error')

# 100:('continue',),
# 101:('switching_protocols',),
# 102:('processing',),
# 103:('checkpoint',),
# 122:('url_too_long','request_url_too_long'),
# 200:('ok','okay','all_ok','all_okay','all_good','\\o/','√'),
# 201:('created',),
# 202:('accepted',),
# 203:('non_authoritative_info','non_authoritatlve_information'),
# 204:('no_content',),
# 205:('reset_content','reset'),
# 206:('partial_content','reset'),
# 207:('multi_status','multiple_status','multi_stati','multiple_stati'),
# 208:('already_reported',),
# 226:('im_used',),
#
# #Redirection
# 300:('multiple_choices',),
# 301:('moved_permanently','moved','\\o-'),
# 302:('found',),
# 303:('see_other','other'),
# 304:('not_modified',),
# 305:('use_porxy',),
# 306:('switch_porxy',),
# 307:('temporary_readirect','temporary_moved','temporary'),
# 308:('permanent_redirect','resume_incomplete','resume'),# These 2 to be removed in 3.0
#
# #Client Error
# 400:('',),
# 401:('',),
# 402:('',),
# 403:('',),
# 404:('',),
# 405:('',),
# 406:('',),
# 407:('',),
# 408:('',),
# 409:('',),
# 410:('',),
# 411:('',),
# 412:('',),
# 413:('',),
# 414:('',),
# 415:('',),
# 416:('',),
# 417:('',),
# 418:('',),
# 421:('',),
# 422:('',),
# 423:('',),
# 424:('',),
# 425:('',),
# 426:('',),
# 428:('',),
# 429:('',),
# 431:('',),
# 444:('',),
# 449:('',),
# 450:('',),
# 451:('',),
# 499:('',),
#
# #Server Error
# 500:('',),
# 501:('',),
# 502:('bad_gateway',),
# 503:('',),
# 504:('',),
# 505:('',),
# 506:('',),
# 507:('',),
# 508:('',),
# 509:('',),
# 510:('',),
# 511:('',),









