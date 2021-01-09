//package sniffer;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//import org.apache.log4j.BasicConfigurator;
//import org.pcap4j.core.NotOpenException;
//import org.pcap4j.core.PcapHandle;
//import org.pcap4j.core.PcapNativeException;
//import org.pcap4j.core.PcapNetworkInterface;
//import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
//import org.pcap4j.core.PcapAddress;
//import org.pcap4j.util.NifSelector;
//import org.pcap4j.core.BpfProgram.BpfCompileMode;
//
//
//public class Main {
//    private static final int snapshotLength = 65536;
//    private static final int readTimeout = 10;
//    public static void main(String[] args) {
//        try {
//            Main main = new Main();
//            BasicConfigurator.configure();
//            PcapNetworkInterface networkInterface = main.chooseNetworkInterface();
//            printInterfaceInformation(networkInterface);
//            main.pcapHandler(networkInterface);
//
//        } catch (IOException | PcapNativeException | NotOpenException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public PcapNetworkInterface chooseNetworkInterface() throws IOException {
//        PcapNetworkInterface networkInterface = new NifSelector().selectNetworkInterface();
//        if (networkInterface == null){
//            System.out.println("No interface chosen");
//        }
//        return networkInterface;
//    }
//
//    void pcapHandler(PcapNetworkInterface networkInterface) throws PcapNativeException, NotOpenException, InterruptedException {
//        String filter = getFilterFromUser();
//        PcapHandle pcapHandle = networkInterface.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);
//        pcapHandle.setFilter(filter, BpfCompileMode.OPTIMIZE);
//
//        new Sniffer(pcapHandle, filter).sniff();
//        pcapHandle.close();
//    }
//
//    public String getFilterFromUser(){
//        System.out.println("Set filter: ");
//        Scanner scanner = new Scanner(System.in);
//        String filter = scanner.nextLine().toLowerCase();
//        scanner.close();
//        return filter;
//    }
//
//    public static void printInterfaceInformation(PcapNetworkInterface networkInterface){
//        System.out.println("Name: " + networkInterface.getName() +
//                "\n   Description: " + networkInterface.getDescription() +
//                "\n   Is up: " + networkInterface.isUp() +
//                "\n   Addresses: ");
//
//        for (PcapAddress address : networkInterface.getAddresses()){
//            System.out.println("Address: " + address.getAddress() +
//                    "\n   Mask: " + address.getNetmask() +
//                    "\n   Broadcast address: " + address.getBroadcastAddress() +
//                    "\n   Destination address: " + address.getDestinationAddress());
//        }
//    }
//}
