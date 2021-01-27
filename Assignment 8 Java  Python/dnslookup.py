import socket

while(1):
    
    try:
        print("\nDNS to IP")
        domain = input("Enter Domain Name : ")
        ip = socket.gethostbyname(domain)
        print("Domain Name {0} IP is {1} ".format(domain,ip))
    except Exception:
        print("Error Occured in DNS TO IP")

    try:
        print("IP to DNS\n")
        ip = input("Enter IP address : ")

        domain_name = socket.gethostbyaddr(ip)[0]

        print(" IP {0} Domain Name is {1} ".format(ip,domain_name))
    except Exception:
        print("Error Occurred\n")

