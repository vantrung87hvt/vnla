/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Laptop extends Products {

    private String cpu_tech;
    private String cpu_speed;
    private String cpu_kind;
    private String cache;
    private String cpu_info;
    private String cpu_manu;
    private String main;
    private String main_manu;
    private String main_bus;
    private String main_ram;
    private String ram;
    private String ram_bus;
    private String ram_cap;
    private String ram_tech;
    private String hdd;
    private String hdd_manu;
    private String hdd_cap;
    private String hdd_speed;
    private String display;
    private String display_size;
    private String graphic;
    private String graphic_info;
    private String sound;
    private String sound_info;
    private String compactdisk;
    private String disk_info;
    private String tinhnangmorong;
    private String network;
    private String network_info;
    private String card_reader;
    private String webcam;
    private String os;
    private String battery;
    private String phukien;
    private String mausac;
    private String trongluong;
    private String thongtinmota;
    private int soluotxem;
    private double gia;
    private String type;
    private String classLaptop;

    public void setClassLaptop(String classLaptop) {
        this.classLaptop = classLaptop;
    }

    public String getClassLaptop() {
        return this.classLaptop;
    }

    public void setAtt(String cpu_tech,
            String cpu_speed,
            String cpu_kind,
            String cache,
            String cpu_info,
            String cpu_manu,
            String main,
            String main_manu,
            String main_bus,
            String main_ram,
            String ram,
            String ram_bus,
            String ram_cap,
            String ram_tech,
            String hdd,
            String hdd_manu,
            String hdd_cap,
            String hdd_speed,
            String display,
            String display_size,
            String graphic,
            String graphic_info,
            String sound,
            String sound_info,
            String compactdisk,
            String disk_info,
            String tinhnangmorong,
            String network,
            String network_info,
            String card_reader,
            String webcam,
            String os,
            String battery,
            String phukien,
            String mausac,
            String trongluong,
            String thongtinmota,
            String ma_dongsanpham,
            String ma_nhasx,
            String quocgia,
            int soluong,
            String image,
            int soluongdat,
            String maloai) {
        this.setBattery(battery);
        this.setCache(cache);
        this.setCard_reader(card_reader);
        this.setCompactdisk(compactdisk);
        this.setCpu_info(cpu_info);
        this.setCpu_kind(cpu_kind);
        this.setCpu_manu(cpu_manu);
        this.setCpu_speed(cpu_speed);
        this.setCpu_tech(cpu_tech);
        this.setDisk_info(disk_info);
        this.setDisplay(display);
        this.setDisplay_size(display_size);
        this.setGraphic(graphic);
        this.setGraphic_info(graphic_info);
        this.setHdd(hdd);
        this.setHdd_cap(hdd_cap);
        this.setHdd_manu(hdd_manu);
        this.setHdd_speed(hdd_speed);
        this.setImage(image);
        this.setMadong(ma_dongsanpham);
        this.setMain(main);
        this.setMain_bus(main_bus);
        this.setMain_manu(main_manu);
        this.setMain_ram(main_ram);
        this.setMaloai(maloai);
        this.setMausac(mausac);
        this.setNetwork(network);
        this.setNetwork_info(network_info);
        this.setOs(os);
        this.setPhukien(phukien);
        this.setQuocgia(quocgia);
        this.setRam(ram);
        this.setRam_bus(ram_bus);
        this.setRam_cap(ram_cap);
        this.setRam_tech(ram_tech);
        this.setSoluong(soluong);
        this.setSoluongdat(soluongdat);
        this.setSound(sound);
        this.setSound_info(sound_info);
        this.setThongtinmota(thongtinmota);
        this.setTrongluong(trongluong);
        this.setThongtinmota(thongtinmota);
        this.setWebcam(webcam);
    }

    public String setMadong(String ma_dongsanpham) {
        return this.ma_dongsanpham = ma_dongsanpham;
    }

    public String getMadong() {
        return ma_dongsanpham;
    }

    public String setQuocgia(String quocgia) {
        return this.quocgia = quocgia;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public String setMota(String mota) {
        return this.mota = mota;
    }

    public String getMota() {
        return this.mota;
    }

    public int setSoluong(int soluong) {
        return this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public String setImage(String image) {
        return this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public int setSoluongdat(int soluongdat) {
        return this.soluongdat = soluongdat;
    }

    public int getSoluongdat() {
        return this.soluongdat;
    }

    public String setMaloai(String maloai) {
        return this.maloai = maloai;
    }

    public String getMaloai() {
        return maloai;
    }

    public int setBaohanh(int baohanh) {
        return this.baohanh = baohanh;
    }

    public int getBaohanh() {
        return baohanh;
    }

     public int setSoluotxem(int soluongxem) {
        return this.soluotxem = soluongxem;
    }

    public int getSoluotxem() {
        return soluotxem;
    }

    public String setCpu_tech(String cpu_tech) {
        return this.cpu_tech = cpu_tech;
    }

    public String getCpu_tech() {
        return cpu_tech;
    }

    public String setCpu_speed(String cpu_speed) {
        return this.cpu_speed = cpu_speed;
    }

    public String getCpu_speed() {
        return cpu_speed;
    }

    public String setCpu_kind(String cpu_kind) {
        return this.cpu_kind = cpu_kind;
    }

    public String getCpu_kind() {
        return cpu_kind;
    }

    public String setCache(String cache) {
        return this.cache = cache;
    }

    public String getCache() {
        return cache;
    }

    public String setCpu_info(String cpu_info) {
        return this.cpu_info = cpu_info;
    }

    public String getCpu_info() {
        return cpu_info;
    }

    public String setCpu_manu(String cpu_manu) {
        return this.cpu_manu = cpu_manu;
    }

    public String getCpu_manu() {
        return cpu_manu;
    }

    public String setMain(String main) {
        return this.main = main;
    }

    public String getMain() {
        return main;
    }

    public String setMain_manu(String main_manu) {
        return this.main_manu = main_manu;
    }

    public String getMain_manu() {
        return main_manu;
    }

    public String setMain_bus(String main_bus) {
        return this.main_bus = main_bus;
    }

    public String getMain_bus() {
        return main_bus;
    }

    public String setMain_ram(String main_ram) {
        return this.main_ram = main_ram;
    }

    public String getMain_ram() {
        return main_ram;
    }

    public String setRam(String ram) {
        return this.ram = ram;
    }

    public String getRam() {
        return ram;
    }

    public String setRam_bus(String ram_bus) {
        return this.ram_bus = ram_bus;
    }

    public String getRam_bus() {
        return ram_bus;
    }

    public String setRam_cap(String ram_cap) {
        return this.ram_cap = ram_cap;
    }

    public String getRam_cap() {
        return ram_cap;
    }

    public String setRam_tech(String ram_tech) {
        return this.ram_tech = ram_tech;
    }

    public String getRam_tech() {
        return ram_tech;
    }

    public String setHdd(String hdd) {
        return this.hdd = hdd;
    }

    public String getHdd() {
        return hdd;
    }

    public String setHdd_cap(String hdd_cap) {
        return this.hdd_cap = hdd_cap;
    }

    public String getHdd_cap() {
        return hdd_cap;
    }

    public String setHdd_manu(String hdd_manu) {
        return this.hdd_manu = hdd_manu;
    }

    public String getHdd_manu() {
        return hdd_manu;
    }

    public String setHdd_speed(String hdd_speed) {
        return this.hdd_speed = hdd_speed;
    }

    public String getHdd_speed() {
        return hdd_speed;
    }

    public String setDisplay(String display) {
        return this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public String setDisplay_size(String display_size) {
        return this.display_size = display_size;
    }

    public String getDisplay_size() {
        return display_size;
    }

    public String setGraphic(String graphic) {
        return this.graphic = graphic;
    }

    public String getGraphic() {
        return graphic;
    }

    public String setGraphic_info(String graphic_info) {
        return this.graphic_info = graphic_info;
    }

    public String getGraphic_info() {
        return graphic_info;
    }

    public String setSound(String sound) {
        return this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public String setSound_info(String sound_info) {
        return this.sound_info = sound_info;
    }

    public String getSound_info() {
        return sound_info;
    }

    public String setCompactdisk(String compactdisk) {
        return this.compactdisk = compactdisk;
    }

    public double setGia(double gia) {
        return this.gia = gia;
    }

    //....................
    public String getCompactdisk() {
        return compactdisk;
    }

    public String setDisk_info(String disk_info) {
        return this.disk_info = disk_info;
    }

    public String getDisk_info() {
        return disk_info;
    }

    public String setTinhnangmorong(String tinhnangmorong) {
        return this.tinhnangmorong = tinhnangmorong;
    }

    public String getTinhnangmorong() {
        return tinhnangmorong;
    }

    public String setNetwork(String network) {
        return this.network = network;
    }

    public String getNetwork() {
        return network;
    }

    public String setNetwork_info(String network_info) {
        return this.network_info = network_info;
    }

    public String getNetwork_info() {
        return network_info;
    }

    public String setCard_reader(String card_reader) {
        return this.card_reader = card_reader;
    }

    public String getCard_reader() {
        return card_reader;
    }

    public String setWebcam(String webcam) {
        return this.webcam = webcam;
    }

    public String getWebcam() {
        return webcam;
    }

    public String setBattery(String battery) {
        return this.battery = battery;
    }

    public String getBattery() {
        return battery;
    }

    public String setOs(String os) {
        return this.os = os;
    }

    public String getOs() {
        return os;
    }

    public String setPhukien(String phukien) {
        return this.phukien = phukien;
    }

    public String getPhukien() {
        return phukien;
    }

    public String setMausac(String mausac) {
        return this.mausac = mausac;
    }

    public String getMausac() {
        return mausac;
    }

    public String setTrongluong(String trongluong) {
        return this.trongluong = trongluong;
    }

    public String getTrongluong() {
        return trongluong;
    }

    public String setThongtinmota(String thongtinmota) {
        return this.thongtinmota = thongtinmota;
    }

    public String getThongtinmota() {
        return thongtinmota;
    }

    public String setNhasx(String nhasx) {
        return this.nhasx = nhasx;
    }

    public String getNhasx() {
        return nhasx;
    }

    public double getGia() {
        return gia;
    }

    public String setType(String type) {
        return this.type=type;
    }
    public String getType() {
        return type;
    }
}
