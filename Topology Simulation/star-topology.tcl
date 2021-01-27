set ns [new Simulator]
set nf [open out.nam w]
$ns namtrace-all $nf

$ns color 1 Blue
$ns color 2 Red

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam out.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

$ns duplex-link $n1 $n0 1Mb 100ms DropTail
$ns duplex-link $n2 $n0 1Mb 100ms DropTail
$ns duplex-link $n0 $n3 1Mb 100ms DropTail
$ns duplex-link $n0 $n4 1Mb 100ms DropTail

$ns duplex-link-op $n1 $n0 orient right-down
$ns duplex-link-op $n2 $n0 orient right-up
$ns duplex-link-op $n0 $n3 orient right-up
$ns duplex-link-op $n0 $n4 orient right-down

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1

set cbr1 [new Application/Traffic/CBR]
$cbr1 set packetSize_ 500
$cbr1 set interval_ 0.100
$cbr1 attach-agent $udp1

set udp2 [new Agent/UDP]
$ns attach-agent $n2 $udp2

set cbr2 [new Application/Traffic/CBR]
$cbr2 set packetSize_ 500
$cbr2 set interval_ 0.100
$cbr2 attach-agent $udp2



set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.100
$cbr0 attach-agent $udp0


set null3 [new Agent/Null]
$ns attach-agent $n3 $null3

set null4 [new Agent/Null]
$ns attach-agent $n4 $null4


$ns connect $udp1 $null3
$ns connect $udp2 $null4

$udp1 set class_ 1
$udp2 set class_ 2


$ns at 1.0 "$cbr1 start"
$ns at 1.5 "$cbr2 start"
$ns at 3 "$cbr1 stop"
$ns at 3.5 "$cbr2 stop"
$ns at 4.0 "finish"
$ns run
	



