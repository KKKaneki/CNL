#include <arpa/inet.h> 
#include <netinet/in.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <sys/socket.h> 
#include <sys/types.h> 
#include <unistd.h> 


  
#define IP_PROTOCOL 0 
#define PORT_NO 15050 
#define cipherKey 'S' 
#define sendrecvflag 0 
#define nofile "File Not Found!" 
#define NET_BUF_SIZE 700005


  
// driver code 
int main() 
{ 
    int sockfd, bytes;
    struct sockaddr_in addr_con;
    socklen_t addrlen = sizeof(addr_con);

    addr_con.sin_family = AF_INET;
    addr_con.sin_port = htons(PORT_NO);
    addr_con.sin_addr.s_addr = INADDR_ANY;

   
  
    sockfd = socket(AF_INET,SOCK_DGRAM,IP_PROTOCOL);

    if( sockfd < 0 ) {
        printf("\n Socket Failed\n");
    } else {
        printf("\nSocket Created\n");
    }
    
    if(bind(sockfd,(const struct sockaddr *)&addr_con,sizeof(addr_con)) == 0) {
        printf("\nBind Success\n");

        while(1){
            char net_buf[NET_BUF_SIZE];
            char file_name[NET_BUF_SIZE];

            printf("Waiting for client...\n");


            bytes = recvfrom(sockfd,file_name,1024,sendrecvflag,(struct sockaddr*)&addr_con, &addrlen);
            FILE* fp = fopen(file_name,"w");
            size_t f;
            printf("FileName: %s\n",file_name);
            f = recvfrom(sockfd,net_buf,700000,sendrecvflag,(struct sockaddr*)&addr_con, &addrlen);


            printf("%lu\n",f);
            if((fwrite(net_buf,f,1,fp)) < 0) {
                printf("\nFile Transfer failed!\n"); 
            } else {
                printf("\nFile Successfully Transferred!\n");
            }
            memset(file_name, '\0', sizeof(file_name));
            if (fp != NULL) 
                fclose(fp); 
        }

    } else {
        printf("\nBind Failed\n");
    }

    
    return 0; 
} 