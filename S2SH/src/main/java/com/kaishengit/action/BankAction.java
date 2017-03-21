package com.kaishengit.action;

import com.kaishengit.pojo.Bank;
import com.kaishengit.service.BankService;
import com.kaishengit.util.QueryParam;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Namespace("/bank")
public class BankAction extends BaseAction {

    @Autowired
    private BankService bankService;

    private List<Bank> bankList;
    private Bank bank;
    private Integer id;

    @Action("list")
    @Override
    public String execute() throws Exception {
        List<QueryParam> queryParamList = QueryParam.builderQueryParamByRequest(getHttpRequest());
        bankList = bankService.findByQueryParam(queryParamList);
        return SUCCESS;
    }

    @Action("new")
    public String newBank() {
        return SUCCESS;
    }

    @Action(value = "save",results = {
            @Result(type = "redirectAction",params = {"actionName","list"})
    })
    public String saveBank() {
        bankService.save(bank);
        return SUCCESS;
    }
    @Action(value = "del",results = {
            @Result(type = "redirectAction",params = {"actionName","list"})
    })
    public String del() {
        bankService.del(id);
        return SUCCESS;
    }
    @Action("edit")
    public String edit() {
        bank = bankService.findById(id);
        return SUCCESS;
    }


    //get set

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
