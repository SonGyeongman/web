package com.example.test2.services;

import com.example.test2.entities.BusinessCardEntity;
import com.example.test2.mappers.IMainMapper;
import com.example.test2.vos.BusinessCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final IMainMapper mainMapper;

    @Autowired
    public MainService(IMainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    public BusinessCardVo getABusinessCard(int userId, int page){
        BusinessCardVo businessCardVo = new BusinessCardVo();
        businessCardVo.setArticleCountPerPage(10);
        businessCardVo.setRequestPage(page);
        //현재 페이지에서 앞뒤로 나타낼 페이지 범위
        final int pageRangeFactor = 4;
        //한페이지에 표시할 article 수
        int articleCountPerPage = businessCardVo.getArticleCountPerPage();
        //article 총 갯수
        int totalArticleCount = this.mainMapper.selectTotalCount(userId);
        //클라이언트가 요청한 현재 페이지
        int requestPage = businessCardVo.getRequestPage();
        //최소 페이지, 1페이지가 최소 페이지 이므로 1로 초기화
        int minPage = 1;
        //최대 페이지, ex) 23 / 10 + {(23 % 10) ==> 3이므로 == 1} == 3페이지 && 20 / 10 + {(20 % 10) ==> 0이므로 == 0} == 2페이지
        int maxPage = Math.max(minPage, totalArticleCount / articleCountPerPage + (totalArticleCount % articleCountPerPage == 0 ? 0 : 1));
        //requestPage - pageRangeFactor가 음수이면 minPage 그대로 양수일때 부터 바끰
        int startPage = Math.max(minPage, requestPage - pageRangeFactor);
        //requestPage + pageRangeFactor 값이 maxPage보다 크는 걸 방지
        int endPage = Math.min(maxPage, requestPage + pageRangeFactor);
        businessCardVo.setArticleCountPerPage(articleCountPerPage);
        businessCardVo.setRequestPage(requestPage);
        businessCardVo.setMinPage(minPage);
        businessCardVo.setMaxPage(maxPage);
        businessCardVo.setStartPage(startPage);
        businessCardVo.setEndPage(endPage);
        businessCardVo.setBusinessCardEntity(this.mainMapper.selectBusinessCardEntity(userId,articleCountPerPage,(requestPage - 1) * articleCountPerPage));
        return businessCardVo;
    }

    public void deleteBusinessCard(int index){
        this.mainMapper.deleteBusinessCard(index);
    }

    public void setBusinessCard(BusinessCardEntity businessCardEntity){
        this.mainMapper.insertBusinessCard(businessCardEntity);
    }

    public BusinessCardEntity getUpdateInfo(int index){
        return this.mainMapper.selectOneInfo(index);
    }

    public void setUpdateInfo(BusinessCardEntity businessCardEntity){
        this.mainMapper.updateBusinessCard(businessCardEntity);
    }
}
