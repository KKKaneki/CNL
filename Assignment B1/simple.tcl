set ns [new Simulator] 
$ns color 1 GREEN 
$ns color 2 RED 

set nf [open out.name w] 
$ns namtrace-all $nf
proc finish {} { 
	global ns nf 
	$ns flush-trace 
	close $nf 
	exec nam out.name &
	exit 0 
} 

set n0 [$ns node] 
set n1 [$ns node] 
set n2 [$ns node] 

$ns duplex-link $n0 $n1 2Mb 10ms DropTail 
$ns duplex-link $n1 $n2 1.7Mb 20ms DropTail 


$ns duplex-link-op $n0 $n1 orient right-down 
$ns duplex-link-op $n1 $n2 orient right 

$ns duplex-link-op $n1 $n2 queuePos 0.5 

set tcp [new Agent/TCP] 
$tcp set class_ 2 
$ns attach-agent $n0 $tcp 

set sink [new Agent/TCPSink] 
$ns attach-agent $n2 $sink 
$ns connect $tcp $sink 
$tcp set fid_ 1 

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP




$ns at 1.0 "$ftp start" 
$ns at 4.5 "$ftp stop" 

$ns at 4.5 "$ns detach-agent $n0 $tcp ; $ns detach-agent $n2 $sink ;" 

$ns at 5.0 "finish" 


$ns run


