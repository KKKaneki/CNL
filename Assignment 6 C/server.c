#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<sys/socket.h>
#include <unistd.h>


int main(){
    int sockfd;
    struct sockaddr_in server_sock;
    server_sock.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_sock.sin_family = AF_INET;
    server_sock.sin_port = 4000;
    
    sockfd = socket(AF_INET,SOCK_STREAM,0);
    if( sockfd > 0) {
        printf("Socket created\n");
    } else {
        printf("Socket Failed\n");
    }

  

    if(bind(sockfd,( struct sockaddr*)& server_sock,sizeof(server_sock)) == 0) {
        printf("Bind Successfully\n");
    } else {
       printf("Bind Unsuccessfully\n");
    }
    socklen_t addrlen = sizeof(struct sockaddr_in);
        while(1){

        if(listen(sockfd,5) == 0) {
            printf("Listening...\n");
    
            int confd;
            struct sockaddr_in client_sock;

            socklen_t len = sizeof(client_sock);
                if((confd=accept(sockfd,( struct sockaddr*)&client_sock,&len)) > 0){
                    printf("Accepted Connection\n");
                    
                    int operatorChoice;
                    double result,operand1, operand2;
                    read(confd,&operatorChoice,sizeof(operatorChoice));
                    read(confd,&operand1,sizeof(operand1));
                    read(confd,&operand2,sizeof(operand2));

                    // printf("Operator : %c ",operatorChoice);
                    switch(operatorChoice) {
                        case 1 : result = operand1 + operand2;
                                break;
                        case 2 : result = operand1 - operand2;
                                break;
                        case 3 : result = operand1 / operand2;
                                break;
                        case 4 : result = operand1 * operand2;
                                break;
                    }
                    write(confd,&result,sizeof(result));
                } else {
                    printf("Connection refused\n");
                }
        }
            
    }
    close(sockfd);
    return 0;
}