#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<netinet/in.h> 
#include <unistd.h>
#include<string.h> 
#include<strings.h>
#include <arpa/inet.h>

int main(){
    int sockfd,connfd;
    struct sockaddr_in serveraddr;
    serveraddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    serveraddr.sin_family = AF_INET;
    serveraddr.sin_port = 4000;
    while(1){
    sockfd = socket(AF_INET,SOCK_STREAM,0);

        if( sockfd > 0 ) {
            printf("Client Socket Established...\n");
            socklen_t len = sizeof(serveraddr);
                connfd = connect(sockfd,(struct sockaddr*)&serveraddr,len);
                if( connfd == 0 ) {
                    printf("Client Connection Established...\n");
                        int operatorChoice;
                        double result,op1,op2;
                        // MENU 
                        printf("1. Addition \n2. Subtraction\n3. Division\n4. Multiplication");

                        printf("\n\nEnter the operator choice : ");
                        scanf("%d",&operatorChoice);
                        printf("Enter the first operand :");
                        scanf("%lf",&op1);
                        while(1){
                            printf("Enter the second operand :");
                            scanf("%lf",&op2);

                            if(operatorChoice == 3 && op2 == 0) {
                                printf("Cannot divide by zero\nEnter Again\n");
                            } else break;
                        }   
                    
                    // printf("The operator : %c ", operator);
                        write(sockfd,&operatorChoice,sizeof(operatorChoice));
                        write(sockfd,&op1,sizeof(op1));
                        write(sockfd,&op2,sizeof(op2));

                        read(sockfd,&result,sizeof(result));
                        printf("The result : %lf \n", result);
                
                } else {
                    printf("Client Connection Failed...\n");
                }
    
        } else {
        printf("Client Socket Failed...\n");
        }
    }
    close(sockfd);
    return 0;
}