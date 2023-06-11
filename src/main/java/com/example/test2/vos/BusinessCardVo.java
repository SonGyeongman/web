package com.example.test2.vos;

import com.example.test2.entities.BusinessCardEntity;
import com.example.test2.enums.BusinessCardEnum;
import com.example.test2.interfaces.IResult;

import java.util.ArrayList;

public class BusinessCardVo extends BusinessCardEntity implements IResult<BusinessCardEnum> {
    private BusinessCardEnum result;

    private int articleCountPerPage;
    //요청한 페이지
    private int requestPage;
    //시작 페이지
    private int startPage;
    //끝 페이지
    private int endPage;
    //최소 페이지 기본값 1페이지
    private int minPage = 1;
    //최대 페이지
    private int maxPage;

    private String searchSelect;

    private String search;

    private String check;

    private BusinessCardEntity[] businessCardEntity;

    public BusinessCardEntity[] getBusinessCardEntity() {
        return businessCardEntity;
    }

    public void setBusinessCardEntity(BusinessCardEntity[] businessCardEntity) {
        this.businessCardEntity = businessCardEntity;
    }

    public int getArticleCountPerPage() {
        return articleCountPerPage;
    }

    public void setArticleCountPerPage(int articleCountPerPage) {
        this.articleCountPerPage = articleCountPerPage;
    }

    public int getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(int requestPage) {
        this.requestPage = requestPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getMinPage() {
        return minPage;
    }

    public void setMinPage(int minPage) {
        this.minPage = minPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public String getSearchSelect() {
        return searchSelect;
    }

    public void setSearchSelect(String searchSelect) {
        this.searchSelect = searchSelect;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public BusinessCardEnum getResult() {
        return result;
    }

    @Override
    public void setResult(BusinessCardEnum result) {
        this.result = result;
    }
}
