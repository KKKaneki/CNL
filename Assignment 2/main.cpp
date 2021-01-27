#include<iostream>
using namespace std;


int main(){
    string s;
    int data[17],receivedData[8];
    cout<<"Enter the data (4 bits) : ";
    cin>>data[7]>>data[6]>>data[5]>>data[3];

    data[1] = data[3]^data[5]^data[7];
    data[2] = data[3]^data[6]^data[7];
    data[4] = data[5]^data[6]^data[7];

    cout<<"Data after including parity bits : ";
    for(int i=1;i<=7;i++) cout<<data[i];
    cout<<"\n";

    cout<<"Enter the data received :";
    for(int i=7;i>=1;i--) cin>>receivedData[i];

    int firstBit = receivedData[1]^receivedData[3]^receivedData[5]^receivedData[7];
    int secondBit = receivedData[2]^receivedData[3]^receivedData[6]^receivedData[7];
    int thirdBit = receivedData[4]^receivedData[5]^receivedData[6]^receivedData[7];

    int result = (firstBit<<0) + (secondBit<<1) + (thirdBit<<2);
    //cout<<firstBit<<" "<<2*secondBit<<" "<<4*thirdBit<<endl;

    if(result!=0){
        cout<<"Error occured at bit :"<<result<<" from right."<<endl;
    } else {
        cout<<"Recieved Data correctly."<<endl;
        cout << "OUTPUT : " << data[7] << data[6] << data[5] << data[3];
    }

}


