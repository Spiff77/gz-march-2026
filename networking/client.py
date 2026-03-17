import socket
import _thread

def on_receive_msg(s):
    while True:
        print(s.recv(1024).decode())

s = socket.socket()
host = socket.gethostbyname("127.0.0.1")                # ip address of server
port = 8080

s.connect((host, port))                        # connect to the server
_thread.start_new_thread(on_receive_msg,(s,))  # listen from server

while True:
    toSend = input()
    s.send(toSend.encode())   
    
s.close()
