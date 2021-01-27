#include <arpa/inet.h> 
#include <netinet/in.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <sys/socket.h> 
#include <sys/types.h> 
#include <unistd.h> 
  
#define IP_PROTOCOL 0 
#define IP_ADDRESS "127.0.0.1" // localhost 
#define PORT_NO 15050 
#define NET_BUF_SIZE 700000
#define cipherKey 'S' 
#define sendrecvflag 0 
  
// function to clear buffer 
void clearBuf(char* b) 
{ 
    int i; 
    for (i = 0; i < NET_BUF_SIZE; i++) 
        b[i] = '\0'; 
} 

// driver code 
int main() 
{ 
    int sockfd, nBytes; 
    struct sockaddr_in addr_con; 
    socklen_t addrlen = sizeof(addr_con); 
    char net_buf[NET_BUF_SIZE]; 
    char file_buf[NET_BUF_SIZE];
    FILE* fp; 
  
    // socket() 
    sockfd = socket(AF_INET, SOCK_DGRAM, 
                    IP_PROTOCOL); 
  
    addr_con.sin_family = AF_INET; 
    addr_con.sin_port = htons(PORT_NO); 
    addr_con.sin_addr.s_addr = inet_addr(IP_ADDRESS); 
    
    if (sockfd < 0) 
        printf("\nClient not created\n"); 
    else
        printf("\nClient Socket created\n"); 
  
     while (1) { 
        printf("\nPlease enter file name to receive:\n"); 
        scanf("%s", net_buf);
        sendto(sockfd,net_buf,1024,sendrecvflag,(struct sockaddr*)&addr_con,addrlen);
        
        clearBuf(file_buf);
        FILE* p = fopen(net_buf,"r"); 
        if(p == NULL) {
            printf("File Not Found\n");
            continue;
        }
       
        fseek(p,0,SEEK_END);
        size_t afsize = ftell(p);
        fseek(p,0,SEEK_SET);

       
        fread(file_buf,afsize,1,p);

        printf("%lu",afsize);
        sendto(sockfd, file_buf, afsize, 
               0, (struct sockaddr*)&addr_con, 
               addrlen); 
    } 
    return 0; 
}