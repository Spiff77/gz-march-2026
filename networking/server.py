import socket                          # import the socket module
import _thread

client_list = []                       # stores all the clients connected to the server

def on_new_client(clientsocket,addr):  # addr is the address of the client
    while True:                        # always listen for incoming messages from the client  
        msg = clientsocket.recv(1024) 
        if msg:
            print("Sending data back to the client...")
            print("Received from client: " + msg.decode())
            for client in client_list:
                if client[0] != clientsocket:     # don't send back to the client who sent the message
                    # broadcast the message to other client
                    print('Sending message to ',client[1])
                    client[0].send(str.encode(f'{addr} > {msg.decode()}'))                    
        else:
            print ("Client has disconnected.")
            break
    clientsocket.close()

s = socket.socket()                  # Create a socket object
host = socket.gethostbyname("")      # Get local machine name
port = 8080                          # port to connect

print("Server started!")
print("Waiting for clients...")

s.bind((host, port))                 # Bind to the port
s.listen(5)                          # wait for client connection

while True:
    c, addr = s.accept()             # Establish connection with client    
    client_info = [c, addr]          # save the client details into the list
    client_list.append(client_info)    
    print("Connected from: ", addr)

    # spin off a new thread to handle the client    
    _thread.start_new_thread(on_new_client,(c,addr))       

s.close()
