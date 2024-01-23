/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author owner
 */
public class transaksi {
    private int transaksiId;
    private int totalHarga;
    private int eventId;
    private int userId;
    private String statusTransaksi;

    public transaksi(int transaksiId, int totalHarga, int eventId, int userId, String statusTransaksi) {
        this.transaksiId = transaksiId;
        this.totalHarga = totalHarga;
        this.eventId = eventId;
        this.userId = userId;
        this.statusTransaksi = statusTransaksi;
    }

    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
    
    
}
