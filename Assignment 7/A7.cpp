
#include <iostream>
#include<fstream>
#include <iomanip>
#include<string>
using namespace std;

int main() {
	string value, sr_no,time,source,destination,info,protocol,len;
	int count=-1,i=0;

	int choice;
	do
	{
		ifstream file("data.csv");
		count=-1;
		i=0;
	cout<<"\nEnter protocol "<<endl;
	cout<<"1.IP\n2.UDP\n3.TCP\n4.Ethernet\n5.Exit\n"<<endl;
	cin>>choice;
	string protocolChoice;
	switch(choice){
	case 1: protocolChoice="ICMPv6";
	break;
	case 2: protocolChoice="UDP";
	break;
	case 3: protocolChoice="TCP";
	break;
	case 4: protocolChoice="ARP";
	break;
	default: protocolChoice="ARP";
	break;
	}
	while(file.good())
	{
		getline(file,sr_no,',');
		getline(file,time,',');
		getline(file,source,',');
		getline(file,destination,',');
		getline(file,protocol,',');
		getline(file,len,',');
		getline(file,info,'\n');

		protocol=string(protocol,1,protocol.length()-2);

		if(protocol=="Protocol"||protocol==protocolChoice)
		{
			cout <<setw(6)<<left<<i++;
			cout <<setw(14)<<left<< string( time, 1, time.length()-2 );
			cout << setw(35)<<left<<string( source, 1, source.length()-2 );
			cout << setw(35)<<left<<string( destination, 1, destination.length()-2 );
			cout <<setw(10)<<left<<protocol<<"  ";
			cout <<setw(10)<<left<< string( len, 1, len.length()-2 );
			cout << string( info, 1, info.length()-2 )<<"\n";
			count++;
		}
	}
	file.close();
	cout<<"\nTotal Packet Count: "<<count;
	}while(choice!=5);
	return 0;
}

