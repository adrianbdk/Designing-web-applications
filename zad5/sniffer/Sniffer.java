//package sniffer;
//
//import org.pcap4j.core.*;
//import org.pcap4j.packet.IpPacket;
//import org.pcap4j.packet.TcpPacket;
//import org.pcap4j.packet.UdpPacket;
//
//public class Sniffer {
//    private PcapHandle pcapHandle = null;
//    private String filter = null;
//
//    public Sniffer(PcapHandle pcapHandle, String filter) {
//        this.pcapHandle = pcapHandle; this.filter = filter;
//    }
//
//    public void sniff() throws PcapNativeException, InterruptedException, NotOpenException {
//        if (pcapHandle == null){
//            System.out.println("Set handle first");
//            return;
//        }
//
//        pcapHandle.loop(0, (PacketListener) packet -> {
//            System.out.println("Packet received: ");
//            System.out.println("Time: " + pcapHandle.getTimestamp());
//            System.out.println("Packet length: " + packet.getHeader().length());
//            String protocolName = packet.get(IpPacket.class).getHeader().getProtocol().name();
//            System.out.println("|Protocol Name: " + protocolName + "|");
//            System.out.println("|Source Address: " + packet.get(IpPacket.class).getHeader().getSrcAddr() + "|");
//            System.out.println("|Destination Address: " + packet.get(IpPacket.class).getHeader().getDstAddr() + "|");
//            if(filter.equals("tcp") || protocolName.equals("TCP")) {
//                System.out.println("|Source Port: " + packet.get(TcpPacket.class).getHeader().getSrcPort() + "|");
//                System.out.println("|Destination Port: " + packet.get(TcpPacket.class).getHeader().getDstPort() + "|\n\n");
//            }else{
//                System.out.println("|Source Port: " + packet.get(UdpPacket.class).getHeader().getSrcPort() + "|");
//                System.out.println("|Destination Port: " + packet.get(UdpPacket.class).getHeader().getDstPort() + "|\n\n");
//            }
//        });
//    }
//}