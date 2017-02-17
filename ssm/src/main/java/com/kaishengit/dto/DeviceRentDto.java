package com.kaishengit.dto;

import java.util.List;

public class DeviceRentDto {


    /**
     * deviceArray : [{"id":"1","name":"拖拉机","unit":"辆","price":"150","num":"1","total":150},{"id":"2","name":"挖掘机","unit":"辆","price":"300","num":"1","total":300}]
     * fileArray : ["1d77be14-7bbe-4007-a8d6-4e7bdc40b87a.mwb","d7be8e2d-3ca4-437a-b21c-245e482abc0e.jpg"]
     * companyName : 李四的公司
     * tel : 88899977
     * linkMan : 李四
     * cardNum : 123123
     * address : 人民路
     * fax : 0987-009
     * rentDate : 2017-02-17
     * backDate : 2017-02-19
     * totalDate : 2
     */

    private String companyName;
    private String tel;
    private String linkMan;
    private String cardNum;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDate;
    private List<DeviceArrayBean> deviceArray;
    private List<DocBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<DocBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class DeviceArrayBean {
        /**
         * id : 1
         * name : 拖拉机
         * unit : 辆
         * price : 150
         * num : 1
         * total : 150
         */

        private Integer id;
        private String name;
        private String unit;
        private Float price;
        private Integer num;
        private Float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    public static class DocBean {
        private String sourceName;
        private String newName;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
